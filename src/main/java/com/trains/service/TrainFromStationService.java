package com.trains.service;

import com.trains.dao.TrainDAO;
import com.trains.dao.TrainFromStationDAO;
import com.trains.dao.TrainWayDAO;
import com.trains.model.dto.TrainFromStationDTO;
import com.trains.model.entity.Train;
import com.trains.model.entity.TrainFromStation;
import com.trains.model.entity.TrainWay;
import com.trains.util.mapperForDTO.TrainFromStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TrainFromStationService {
    private TrainFromStationDAO trainFromStationDAO;
    private TrainDAO trainDAO;
    private TrainWayDAO trainWayDAO;
    private TrainFromStationMapper trainFromStationMapper;

    @Autowired
    public void setTrainWayDAO(TrainWayDAO trainWayDAO) {
        this.trainWayDAO = trainWayDAO;
    }

    @Autowired
    public void setTrainDAO(TrainDAO trainDAO) {
        this.trainDAO = trainDAO;
    }

    @Autowired
    public void setTrainFromStationMapper(TrainFromStationMapper trainFromStationMapper) {
        this.trainFromStationMapper = trainFromStationMapper;
    }

    @Autowired
    public void setTrainFromStationDAO(TrainFromStationDAO trainFromStationDAO) {
        this.trainFromStationDAO = trainFromStationDAO;
    }

    public List<TrainFromStationDTO> getAllTrains() {
        List<TrainFromStation> trains = trainFromStationDAO.getAllTrain();
        List<TrainFromStationDTO> trainDTOS = new ArrayList<>();
        for (TrainFromStation train: trains) {
            trainDTOS.add(trainFromStationMapper.mapEntityToDto(train));
        }
        return trainDTOS;
    }

    public void add(TrainFromStationDTO trainFromStationDTO) {
        TrainFromStation train = trainFromStationMapper.mapDtoToEntity(trainFromStationDTO);
        trainFromStationDAO.add(train); }

    public void delete(TrainFromStationDTO trainFromStationDTO) {
        TrainFromStation train = trainFromStationMapper.mapDtoToEntity(trainFromStationDTO);
        trainFromStationDAO.delete(train); }

    public void edit(TrainFromStationDTO trainFromStationDTO) {
        TrainFromStation train = trainFromStationMapper.mapDtoToEntity(trainFromStationDTO);
        trainFromStationDAO.edit(train);
    }

    public TrainFromStationDTO getById(int id) {
        TrainFromStation train = trainFromStationDAO.getById(id);
        return trainFromStationMapper.mapEntityToDto(train);
    }

    public void fillTheTable() {
        List<TrainFromStation> trainFromStations = trainFromStationDAO.getAllTrain();

        //получение списка поездов на сегодняшнюю дату
        List<Train> trains = trainDAO.getTrainsByDepartureDate(LocalDate.now());

        for (Train train : trains) {
            //поулчение маршрута для данного поезда
            List<TrainWay> trainWays = trainWayDAO.getWaysByNumberWay(train.getTrainWay().getNumberWay());


            if (trainFromStations.isEmpty()) {
                for (TrainWay trainWay : trainWays) {
                    TrainFromStation trainFromStation = new TrainFromStation();
                    trainFromStation.setArrivalTime(trainWay.getArrivalTime().toLocalTime());
                    trainFromStation.setDepartureTime(trainWay.getDepartureTime().toLocalTime());
                    trainFromStation.setDaysInWay(trainWay.getDaysInWay());
                    trainFromStation.setIdTrain(train.getTrainNumber());
                    trainFromStation.setNameStation(trainWay.getStation().getNameStation());

                    trainFromStationDAO.add(trainFromStation);
                }

            } else {
                // получение номеров поездов которые уже существуют в таблице Schedule
                Set<Integer> trainId = new HashSet<>();
                for (TrainFromStation trainFromStation : trainFromStations) {
                    trainId.add(trainFromStation.getIdTrain());
                }
                // добавление поездов которых нет в таблице Schedule
                if (!trainId.contains(train.getTrainNumber())) {
                    for (TrainWay trainWay : trainWays) {
                        TrainFromStation currentTrainFromStation = new TrainFromStation();
                        currentTrainFromStation.setArrivalTime(trainWay.getArrivalTime().toLocalTime());
                        currentTrainFromStation.setDepartureTime(trainWay.getDepartureTime().toLocalTime());
                        currentTrainFromStation.setDaysInWay(trainWay.getDaysInWay());
                        currentTrainFromStation.setIdTrain(train.getTrainNumber());
                        currentTrainFromStation.setNameStation(trainWay.getStation().getNameStation());

                        trainFromStationDAO.add(currentTrainFromStation);
                    }
                }
            }
        }
    }
}
