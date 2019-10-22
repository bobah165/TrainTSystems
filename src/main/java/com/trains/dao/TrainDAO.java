package com.trains.dao;

import com.trains.model.dto.PassengersFromTrainDTO;
import com.trains.model.dto.TrainFromStationAToB;
import com.trains.model.entity.*;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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


    public List<TrainFromStationAToB> getTrainsFromStations (String stationNameA, String stationNameB) {
        Session session = sessionFactory.getCurrentSession();
        List<Timetable> timetables = session.createQuery("from Timetable").list();
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
        List<TrainFromStationAToB> getTrainFromStationA = new ArrayList<>();
        List<TrainFromStationAToB> getTrainFromStationB = new ArrayList<>();

        for (Timetable timetable: timetables) {
            //находим поезда проезжающие через станцию А
            if (timetable.getStation().getId() == stationA.getId()) {
                TrainFromStationAToB trainFromStationAToB = new TrainFromStationAToB();
                trainFromStationAToB.setTrainID(timetable.getTrain().getId());
                trainFromStationAToB.setDeprtureStation(stationNameA);
                trainFromStationAToB.setArrivalStation(stationNameB);
                trainFromStationAToB.setArrivalTime(timetable.getArrivalTime());
                trainFromStationAToB.setDepartureTime(timetable.getDepartureTime());
                trainFromStationAToB.setCountFreeSits(timetable.getCountFreeSits());

                getTrainFromStationA.add(trainFromStationAToB);
            }
            //находим поезда проезжающие через станцию В
            if (timetable.getStation().getId() == stationB.getId()) {
                TrainFromStationAToB trainFromStationAToB = new TrainFromStationAToB();
                trainFromStationAToB.setTrainID(timetable.getTrain().getId());
                trainFromStationAToB.setDeprtureStation(stationNameA);
                trainFromStationAToB.setArrivalStation(stationNameB);
                trainFromStationAToB.setArrivalTime(timetable.getArrivalTime());
                trainFromStationAToB.setDepartureTime(timetable.getDepartureTime());
                trainFromStationAToB.setCountFreeSits(timetable.getCountFreeSits());

                getTrainFromStationB.add(trainFromStationAToB);
            }
        }

            //ищем поезда проезжающие и через станию А и через станцию В
            for(TrainFromStationAToB trainFromStationA: getTrainFromStationA){
                for (TrainFromStationAToB trainFromStationB:getTrainFromStationB) {

                    if (trainFromStationA.getTrainID()==trainFromStationB.getTrainID()) {
                        trainFromStationAToBS.add(trainFromStationA);
                        }
                    }
                }
        return trainFromStationAToBS;
    }

}
