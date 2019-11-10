package com.trains.dao;

import com.trains.model.dto.*;
import com.trains.model.entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<PassengersFromTrainDTO> getPassengerFromTrain (int idTrain){
        Session session = sessionFactory.getCurrentSession();
        List<Ticket> tickets = session.createQuery("from Ticket where train.id ="+idTrain).list();
        List<PassengersFromTrainDTO> passengersFromTrainDTOS = new ArrayList<>();
        for (Ticket ticket: tickets) {
            PassengersFromTrainDTO passengersFromTrainDTO = new PassengersFromTrainDTO();
            passengersFromTrainDTO.setTicketID(ticket.getId());
            passengersFromTrainDTO.setName(ticket.getPassenger().getName());
            passengersFromTrainDTO.setSurname(ticket.getPassenger().getSurname());
            passengersFromTrainDTO.setBirthday(ticket.getPassenger().getBirthday());

            passengersFromTrainDTOS.add(passengersFromTrainDTO);
        }

        return passengersFromTrainDTOS;
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
