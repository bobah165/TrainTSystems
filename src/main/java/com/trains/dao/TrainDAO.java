package com.trains.dao;


import com.trains.model.entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class TrainDAO extends CrudDAO {

    public List<Train> allTrain() {
        Session session = sessionFactory.getCurrentSession();
         return session.createQuery("from Train").list();
    }

    public List<Train> allTrainPagination(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Train").setFirstResult(10*(page-1)).setMaxResults(10).list();
    }

    public int getCountOfPage() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count (*) from Train ",Number.class).getSingleResult().intValue();
    }


    public Train getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Train.class,id);
    }

    public void delByID (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Train.class,id));
    }

    public List<Ticket> getPassengerFromTrain (int idTrain){
        Session session = sessionFactory.getCurrentSession();
        List<Ticket> tickets = session.createQuery("from Ticket where train.id ="+idTrain).list();
        return tickets;
    }



//    public List<TrainWay> getTrainWaysForTrain(Train train) {
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("from TrainWay where numberWay = :way");
//        query.setParameter("way",train.getTrainWay().getNumberWay());
//        List<TrainWay> trainWays = query.list();
//        return trainWays;
//    }


    public void deleteIfNoPassengerInTrain() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Train where tickets.size = 0");
        List<Train> trains = query.list();
        if (!trains.isEmpty()) {
            for (Train train : trains) {
                session.delete(train);
            }
        }
    }

    public List<Train> getTrainsByDepartureDate(LocalDate departureDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Train where departureDate = :date");
        query.setParameter("date",departureDate);
        List<Train> trains = query.list();
        return trains;
    }

    public List<Train> getTrainByNumberWay (int numberWay) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Train where trainWay.numberWay = :way");
        query.setParameter("way",numberWay);
        List<Train> trains = query.list();
        return trains;
    }

    public List<Train> getTrainsByDepartureDateAndNumberWay(LocalDate departureDate, int numberWay){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Train t where t.departureDate = :date " +
                "and t.trainWay.numberWay = :way");
        query.setParameter("date",departureDate);
        query.setParameter("way",numberWay);
        return query.list();
    }

    public List<Train> getSortedByTrainNumber(int page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Train order by trainNumber");
        return query.setFirstResult(10*(page-1)).setMaxResults(10).list();
    }

    public List<Train> getSortedListByDepartureDate(int page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Train order by departureDate");
        return query.setFirstResult(10*(page-1)).setMaxResults(10).list();
    }

    public List<Train> findTrainByTrainNumber(int trainNumber) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Train where trainNumber = :trainNum");
        query.setParameter("trainNum",trainNumber);
        return query.list();
    }


    public List<Train> findTrainByDepartureDate(LocalDate departureDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Train where departureDate = :date");
        query.setParameter("date",departureDate);
        return query.list();
    }

}

