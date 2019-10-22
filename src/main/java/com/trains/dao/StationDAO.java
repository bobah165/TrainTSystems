package com.trains.dao;

import com.trains.model.dto.TrainFromStationDTO;
import com.trains.model.entity.Station;
import com.trains.model.entity.Timetable;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StationDAO extends CrudDAO {

    public List<Station> allStations() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Station ").list();
    }

    public Station getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Station.class,id);
    }

    public Station getByName (String name) {
        Session session = sessionFactory.getCurrentSession();
        List<Station> stations = session.createQuery("from Station ").list();
        Station station = new Station();
        for (Station stationFromList: stations) {
            if(stationFromList.getNameStation().equals(name)) {
                station = stationFromList;
                break;
            }
        }
        return station;
    }

    public void delByID (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Station.class,id));
    }

    public List<TrainFromStationDTO> getTrainFromStation(int idStation) {
        Session session = sessionFactory.getCurrentSession();
        List<TrainFromStationDTO> trainFromStationDTOS = new ArrayList<>();
        List<Timetable> timetables = session.createQuery("from Timetable where station.id = "+idStation).list();
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
