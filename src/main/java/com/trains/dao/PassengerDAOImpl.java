package com.trains.dao;

import com.trains.model.Passenger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PassengerDAOImpl extends CrudDAO {

    public List<Passenger> allPassengers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Passenger").list();
    }

    public Passenger getById(int idPassenger) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Passenger.class,idPassenger);
    }
}
