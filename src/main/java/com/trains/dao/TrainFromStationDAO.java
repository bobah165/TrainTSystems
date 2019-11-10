package com.trains.dao;

import com.trains.model.entity.TrainFromStation;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainFromStationDAO extends CrudDAO {
    @Override
    public TrainFromStation getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TrainFromStation.class,id);
    }

    public List<TrainFromStation> allTrain (){
        Session session = sessionFactory.getCurrentSession();
        List<TrainFromStation> trains = session.createQuery("from TrainFromStation ").list();
        return trains;
    }

//    public List<TrainFromStation> getTrainByStationId (int stationId){
//
//    }


}
