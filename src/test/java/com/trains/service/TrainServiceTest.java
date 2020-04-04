package com.trains.service;


import com.trains.dao.TrainDAO;
import com.trains.model.dto.PassengerDTO;
import com.trains.model.dto.PassengersFromTrainDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.dto.TrainFromStationAToB;
import com.trains.model.entity.*;
import com.trains.util.mapperForDTO.PassengerMapper;
import com.trains.util.mapperForDTO.TrainMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TrainServiceTest {
    @Mock
    private TrainDAO trainDAO;

    @Mock
    private TrainMapper mapper;

    private TrainDTO trainDTO;
    private TrainWay trainWay;
    private Station station;
    private PassengersFromTrainDTO passengersFromTrainDTO;
    private TrainFromStationAToB trainFromStationAToB;
    private FreeSeats freeSeats;
    private Train train;

    private PassengerDTO passengerDTO;

    @InjectMocks
    private TrainService trainService;

    @Before
    public void initTrain() {
        MockitoAnnotations.initMocks(this);

        station = new Station();
        station.setId(1);
        station.setNameStation("Piter");

        trainWay = new TrainWay();
        trainWay.setTrains(new ArrayList<>());
        trainWay.setNumberWay(1);
        trainWay.setDepartureTime(Time.valueOf("12:30:00"));
        trainWay.setStation(station);
        trainWay.setArrivalTime(Time.valueOf("13:13:00"));
        trainWay.setId(1);
        trainWay.setDaysInWay(1);


        trainDTO = new TrainDTO();
        Date date = new Date(11-11-2019);
        trainDTO.setDepartureDate(date);
        trainDTO.setTrainWay(trainWay);
        trainDTO.setTrainNumber(1);
        trainDTO.setCountSits(600);
        trainDTO.setTickets(new ArrayList<>());
        trainDTO.setId(1);
        TrainMapper trainMapper = new TrainMapper();
        train = trainMapper.mapDtoToEntity(trainDTO);

        passengerDTO = new PassengerDTO();
        passengerDTO.setTickets(new ArrayList<>());
        passengerDTO.setUser("passenger");
        passengerDTO.setId(1);
        passengerDTO.setBirthday(date);
        passengerDTO.setName("alex");
        passengerDTO.setLogin("1234");
        passengerDTO.setEmail("alex@mail.ru");

        passengersFromTrainDTO = new PassengersFromTrainDTO();
        LocalDate localDate = LocalDate.of(2019,10,25);
        passengersFromTrainDTO.setSurname("none");
        passengersFromTrainDTO.setName("none");
        passengersFromTrainDTO.setTicketID(1);
        passengersFromTrainDTO.setBirthday(localDate);

        trainFromStationAToB = new TrainFromStationAToB();
        trainFromStationAToB.setCountFreeSits(200);
        trainFromStationAToB.setArrivalTime(LocalTime.parse("00:00"));
        trainFromStationAToB.setDepartureTime(LocalTime.parse("00:00"));
        trainFromStationAToB.setDeprtureStation("piter");
        trainFromStationAToB.setArrivalStation("moscow");
        trainFromStationAToB.setTrainID(1);

        freeSeats = new FreeSeats();
        freeSeats.setFreeSeats(1);
        freeSeats.setIdTrain(1);
        freeSeats.setStationName("Piter");

    }



    @Test
    public void allTrains() {
        List<TrainDTO> actualDTO = new ArrayList<>();
        actualDTO.add(trainDTO);

        List<Train> actual = new ArrayList<>();
        actual.add(train);

        Mockito.when(trainDAO.allTrain()).thenReturn(actual);
        Mockito.when(mapper.mapEntityToDto(train)).thenReturn(trainDTO);
        Assertions.assertEquals(trainService.allTrains(),actualDTO);
    }

    @Test
    public void add() {
        Mockito.when(mapper.mapDtoToEntity(trainDTO)).thenReturn(train);
        trainService.add(trainDTO);
        Mockito.verify(trainDAO,Mockito.atLeastOnce()).add(train);
    }

    @Test
    public void delete() {
        Mockito.when(mapper.mapDtoToEntity(trainDTO)).thenReturn(train);
        trainService.delete(trainDTO);
        Mockito.verify(trainDAO,Mockito.atLeastOnce()).delete(train);
    }

    @Test
    public void edit() {
        Mockito.when(mapper.mapDtoToEntity(trainDTO)).thenReturn(train);
        trainService.edit(trainDTO);
        Mockito.verify(trainDAO,Mockito.atLeastOnce()).edit(train);
    }

    @Test
    public void getById() {
        Mockito.when(trainDAO.getById(1)).thenReturn(train);
        Mockito.when(mapper.mapEntityToDto(train)).thenReturn(trainDTO);
        Assertions.assertEquals(trainService.getById(1),trainDTO);

        Mockito.when(trainService.getById(1)).thenReturn(trainDTO);
    }

    @Test
    public void delByID() {
        Mockito.when(mapper.mapDtoToEntity(trainDTO)).thenReturn(train);
        Mockito.when(trainDAO.getById(1)).thenReturn(train);
        trainService.delByID(1);
        Mockito.verify(trainDAO,Mockito.atLeastOnce()).delByID(1);
    }

    @Test
    public void getPassengerFromTrain() {
        Ticket ticket = new Ticket();
        List<Ticket> tickets = new ArrayList<>();
        PassengerMapper passengerMapper = new PassengerMapper();
        ticket.setPassenger(passengerMapper.mapDtoToEntity(passengerDTO));
        tickets.add(ticket);

        Mockito.when(trainDAO.getPassengerFromTrain(1)).thenReturn(tickets);
        for (PassengersFromTrainDTO pass : trainService.getPassengerFromTrain(1))
        {
            Assertions.assertEquals(pass.getName(),passengerDTO.getName());
        }
    }
//
//
//    @Test
//    public void allTrainsPagination() {
//        List<TrainDTO> actual = new ArrayList<>();
//        actual.add(trainDTO);
//        Mockito.when(trainService.allTrainsPagination(1)).thenReturn(actual);
//    }
//
//    @Test
//    public void getCountOfPage(){
//        Mockito.when(trainService.getCountOfPage()).thenReturn(1);
//    }


//    @Test
//    public void addInformatonInTicket() {
//        Mockito.doNothing().when(trainService).addInformatonInTicket(train.getId(),trainWay.getStation().getNameStation(),
//                "Moscow",trainWay.getArrivalTime().toLocalTime(),trainWay.getDepartureTime().toLocalTime());
//    }
//
//    @Test
//    public void deleteIfNoPassengerInTrain() {
//        Mockito.doNothing().when(trainService).deleteIfNoPassengerInTrain();
//    }
//
//    @Test
//    public void checkPassengerByNameSurnameBirthday() {
//        Mockito.when(trainService.checkPassengerByNameSurnameBirthday(passengerDTO.getName(),
//                passengerDTO.getSurname(),passengerDTO.getBirthday())).thenReturn(passengerDTO);
//    }
//
//    @Test
//    public void addInfAboutFreeSeatsIntoTable() {
//        List<FreeSeats> actual = new ArrayList<>();
//        actual.add(freeSeats);
//        Mockito.when(trainService.addInfAboutFreeSeatsIntoTable(train)).thenReturn(actual);
//    }
//
//    @Test
//    public void checkFreeSeatsInDepartureStation() {
//        Mockito.when(trainService.checkFreeSeatsInDepartureStation(new ArrayList<>(),new TicketInform())).thenReturn(true);
//    }
//
//    @Test
//    public void checkFreeSeatsInTrain() {
//        Mockito.doNothing().when(trainService).checkFreeSeatsInTrain();
//    }
//


}
