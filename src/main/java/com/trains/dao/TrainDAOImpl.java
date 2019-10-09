package com.trains.dao;

import com.trains.model.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainDAOImpl extends MainDAO  {

    public List<Train> allTrain() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Train").list();
    }


    public Train getById(int idTrain) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Train.class,idTrain);
    }
}
