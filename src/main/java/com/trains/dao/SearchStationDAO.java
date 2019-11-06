package com.trains.dao;

import com.trains.model.dto.TrainFromStationAToB;
import com.trains.model.entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchStationDAO extends CrudDAO{

    @Override
    public SearchStations getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(SearchStations.class,id);
    }

    public List<SearchStations> allTrains() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from SearchStations ").list();
    }

    public void delByID (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(SearchStations.class,id));
    }

    public List<TrainFromStationAToB> getTrainsFromStations (String stationNameA, String stationNameB, LocalTime startTime, LocalTime endTime, LocalDate departureDate)
    {
        Session session = sessionFactory.getCurrentSession();
        // находим инофрмаци о станциях в БД
        Station stationA = new Station();
        Station stationB = new Station();
        List<TrainWay> timetables = session.createQuery("from TrainWay ").list();
        Query queryA = session.createQuery("from Station s where s.nameStation like :stationNameA");
        queryA.setParameter("stationNameA",stationNameA);
        List<Station> stationsA = queryA.list();
        if (!stationsA.isEmpty()) stationA = stationsA.get(0);

        Query queryB = session.createQuery("from Station s where s.nameStation like :stationNameB");
        queryB.setParameter("stationNameB",stationNameB);
        List<Station> stations = queryB.list();
        if (!stations.isEmpty()) stationB = stations.get(0);


        List<TrainFromStationAToB> trainFromStationAToBS = new ArrayList<>();
        List<TrainWay> trainWaysBetweenAandB = new ArrayList<>();

        Query queryWayA = session.createQuery("from TrainWay t where t.id = : stationId");
        queryWayA.setParameter("stationId",stationA.getId());
        List<TrainWay> getTrainWayA = queryWayA.list();

        Query queryWayB = session.createQuery("from TrainWay t where t.id = : stationId");
        queryWayA.setParameter("stationId",stationB.getId());
        List<TrainWay> getTrainWayB = queryWayA.list();


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
                            depTime = trainWay.getDepartureTime();
                        }
                        if (trainWay.getStation().getNameStation().equals(stationNameB)) {
                            arrivTime = trainWay.getArrivalTime();
                        }
                    }
                }
                trainFromStationAToB.setDepartureTime((depTime.toLocalTime()));
                trainFromStationAToB.setArrivalTime(arrivTime.toLocalTime());

                trainFromStationAToBS.add(trainFromStationAToB);
            }
        }

        //ищем поезда в деапазоне времени
        List<TrainFromStationAToB> trainFromStationAToBS1 = new ArrayList<>();
        trainFromStationAToBS1.addAll(trainFromStationAToBS);
        for (TrainFromStationAToB trainFromStationAToB: trainFromStationAToBS) {
            if(!(trainFromStationAToB.getDepartureTime().isAfter(startTime)&&trainFromStationAToB.getDepartureTime().isBefore(endTime))){
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
