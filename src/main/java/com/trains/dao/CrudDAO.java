package com.trains.dao;


import com.trains.dto.TrainFromStationDTO;
import com.trains.model.Station;
import com.trains.model.Timetable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CrudDAO {
    protected SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public <T> void add(T object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
    }

    public <T> void delete(T object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }

    public <T> void edit(T object) {
        Session session = sessionFactory.getCurrentSession();
        session.update(object);
    }

    public List<TrainFromStationDTO> getTrainFromStation(int idStation) {
        Session session = sessionFactory.getCurrentSession();
        List<TrainFromStationDTO> trainFromStationDTOS = new ArrayList<>();
        List<Timetable> timetables = session.createQuery("from Timetable where station.id_station = "+idStation).list();
        Station station = session.get(Station.class, idStation);

        for (Timetable timetable : timetables) {
            TrainFromStationDTO trainFromStationDTO = new TrainFromStationDTO();
            trainFromStationDTO.setIdTrain(timetable.getTrain().getId());
            trainFromStationDTO.setNameStation(station.getNameStation());
            trainFromStationDTO.setDepartureTime(timetable.getDepartureTime());
            trainFromStationDTO.setArrivalTime(timetable.getArrivalTime());

            trainFromStationDTOS.add(trainFromStationDTO);
        }

        return trainFromStationDTOS;
    }

}
