package com.trains.service;

import com.trains.dao.*;
import com.trains.model.dto.*;
import com.trains.model.entity.*;
import com.trains.util.MessageSender;
import com.trains.util.mapperForDTO.TrainMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainService {
    private TrainMapper trainMapper;
    private TrainDAO trainDAO;
    private SearchStationDAO searchStationDAO;
    private TicketInformDAO ticketInformDAO;
    private TrainFromStationDAO trainFromStationDAO;

    private static Logger logger = LoggerFactory.getLogger(TrainService.class);

    @Autowired
    public void setTrainFromStationDAO(TrainFromStationDAO trainFromStationDAO) {
        this.trainFromStationDAO = trainFromStationDAO;
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

    public List<TrainDTO> allTrainsPagination(int page) {
        List<Train> trains = trainDAO.allTrainPagination(page);
        List<TrainDTO> trainDTOS = new ArrayList<>();
        for (Train train: trains) {
            trainDTOS.add(trainMapper.mapEntityToDto(train));
        }
        return trainDTOS;
    }

    public int trainCountForPage() {
        return trainDAO.trainCountForPage();
    }

    public void add(TrainDTO trainDTO) {
        Train train = trainMapper.mapDtoToEntity(trainDTO);
        trainDAO.add(train);

        if(train.getDepartureDate().isEqual(LocalDate.now())) {
            try {
                MessageSender.send();
            } catch (Exception e) {
                logger.error("Message haven't been sent");
            }
        }

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
        Train train = trainDAO.getById(id);
        if (train.getDepartureDate().isEqual(LocalDate.now())){
            trainFromStationDAO.deleteByTrainNumber(train.getTrainNumber());
            try {
                MessageSender.send();
            } catch (JMSException ex) {
                logger.error("Message haven't been sent");
            }
        }

        trainDAO.delByID(id);
    }

    public List<PassengersFromTrainDTO> getPassengerFromTrain (int idTrain) {
        List<Ticket> tickets = trainDAO.getPassengerFromTrain(idTrain);
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

