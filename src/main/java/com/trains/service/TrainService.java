package com.trains.service;

import com.trains.dao.TrainDAO;
import com.trains.model.dto.PassengersFromTrainDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.dto.TrainFromStationAToB;
import com.trains.model.dto.TrainWayDTO;
import com.trains.model.entity.Train;
import com.trains.util.mapperForDTO.TrainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainService {
    private TrainMapper trainMapper;
    private TrainDAO trainDAO;
    private TrainWayService trainWayService;

    @Autowired
    public void setTrainWayService(TrainWayService trainWayService) {
        this.trainWayService = trainWayService;
    }

    @Autowired
    public void setTrainDAO (TrainDAO trainDAO) {
        this.trainDAO = trainDAO;
    }

    @Autowired
    public void setTrainMapper(TrainMapper trainMapper) {
        this.trainMapper = trainMapper;
    }

    public List<TrainDTO> allTrains() {
        List<Train> trains = trainDAO.allTrain();
        List<TrainDTO> trainDTOS = new ArrayList<>();
        for (Train train: trains) {
            trainDTOS.add(trainMapper.mapEntityToDto(train));
        }
        return trainDTOS;
    }

    public void add(TrainDTO trainDTO) {
        Train train = trainMapper.mapDtoToEntity(trainDTO);
        trainDAO.add(train);
    }

    public void delete(TrainDTO trainDTO) {
        Train train = trainMapper.mapDtoToEntity(trainDTO);
        trainDAO.delete(train);
    }

    public void edit(TrainDTO trainDTO) {
        Train train = trainMapper.mapDtoToEntity(trainDTO);
        trainDAO.edit(train);
    }

    public TrainDTO getById(int idTrain) {
        Train train = trainDAO.getById(idTrain);
        TrainDTO trainDTO = trainMapper.mapEntityToDto(train);
        return trainDTO;
    }

    public void delByID (int id) { trainDAO.delByID(id); }

    public List<PassengersFromTrainDTO> getPassengerFromTrain (int idTrain) {
        return trainDAO.getPassengerFromTrain(idTrain);
    }

    public List<TrainFromStationAToB> getTrainsFromStations (String stationNameA, String stationNameB, Time startTime, Time endTime, LocalDate departureDate) {
        return trainDAO.getTrainsFromStations(stationNameA,stationNameB,startTime,endTime, departureDate);
    }

    public String getDateOfStation (int trainId, String stationName) {
        Train train = trainDAO.getById(trainId);
        int getDays = 1;
        LocalDate localDate = train.getDepartureDate();
        List<TrainWayDTO> trainWays = trainWayService.allWays();
        List<TrainWayDTO> getForOneTrain = new ArrayList<>();
        for (TrainWayDTO trainWayDTO: trainWays) {
            if (trainWayDTO.getNumberWay()==train.getTrainWay().getNumberWay()){
                getForOneTrain.add(trainWayDTO);
            }
        }
        for (TrainWayDTO trainWayDTO: getForOneTrain) {
            if(trainWayDTO.getStation().equals(stationName)) {
                getDays = trainWayDTO.getDaysInWay();
            }
        }

        localDate = localDate.plusDays(getDays-1);
        return localDate.toString();
    }

}

