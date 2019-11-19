package com.trains.dao;


import com.trains.model.entity.TrainFromStation;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TrainFromStationDAO extends CrudDAO {
    @Override
    public TrainFromStation getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TrainFromStation.class,id);
    }

    public List<TrainFromStation> getAllTrain(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from TrainFromStation ").list();
    }



        public void deleteByTrainNumber(int trainNumber) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from TrainFromStation t where t.idTrain= :trainId");
        query.setParameter("trainId",trainNumber);
        List<TrainFromStation> trainFromStations = query.list();
        for (TrainFromStation trainFromStation: trainFromStations) {
            session.delete(trainFromStation);
            }
        }
}

