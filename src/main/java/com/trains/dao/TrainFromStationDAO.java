package com.trains.dao;

import com.trains.model.entity.Train;
import com.trains.model.entity.TrainFromStation;
import com.trains.model.entity.TrainWay;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class TrainFromStationDAO extends CrudDAO {
    @Override
    public TrainFromStation getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TrainFromStation.class,id);
    }

    public List<TrainFromStation> allTrain (){
        Session session = sessionFactory.getCurrentSession();
        List<TrainFromStation> trains = session.createQuery("from TrainFromStation ").list();
        return trains;
    }

    public void fillTheTable() {
        Session session = sessionFactory.getCurrentSession();
        List<TrainFromStation> trainFromStations = session.createQuery("from TrainFromStation").list();

            //получение списка поездов на сегодняшнюю дату
            Query query = session.createQuery("from Train t where t.departureDate =: nowDate");
            query.setParameter("nowDate", LocalDate.now());
            List<Train> trains = query.list();

            for (Train train : trains) {
                //поулчение маршрута для данного поезда
                Query query1 = session.createQuery("from TrainWay where numberWay = :way");
                query1.setParameter("way", train.getTrainWay().getNumberWay());
                List<TrainWay> trainWays = query1.list();


                if(trainFromStations.isEmpty()) {
                for (TrainWay trainWay : trainWays) {
                    TrainFromStation trainFromStation = new TrainFromStation();
                    trainFromStation.setArrivalTime(trainWay.getArrivalTime().toLocalTime());
                    trainFromStation.setDepartureTime(trainWay.getDepartureTime().toLocalTime());
                    trainFromStation.setDaysInWay(trainWay.getDaysInWay());
                    trainFromStation.setIdTrain(train.getTrainNumber());
                    trainFromStation.setNameStation(trainWay.getStation().getNameStation());

                    session.persist(trainFromStation);
                }

            } else {
                    // получение номеров поездов которые уже существуют в таблице Schedule
                    Set<Integer> trainId = new HashSet<>();
                    for (TrainFromStation trainFromStation: trainFromStations) {
                        trainId.add(trainFromStation.getIdTrain());
                    }
                        // добавление поездов которых нет в таблице Schedule
                        if (!trainId.contains(train.getTrainNumber())){
                            for (TrainWay trainWay : trainWays) {
                                TrainFromStation currentTrainFromStation = new TrainFromStation();
                                currentTrainFromStation.setArrivalTime(trainWay.getArrivalTime().toLocalTime());
                                currentTrainFromStation.setDepartureTime(trainWay.getDepartureTime().toLocalTime());
                                currentTrainFromStation.setDaysInWay(trainWay.getDaysInWay());
                                currentTrainFromStation.setIdTrain(train.getTrainNumber());
                                currentTrainFromStation.setNameStation(trainWay.getStation().getNameStation());

                                session.persist(currentTrainFromStation);
                            }
                        }
                    }
                }
        }

        public void deleteByTrainNumber(int trainNumber) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from TrainFromStation t where t.idTrain= :trainId");
        query.setParameter("trainId",trainNumber);
        List<TrainFromStation> trainFromStations = query.list();
        for (TrainFromStation trainFromStation: trainFromStations) {
            session.delete(trainFromStation);
            }
        }
}

