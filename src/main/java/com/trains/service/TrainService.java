package com.trains.service;

import com.trains.dao.*;
import com.trains.model.dto.*;
import com.trains.model.entity.*;
import com.trains.util.MessageSender;
import com.trains.util.mapperForDTO.SearchStationMapper;
import com.trains.util.mapperForDTO.TrainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TrainService {
    private TrainMapper trainMapper;
    private TrainDAO trainDAO;
    private SearchStationDAO searchStationDAO;
    private TicketInformDAO ticketInformDAO;
    private SearchStationMapper searchStationMapper;
    private TrainFromStationDAO trainFromStationDAO;
    private TrainWayDAO trainWayDAO;

    @Autowired
    public void setTrainWayDAO(TrainWayDAO trainWayDAO) {
        this.trainWayDAO = trainWayDAO;
    }

    @Autowired
    public void setTrainFromStationDAO(TrainFromStationDAO trainFromStationDAO) {
        this.trainFromStationDAO = trainFromStationDAO;
    }

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
        List<Train> trainList = trainDAO.getTrainByDepartureDate(LocalDate.now());
        Train train = trainMapper.mapDtoToEntity(trainDTO);
        trainDAO.add(train);

     //   List<TrainFromStation> trainFromStation = trainFromStationDAO.allTrain();
        List<Train> trains = trainDAO.getTrainByDepartureDate(LocalDate.now());

//        boolean b = trainFromStation.isEmpty();
//        if (b) {
//            for (Train train1 : trains) {
//                TrainFromStation trainFromStation1 = new TrainFromStation();
//                trainFromStation1.setIdTrain(train1.getId());
//                trainFromStation1.setDate(train1.getDepartureDate());
//
//                for (TrainWay trainWay : trainWayDAO.getWaysByNumberWay(train1.getTrainWay().getNumberWay())) {
//                    trainFromStation1.setNameStation(trainWay.getStation().getNameStation());
//                    trainFromStation1.setDepartureTime(trainWay.getDepartureTime().toLocalTime());
//                    trainFromStation1.setArrivalTime(trainWay.getArrivalTime().toLocalTime());
//                    trainFromStationDAO.add(trainFromStation1);
//                }
//            }
//        } else {
            boolean b1 = trainList.size() != trains.size();
            if (b1) {
                // если добавленный поезд будет на сегодня в расписаниии, то отправляем сообщение
                try {
                    MessageSender.send();
                } catch (JMSException ex) {
                    System.out.println(ex.getStackTrace()); // вставить логгер
                }
            }
       // }
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

    public void delByID (int id) {
        List<Train> trainList = trainDAO.getTrainByDepartureDate(LocalDate.now());
        trainDAO.delByID(id);

        List<Train> trains = trainDAO.getTrainByDepartureDate(LocalDate.now());

        if (trainList.size()!=trains.size()){
            // если изменилось расписание, удалили поезд, то отправлем сообщение
            try {
                MessageSender.send();
            } catch (JMSException ex) {
                System.out.println(ex.getStackTrace()); // вставить логгер
            }
        }
    }

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

    public void deleteIfNoPassengerInTrain() {
        trainDAO.deleteIfNoPassengerInTrain();
    }

    public List<Train> getTrainByDepartureDate (LocalDate departureDate) {
        return trainDAO.getTrainByDepartureDate(departureDate);
    }

}

