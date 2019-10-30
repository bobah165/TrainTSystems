package com.trains.dao;

import com.trains.model.dto.PassengersFromTrainDTO;
import com.trains.model.dto.TrainFromStationAToB;
import com.trains.model.entity.*;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeSet;

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


    public List<TrainFromStationAToB> getTrainsFromStations (String stationNameA, String stationNameB, Time startTime, Time endTime, LocalDate departureDate)
    {
        Session session = sessionFactory.getCurrentSession();
        List<TrainWay> timetables = session.createQuery("from TrainWay ").list();
        List<Station> stations = session.createQuery("from Station").list();
        // находим инофрмаци о станциях в БД
        Station stationA = new Station();
        Station stationB = new Station();
        for (Station station: stations){
            if (station.getNameStation().equals(stationNameA)) {
                stationA = station;
            }
            if (station.getNameStation().equals(stationNameB)) {
                stationB = station;
            }
        }

        List<TrainFromStationAToB> trainFromStationAToBS = new ArrayList<>();
        List<TrainWay> getTrainWayA = new ArrayList<>();
        List<TrainWay> getTrainWayB = new ArrayList<>();
        List<TrainWay> trainWaysBetweenAandB = new ArrayList<>();

        for (TrainWay timetable: timetables) {
            //находим маршруты поездов через станцию А
            if (timetable.getStation().getId() == stationA.getId()) {
                getTrainWayA.add(timetable);
            }
            //находим маршруты поездов через станцию В
            if (timetable.getStation().getId() == stationB.getId()) {
                getTrainWayB.add(timetable);
            }
        }
        // находим маршруты поездов проходящих и через А и через В
        for (TrainWay trainWayA: getTrainWayA ) {
            for (TrainWay trainWayB: getTrainWayB) {
                if(trainWayA.getNumberWay()==trainWayB.getNumberWay()){
                    trainWaysBetweenAandB.add(trainWayA);
                }
            }
        }

            //ищем поезда проезжающие по этим маршрутам
            List<Train> allTrains = session.createQuery("from Train ").list();
            List<Train> trainsFromAtoB = new ArrayList<>();
            for (Train train: allTrains) {
                for (TrainWay trainWay: trainWaysBetweenAandB)
                if (train.getTrainWay().getNumberWay()==trainWay.getNumberWay()) {
                    trainsFromAtoB.add(train);
                }
            }

            int days = 0; // количество дней в пути
            // получение даты у поездов которые несколько дней в пути
            for (Train train:trainsFromAtoB) {
                for (TrainWay trainWay: timetables) {
                    if (train.getTrainWay().getNumberWay()==trainWay.getNumberWay()) {
                        if (trainWay.getStation().getNameStation().equals(stationNameA)){
                            days = trainWay.getDaysInWay();
                        }
                    }
                }
                if (train.getDepartureDate().plusDays(days-1).isEqual(departureDate)) {
                    TrainFromStationAToB trainFromStationAToB = new TrainFromStationAToB();
                    trainFromStationAToB.setTrainID(train.getId());
                    trainFromStationAToB.setCountFreeSits(train.getCountSits());
                    trainFromStationAToB.setArrivalStation(stationNameB);
                    trainFromStationAToB.setDeprtureStation(stationNameA);

                    Time depTime = Time.valueOf("00:00:00");
                    Time arrivTime = Time.valueOf("00:00:00");
                    for (TrainWay trainWay : timetables) {
                        if (train.getTrainWay().getNumberWay() == trainWay.getNumberWay()) {
                            if (trainWay.getStation().getNameStation().equals(stationNameA)) {
                                depTime = trainWay.getShedule();
                                arrivTime = trainWay.getStopTime();
                            }
                        }
                    }
                    trainFromStationAToB.setDepartureTime(depTime);
                    trainFromStationAToB.setArrivalTime(arrivTime);

                    trainFromStationAToBS.add(trainFromStationAToB);
                }
            }

            //ищем поезда в деапазоне времени
        List<TrainFromStationAToB> trainFromStationAToBS1 = new ArrayList<>();
            trainFromStationAToBS1.addAll(trainFromStationAToBS);
        for (TrainFromStationAToB trainFromStationAToB: trainFromStationAToBS) {
            if(!(trainFromStationAToB.getDepartureTime().after(startTime)&&trainFromStationAToB.getDepartureTime().before(endTime))){
               trainFromStationAToBS1.remove(trainFromStationAToB);
            }
       }

        List<FreeSeats> freeSeats = session.createQuery("from FreeSeats ").list();
        for(TrainFromStationAToB trainFromStationAToB: trainFromStationAToBS1) {
            for(FreeSeats freeSeat: freeSeats) {
                if (trainFromStationAToB.getTrainID()==freeSeat.getIdTrain()
                &&trainFromStationAToB.getDeprtureStation().equals(freeSeat.getStationName())) {
                    trainFromStationAToB.setCountFreeSits(freeSeat.getFreeSeats());
                }
            }
        }


        return trainFromStationAToBS1;
    }

}
