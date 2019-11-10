package com.trains.dao;

import com.trains.model.entity.TrainWay;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainWayDAO extends CrudDAO {

    public List<TrainWay> allWays() {
        Session session = sessionFactory.getCurrentSession();
        List<TrainWay> trainWays = session.createQuery("from TrainWay").list();
        return trainWays;
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

    public List<TrainWay> getWaysByNumberWay (int numberWay) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from TrainWay t where t.numberWay = :numberWay");
        query.setParameter("numberWay",numberWay);
        List<TrainWay> trainWays = query.list();
        return trainWays;
    }
}
