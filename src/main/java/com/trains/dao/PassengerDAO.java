package com.trains.dao;

import com.trains.model.entity.Passenger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PassengerDAO extends CrudDAO {

    public List<Passenger> allPassengers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Passenger").list();
    }

    public void delByID (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Passenger.class,id));
    }


    public Passenger getById(int idPassenger) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Passenger.class,idPassenger);
    }
}
