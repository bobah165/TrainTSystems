package com.trains.dao;

import com.trains.model.entity.Passenger;
import org.hibernate.Session;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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

    public int getPassengerId (String name, String surname, Date birthday) {
        int getID = 0;
        Session session = sessionFactory.getCurrentSession();
        List<Passenger> passengers = session.createQuery("from Passenger").list();
        for (Passenger passenger: passengers) {
            boolean b = passenger.getName().equals(name);
            boolean b1 =passenger.getSurname().equals(surname);
            boolean b2 = passenger.getBirthday().isEqual(birthday.toLocalDate());
            if (passenger.getName().equals(name)&&passenger.getSurname().equals(surname)&&passenger.getBirthday().isEqual(birthday.toLocalDate())) {
                        getID = passenger.getId();
                        break;
                    } else getID = -1;
            }

        return getID;
    }

    public void addPassengerByNameSurnameDate (String name, String surname, Date birthday) {
        Session session = sessionFactory.getCurrentSession();
        Passenger passenger = new Passenger();

        passenger.setBirthday(birthday.toLocalDate());
        passenger.setName(name);
        passenger.setSurname(surname);

        passenger.setLogin("none");
        passenger.setPassword("none");
        passenger.setUser("passenger");
        passenger.setEmail("none");

        session.persist(passenger);

    }

    public Passenger getPassengerBylogin (String login) {
        Session session = sessionFactory.getCurrentSession();
        Passenger passengerLog = new Passenger();
        List<Passenger> passengers = session.createQuery("from Passenger ").list();
        for (Passenger passenger: passengers) {
            if (passenger.getLogin().equals(login)) {
                passengerLog = passenger;
            }
        }

        return passengerLog;
    }
}
