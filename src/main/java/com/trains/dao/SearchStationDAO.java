package com.trains.dao;

import com.trains.model.entity.SearchStations;
import org.hibernate.Session;
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
}
