package com.trains.service;

import com.trains.dao.SearchStationDAO;
import com.trains.dao.TicketInformDAO;
import com.trains.dao.TrainDAO;
import com.trains.dao.TrainWayDAO;
import com.trains.model.dto.*;
import com.trains.model.entity.SearchStations;
import com.trains.model.entity.TicketInform;
import com.trains.model.entity.Train;
import com.trains.model.entity.TrainWay;
import com.trains.util.mapperForDTO.SearchStationMapper;
import com.trains.util.mapperForDTO.TrainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainService {
    private TrainMapper trainMapper;
    private TrainDAO trainDAO;
    private SearchStationDAO searchStationDAO;
    private TicketInformDAO ticketInformDAO;
    private SearchStationMapper searchStationMapper;

    @Autowired
    public void setSearchStationMapper(SearchStationMapper searchStationMapper) {
        this.searchStationMapper = searchStationMapper;
    }

    @Autowired
    public void setTicketInformDAO(TicketInformDAO ticketInformDAO) {
        this.ticketInformDAO = ticketInformDAO;
    }

    @Autowired
    public void setSearchStationDAO(SearchStationDAO searchStationDAO) {
        this.searchStationDAO = searchStationDAO;
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

//    public String getDateOfStation (int trainId, String stationName) {
//        Train train = trainDAO.getById(trainId);
//        int getDays = 1;
//        LocalDate localDate = train.getDepartureDate();
//        List<TrainWay> trainWays = trainWayDAO.allWays();
//        List<TrainWayDTO> getForOneTrain = new ArrayList<>();
//        for (TrainWay trainWay: trainWays) {
//            if (trainWay.getNumberWay()==train.getTrainWay().getNumberWay()){
//                getForOneTrain.add(trainWay);
//            }
//        }
//        for (TrainWayDTO trainWayDTO: getForOneTrain) {
//            if(trainWayDTO.getStation().equals(stationName)) {
//                getDays = trainWayDTO.getDaysInWay();
//            }
//        }
//
//        localDate = localDate.plusDays(getDays-1);
//        return localDate.toString();
//    }

//    public TrainDTO getTrainByDate (List<TrainDTO> trains, SearchStationDTO searchStationDTO, int trainID) {
//        List<Train> trainsList = new ArrayList<>();
//        for (TrainDTO trainDTO: trains ) {
//            trainsList.add(trainMapper.mapDtoToEntity(trainDTO));
//        }
//        SearchStations searchStations = searchStationMapper.mapDtoToEntity(searchStationDTO);
//
//        return trainMapper.mapEntityToDto(trainDAO.getTrainByDate(trainsList,searchStations,trainID));
//    }


    public void addTicketInfByTrainId (int idTrain) {
        List<Train> trains = trainDAO.allTrain();
        int idCurrentPassenger = ((PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        SearchStations searchStation = searchStationDAO.getById(idCurrentPassenger);

        Train trainDTO = trainDAO.getTrainByDate(trains,searchStation,idTrain);
        List<TrainWay> trainWays = trainDAO.getTrainWaysForTrain();

        TicketInform ticketInform = ticketInformDAO.fullInformation(searchStation,trainWays,trainDTO);
        ticketInformDAO.add(ticketInform);
        searchStationDAO.delete(searchStation);
    }

}

