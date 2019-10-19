package com.trains.dao;

import com.trains.model.entity.Train;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainDAO extends CrudDAO {

    public List<Train> allTrain() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Train").list();
    }


    public Train getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Train.class,id);
    }

    public void delByID (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Train.class,id));
    }

}
