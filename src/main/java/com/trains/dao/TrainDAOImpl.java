package com.trains.dao;

import com.trains.model.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainDAOImpl implements TrainDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Train> allTrains() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Train").list();
    }

    @Override
    public void add(Train train) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(train);
    }

    @Override
    public void delete(Train train) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(train);
    }

    @Override
    public void edit(Train train) {
        Session session = sessionFactory.getCurrentSession();
        session.update(train);
    }

    @Override
    public Train getById(int idTrain) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Train.class,idTrain);
    }
}
