package com.trains.dao;


import com.trains.model.entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

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

    public int trainCountForPage() {
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

    public Train getTrainByDate (List<Train> trains, SearchStations searchStationDTO, int trainID) {
        Train currentTrain = new Train();
        for (Train train: trains){
            //String depDate1 = trainService.getDateOfStation(trainID,searchStationDTO.getDepartureStation());
            if ((train.getDepartureDate().isEqual(searchStationDTO.getDepartureDate()) && train.getId()==trainID)) {
                currentTrain = train;
            }
        }

        return currentTrain;
    }

    public List<TrainWay> getTrainWaysForTrain () {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from TrainWay ").list();

    }

    public List<Train> getTrainByDepartureDate (LocalDate departureDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Train t where t.departureDate = :departureDate");
        query.setParameter("departureDate",departureDate);
        List<Train> trains = query.list();
        return trains;
    }

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


}
