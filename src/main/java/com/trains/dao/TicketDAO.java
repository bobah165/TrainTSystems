package com.trains.dao;

import com.trains.model.entity.Passenger;
import com.trains.model.entity.Ticket;
import com.trains.model.entity.Train;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.util.ArrayList;
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

}
