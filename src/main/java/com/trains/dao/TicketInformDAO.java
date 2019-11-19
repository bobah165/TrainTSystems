package com.trains.dao;

import com.trains.model.dto.*;
import com.trains.model.entity.TicketInform;
import org.hibernate.Session;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class TicketInformDAO extends CrudDAO {

    public List<TicketInform> getAllTickets() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from TicketInform ").list();
    }

    @Override
    public TicketInform getById(int idTicket) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TicketInform.class,idTicket);
    }

    public void delByID (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(TicketInform.class,id));
    }



    public void addPassengerInformationToTicket(String name, String surname, Date birthday, int idPassenger){
        Session session = sessionFactory.getCurrentSession();
        int idCurrentPassenger = ((PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        TicketInform ticketInform = session.get(TicketInform.class,idCurrentPassenger);

        ticketInform.setName(name);
        ticketInform.setSurname(surname);
        ticketInform.setBirthday(birthday.toLocalDate());
        ticketInform.setIdPassenger(idPassenger);

        session.update(ticketInform);
    }
}
