package com.trains.dao;

import com.trains.model.entity.Passenger;
import com.trains.model.entity.Ticket;
import com.trains.model.entity.Train;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class TicketDAO extends CrudDAO {

    public List<Ticket> allTickets() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Ticket").list();
    }

    @Override
    public Ticket getById(int idTicket) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Ticket.class,idTicket);
    }

    public void delByID (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Ticket.class,id));
    }


    public void addTicketByTrainDTOPassengerDTO (Train train, Passenger passenger) {
        Session session = sessionFactory.getCurrentSession();
        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setTrain(train);
        session.persist(ticket);

    }

    public boolean checkTicketByNameSurnameBirthday (String name, String surname, Date birthday, Train train) {
        Session session = sessionFactory.getCurrentSession();
        boolean isValid = false;
        Query query = session.createQuery("from Ticket where train.id = :idTrain " +
                " and passenger.name like :name" +
                " and passenger.surname like :surname"+
                " and passenger.birthday = :date");
        query.setParameter("idTrain",train.getId());
        query.setParameter("name",name);
        query.setParameter("surname",surname);
        query.setParameter("date",birthday.toLocalDate());
        List<Ticket> tickets = query.list();
        if (tickets.isEmpty()) {
            isValid = true;
        }
        return isValid;
    }

}
