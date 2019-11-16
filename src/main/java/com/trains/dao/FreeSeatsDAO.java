package com.trains.dao;

import com.trains.model.entity.FreeSeats;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import java.util.List;

@Repository
public class FreeSeatsDAO extends CrudDAO {
    @Override
    public FreeSeats getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(FreeSeats.class,id);
    }

    public List<FreeSeats> getAllSeats() {
        Session session = sessionFactory.getCurrentSession();
        List<FreeSeats> freeSeats = session.createQuery("from FreeSeats ").list();
        return freeSeats;
    }

    public FreeSeats getByStationAndTrainID (String stationName, int trainID) {
        Session session = sessionFactory.getCurrentSession();
        FreeSeats freeSeat = new FreeSeats();
        Query query = session.createQuery("from FreeSeats where idTrain = :trainID and stationName like :stationName");
        query.setParameter("stationName",stationName);
        query.setParameter("trainID",trainID);
        List<FreeSeats> freeSeats = query.list();
        if (!freeSeats.isEmpty()) freeSeat = freeSeats.get(0);
        return freeSeat;
    }
 }
