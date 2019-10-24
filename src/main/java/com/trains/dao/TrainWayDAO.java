package com.trains.dao;

import com.trains.model.entity.TrainWay;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainWayDAO extends CrudDAO {

    public List<TrainWay> allWays() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from TrainWay").list();
    }

    public void delByID (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(TrainWay.class,id));
    }

    @Override
    public TrainWay getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TrainWay.class,id);
    }
}
