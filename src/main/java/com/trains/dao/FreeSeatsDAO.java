package com.trains.dao;

import com.trains.model.entity.FreeSeats;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FreeSeatsDAO extends CrudDAO {
    @Override
    public FreeSeats getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(FreeSeats.class,id);
    }

    public List<FreeSeats> allSeats () {
        Session session = sessionFactory.getCurrentSession();
        List<FreeSeats> freeSeats = session.createQuery("from FreeSeats ").list();
        return freeSeats;
    }

    public FreeSeats getByStationAndTrainID (String stationName, int trainID) {
        Session session = sessionFactory.getCurrentSession();
        List<FreeSeats> freeSeats = session.createQuery("from FreeSeats where idTrain = "+trainID).list();
        for(FreeSeats freeSeat:freeSeats){
            if (freeSeat.getStationName().equals(stationName))
                return freeSeat;
        }
        return new FreeSeats();
    }
 }
