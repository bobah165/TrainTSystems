package com.trains.service;

import com.trains.dao.*;
import com.trains.model.dto.*;
import com.trains.model.entity.*;
import com.trains.util.MessageSender;
import com.trains.util.mapperForDTO.PassengerMapper;
import com.trains.util.mapperForDTO.TrainMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrainService {
    private TrainMapper trainMapper;
    private TrainDAO trainDAO;
    private TicketDAO ticketDAO;
    private PassengerDAO passengerDAO;
    private SearchStationDAO searchStationDAO;
    private TicketInformDAO ticketInformDAO;
    private TrainFromStationDAO trainFromStationDAO;
    private FreeSeatsDAO freeSeatsDAO;
    private TrainWayDAO trainWayDAO;
    private PassengerMapper passengerMapper;

    private static Logger logger = LoggerFactory.getLogger(TrainService.class);

    @Autowired
    public void setTrainWayDAO(TrainWayDAO trainWayDAO) {
        this.trainWayDAO = trainWayDAO;
    }

    @Autowired
    public void setFreeSeatsDAO(FreeSeatsDAO freeSeatsDAO) {
        this.freeSeatsDAO = freeSeatsDAO;
    }

    @Autowired
    public void setPassengerMapper(PassengerMapper passengerMapper) {
        this.passengerMapper = passengerMapper;
    }

    @Autowired
    public void setPassengerDAO(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    @Autowired
    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

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

    public int getCountOfPage() {
        return trainDAO.getCountOfPage();
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

    public Train getTrainByDate (List<Train> trains, SearchStations searchStationDTO, int trainID) {
        Train currentTrain = new Train();
        for (Train train: trains){
            if ((train.getDepartureDate().isEqual(searchStationDTO.getDepartureDate()) && train.getId()==trainID)) {
                currentTrain = train;
            }
        }
        return currentTrain;
    }


    public void addTicketInfByTrainId (int idTrain) {
        List<Train> trains = trainDAO.allTrain();
        int idCurrentPassenger = ((PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        SearchStations searchStation = searchStationDAO.getById(idCurrentPassenger);

        Train trainDTO = getTrainByDate(trains,searchStation,idTrain);
        List<TrainWay> trainWays = trainDAO.getTrainWaysForTrain();

        TicketInform ticketInform = ticketInformDAO.fullInformation(searchStation,trainWays,trainDTO);
        ticketInformDAO.add(ticketInform);
        searchStationDAO.delete(searchStation);
    }

    public void deleteIfNoPassengerInTrain() {
        trainDAO.deleteIfNoPassengerInTrain();
    }



    public PassengerDTO сheckPassengerByNameSurnameBirthday(String name, String surname, Date birthday) throws NullPointerException {
        boolean isValid = true;
        int idCurrentPassenger = ((PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        TicketInform ticketInform = ticketInformDAO.getById(idCurrentPassenger);

        Train train = trainDAO.getById(ticketInform.getIdTrain());
        isValid = ticketDAO.checkTicketByNameSurnameBirthday(name,surname,birthday,train);

        // добавление пассажира: если такой пассажир не зарегистрирован, то добавляем, если есть то вытаскиваем о нем
        // информацию из БД
        if (passengerDAO.getPassengerId(name,surname,birthday)<0) {
            passengerDAO.addPassengerByNameSurnameDate(name, surname,birthday);
            logger.info("Add passenger by name "+name+" surname "+surname+" birthday "+birthday);
        }
        Passenger passenger = passengerDAO.getById(passengerDAO.getPassengerId(name, surname, birthday));
        logger.info("Get passenger by id "+passenger);


        // заполняем информаци о пассажире в общую информационну таблицу о билете
        ticketInformDAO.addPassengerInformationToTicket(name,surname,birthday,passenger.getId());

        // проверка покупки билета за 10 минут до отправления поезда
        LocalTime depTime10minutes = ticketInform.getDepartureTime().toLocalTime().minusMinutes(10);
        LocalTime depTime = ticketInform.getDepartureTime().toLocalTime();
        LocalTime currentTime = new Time(System.currentTimeMillis()).toLocalTime();
        if(currentTime.isAfter(depTime10minutes)&&currentTime.isBefore(depTime)) {
            isValid = false;
        }

        if(isValid){
            return passengerMapper.mapEntityToDto(passenger);
        } else throw new NullPointerException();

    }



    public List<FreeSeats>  addInfAboutFreeSeatsIntoTable(Train train) {

        //находим все станции по маршруту поезда
        List<TrainWay> trainOneWAy = new ArrayList<>();
        List<TrainWay> trainWays = trainWayDAO.getAllWays();

        for (TrainWay trainWay: trainWays){
            if (trainWay.getNumberWay()==train.getTrainWay().getNumberWay())
                trainOneWAy.add(trainWay);
        }

        //сортировка полученного расписания для одной станции по времени отправления
        List<TrainWay> sortedTrainWay= trainOneWAy.stream()
                .sorted(Comparator.comparing(TrainWay::getDaysInWay)
                        .thenComparing(TrainWay::getDepartureTime))
                .collect(Collectors.toList());

        //добавляем данные о поезде в таблицу free_sets
        List<FreeSeats> freeSeats = freeSeatsDAO.getAllSeats();
        List<FreeSeats> freeSeatInWay = new ArrayList<>();
        for (FreeSeats freeSeat: freeSeats){
            for(TrainWay trainWay: sortedTrainWay) {
                if (freeSeat.getIdTrain() == train.getId()
                        && freeSeat.getStationName().equals(trainWay.getStation().getNameStation())) {
                    freeSeatInWay.add(freeSeat);
                }
            }
        }

        // если в таблице нет информации о свободных местах то заполняем ее
        if (freeSeatInWay.isEmpty()) {
            for (TrainWay trainWay: sortedTrainWay) {
                FreeSeats seatsDTO = new FreeSeats();
                seatsDTO.setStationName(trainWay.getStation().getNameStation());
                seatsDTO.setIdTrain(train.getId());
                seatsDTO.setFreeSeats(train.getCountSits());
                freeSeatsDAO.add(seatsDTO);
                freeSeatInWay.add(seatsDTO);
            }
        }
        return freeSeatInWay;
    }



    public boolean checkFreeSeatsInDepartureStation(List<FreeSeats> sortedFreeSeatInWay, TicketInform ticketInform) {
        boolean isFreeSeats = true;
        for (FreeSeats freeSeats1: sortedFreeSeatInWay) {

            // поиск станции отправления и проверка наличия мест
            if(freeSeats1.getStationName().equals(ticketInform.getDepartureStation())) {
                if(freeSeats1.getFreeSeats()>0) {
                    int free = freeSeats1.getFreeSeats() - 1;
                    freeSeats1.setFreeSeats(free);
                    freeSeatsDAO.edit(freeSeats1);
                } else {
                    isFreeSeats = false;
                }
            }
        }
        return isFreeSeats;
    }


    public void checkFreeSeatsInTrain () {
        int idCurrentPassenger = ((PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        TicketInform ticketInform = ticketInformDAO.getById(idCurrentPassenger);
        Train train = trainDAO.getById(ticketInform.getIdTrain());

        List<FreeSeats> freeSeatInWay = addInfAboutFreeSeatsIntoTable(train);

        List<FreeSeats> sortedFreeSeatInWay = freeSeatInWay.stream()
                .sorted(Comparator.comparing(FreeSeats::getId))
                .collect(Collectors.toList());

        if(!checkFreeSeatsInDepartureStation(sortedFreeSeatInWay,ticketInform)) {
            throw  new NullPointerException();
        }


        // добавление билетов (уменьшение числа свободных мест)
        // получаем записи из таблицы Free Seats о станции отправления и прибытия
        FreeSeats freeSeatsDeparture  = freeSeatsDAO.getByStationAndTrainID(ticketInform.getDepartureStation(),ticketInform.getIdTrain());
        FreeSeats freeSeatsArrival = freeSeatsDAO.getByStationAndTrainID(ticketInform.getArrivalStation(),ticketInform.getIdTrain());

        if(((freeSeatsDeparture.getId()+1)!=freeSeatsArrival.getId())) {

            int depNumber =  sortedFreeSeatInWay.indexOf(freeSeatsDeparture);
            int arrNumber = sortedFreeSeatInWay.indexOf(freeSeatsArrival);

            for (int j = depNumber+1; j<arrNumber;j++ ) {
                //проверка наличия мест на промежуточных станциях
                //если хотя бы на одной промежуточной станции нет мест, то false,
                // если есть места, то true
                if(sortedFreeSeatInWay.get(j).getFreeSeats()>0) {
                    sortedFreeSeatInWay.get(j).setFreeSeats(sortedFreeSeatInWay.get(j).getFreeSeats()-1);
                    freeSeatsDAO.edit(sortedFreeSeatInWay.get(j));
                } else {
                    throw new NullPointerException();
                }
            }
        }
    }


}

