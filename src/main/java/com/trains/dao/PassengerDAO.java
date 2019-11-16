package com.trains.dao;

import com.trains.model.entity.Passenger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PassengerDAO extends CrudDAO {

    public List<Passenger> getAllPassengers() {
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
        int getID = -1;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Passenger p where p.name like :name and p.surname like :surname and p.birthday = :birthday");
        query.setParameter("name",name);
        query.setParameter("surname",surname);
        query.setParameter("birthday",birthday.toLocalDate());
        List<Passenger> passengers = query.list();
        if (!passengers.isEmpty()) getID = passengers.get(0).getId();

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
        passengerLog.setUser("none");
        passengerLog.setBirthday(LocalDate.of(1990,12,12));
        passengerLog.setEmail("none");
        passengerLog.setId(0);
        passengerLog.setLogin("none");
        passengerLog.setPassword("none");
        passengerLog.setSurname("none");
        passengerLog.setName("none");
        Query query = session.createQuery("from Passenger p where p.login like :login");
        query.setParameter("login",login);
        List<Passenger> passengers = query.list();
        if(!passengers.isEmpty()) passengerLog = passengers.get(0);


        return passengerLog;
    }
}
