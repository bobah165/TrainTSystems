package com.trains.dao;

import com.trains.model.entity.TicketInform;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketInformDAO extends CrudDAO {

    public List<TicketInform> allTickets() {
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
}
