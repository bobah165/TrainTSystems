package com.trains.dao;

import com.trains.model.entity.Ticket;
import com.trains.model.entity.Train;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    public void delByID (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Ticket.class,id));
    }

    public List<Ticket> getByTrain (Train train) {
        Session session = sessionFactory.getCurrentSession();
        List<Ticket> tickets = session.createQuery("from Ticket").list();
        List<Ticket> ticketList = new ArrayList<>();
        for (Ticket ticket: tickets) {
            if (ticket.getTrain().getCountSits()==train.getId()) {
                ticketList.add(ticket);
            }
        }
        return ticketList;
    }

}
