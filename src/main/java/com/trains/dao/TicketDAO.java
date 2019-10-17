package com.trains.dao;

import com.trains.model.Ticket;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDAO extends CrudDAO {
    public List<Ticket> allTickets() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Ticket").list();
    }

    public Ticket getById(int idTicket) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Ticket.class,idTicket);
    }
}
