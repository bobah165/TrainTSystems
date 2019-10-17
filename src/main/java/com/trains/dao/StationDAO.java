package com.trains.dao;

import com.trains.model.Station;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StationDAO extends CrudDAO {

    public List<Station> allStations() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Station ").list();
    }

    public Station getById(int idPassenger) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Station.class,idPassenger);
    }
}
