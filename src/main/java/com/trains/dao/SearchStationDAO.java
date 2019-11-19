package com.trains.dao;


import com.trains.model.entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchStationDAO extends CrudDAO{

    @Override
    public SearchStations getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(SearchStations.class,id);
    }

    public List<SearchStations> allTrains() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from SearchStations ").list();
    }

    public void delByID (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(SearchStations.class,id));
    }


    public Station getStationByName(String stationName) {
        Session session = sessionFactory.getCurrentSession();
        Station station = new Station();
        Query queryA = session.createQuery("from Station s where s.nameStation like :stationName");
        queryA.setParameter("stationName",stationName);
        List<Station> stationsA = queryA.list();
        if (!stationsA.isEmpty()) station = stationsA.get(0);
        return station;
    }
}
