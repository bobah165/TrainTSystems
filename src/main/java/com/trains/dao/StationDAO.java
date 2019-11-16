package com.trains.dao;

import com.trains.model.dto.TrainFromStationDTO;
import com.trains.model.dto.TrainWayDTO;
import com.trains.model.entity.Station;
import com.trains.model.entity.Train;
import com.trains.model.entity.TrainWay;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StationDAO extends CrudDAO {

    public List<Station> getAllStations() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Station ").list();
    }

    public List<Station> getStationsForPagination(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Station").setFirstResult(10*(page-1)).setMaxResults(10).list();
    }

    public int getCountStationsForPagination() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count (*) from Station ",Number.class).getSingleResult().intValue();
    }


    public Station getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Station.class,id);
    }

    public Station getByName (String name) {
        Session session = sessionFactory.getCurrentSession();
        Station station = new Station();
        Query query = session.createQuery("from Station where nameStation like :stationName");
        query.setParameter("stationName",name);
        List<Station> stations = query.list();
        if (!stations.isEmpty())  station = stations.get(0);
        return station;
    }

    public void delByID (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Station.class,id));
    }



    // получение расписание поездов по станции
    // общий алгоритм: составляем список маршрутов проходящих через заданную станцию
    // ищем поезда идущие по маршруту, в котором есть заданная станция
    // смотрим в какую дату проходит поезд заданную станцию и сравниваем с искомой датой
    public List<TrainFromStationDTO> getTrainFromStation(int idStation, Date departureDate, LocalTime startTime, LocalTime endTime) {
        Session session = sessionFactory.getCurrentSession();
        // основной массив с расписанием
        List<TrainFromStationDTO> trainFromStationDTOS = new ArrayList<>();
        // поиск маршрутов проходящих через заданную станцию
        List<TrainWay> trainWays = session.createQuery("from TrainWay where station.id = "+idStation).list();
        // список всех поездов
        List<Train> trains = session.createQuery("from Train").list();

        // информация о станции
        Station station = session.get(Station.class, idStation);

        //поиск поездов ходящих по маршруту в котором есть искомая станция
        for (TrainWay trainWay : trainWays) {
            for(Train train: trains) {
                if (trainWay.getNumberWay()==train.getTrainWay().getNumberWay()){
                    int days = 0; //количество дней в пути по расписанию
                    LocalDate localDate = train.getDepartureDate(); // время отправления поезда по расписанию

                    //ищем объект по номеру маршрута и станции
                    Query query = session.createQuery("from TrainWay t where t.station.id =:idStation and t.numberWay =:trainWay");
                    query.setParameter("idStation",idStation);
                    query.setParameter("trainWay",trainWay.getNumberWay());
                    List<TrainWay> trainWayDTOS = query.list();
                    if (!trainWayDTOS.isEmpty()) {
                        days = trainWayDTOS.get(0).getDaysInWay();
                        days = days - 1;
                    }

                    LocalDate localDateSearch = departureDate.toLocalDate(); // дата поиска

                    //добавлене поезда в список, если в дату поиска он проходит искомую станцию
                        if (localDateSearch.isEqual(localDate.plusDays(days))) {
                            TrainFromStationDTO trainFromStationDTO = new TrainFromStationDTO();
                            trainFromStationDTO.setIdTrain(train.getId());
                            trainFromStationDTO.setNameStation(station.getNameStation());
                            trainFromStationDTO.setDepartureTime(trainWay.getDepartureTime().toLocalTime());
                            trainFromStationDTO.setArrivalTime(trainWay.getArrivalTime().toLocalTime());
                            trainFromStationDTOS.add(trainFromStationDTO);
                        }
                }
            }
        }

        // из полученного листа поездов по дате ищем поезда удовлетворяющие заданному промежутку времени
        List<TrainFromStationDTO> finalTrainList = new ArrayList<>();
            for (TrainFromStationDTO trainFromStationDTO: trainFromStationDTOS) {
                LocalTime trainTime = trainFromStationDTO.getDepartureTime();
                if (trainTime.isAfter(startTime) && trainTime.isBefore(endTime)) {
                    finalTrainList.add(trainFromStationDTO);
                }
            }


        return finalTrainList;
    }
}
