package com.trains.service;

import com.trains.model.dto.PassengerDTO;
import com.trains.model.dto.TicketDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.entity.Passenger;
import com.trains.model.entity.Station;
import com.trains.model.entity.Train;
import com.trains.model.entity.TrainWay;
import com.trains.util.mapperForDTO.PassengerMapper;
import com.trains.util.mapperForDTO.TrainMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TicketServiceTest {
    private TicketDTO ticketDTO;
    private Train train;
    private Passenger passenger;
//    private TrainDTO trainDTO;
//    private PassengerDTO passengerDTO;

    @Mock
    private TicketService ticketService;

    @Before
    public void initTicket() {
        passenger = new Passenger();
        passenger.setName("bob");
        passenger.setSurname("smith");
        passenger.setPassword("1234");
        passenger.setLogin("bob");
        passenger.setId(1);
        passenger.setEmail("bob@mail.ru");
        passenger.setBirthday(LocalDate.of(2000,11,23));
        passenger.setUser("passenger");

        Station station = new Station();
        station.setId(1);
        station.setNameStation("Rostov");

        TrainWay trainWay = new TrainWay();
        trainWay.setNumberWay(1);
        trainWay.setStation(station);
        trainWay.setId(1);
        trainWay.setDaysInWay(1);
        trainWay.setArrivalTime(Time.valueOf("23:00:00"));
        trainWay.setDepartureTime(Time.valueOf("22:00:00"));

        train = new Train();
        train.setDepartureDate(LocalDate.of(2019,11,10));
        train.setTrainWay(trainWay);
        train.setTrainNumber(1);
        train.setCountSits(800);

        ticketDTO = new TicketDTO();
        ticketDTO.setPassenger(passenger);
        ticketDTO.setTrain(train);
        ticketDTO.setId(1);
        System.out.println();
    }

    @Test
    public void allTickets() {
        List<TicketDTO> actual = new ArrayList<>();
        actual.add(ticketDTO);
        Mockito.when(ticketService.allTickets()).thenReturn(actual);
    }

    @Test
    public void add() {
        Mockito.doNothing().when(ticketService).add(ticketDTO);
    }

    @Test
    public void delete() {
        Mockito.doNothing().when(ticketService).delete(ticketDTO);
    }

    @Test
    public void edit() {
        Mockito.doNothing().when(ticketService).edit(ticketDTO);
    }

    @Test
    public void getById() {
        Mockito.when(ticketService.getById(1)).thenReturn(ticketDTO);
    }

    @Test
    public void delByID() {
        Mockito.doNothing().when(ticketService).delByID(1);
    }

    @Test
    public void getByTrain() {
        List<TicketDTO> actual = new ArrayList<>();
        actual.add(ticketDTO);
        Mockito.when(ticketService.getByTrain(train)).thenReturn(actual);
    }

    @Test
    public void addTicketByTrainDTOPassengerDTO() {
        TrainDTO trainDTO = new TrainDTO();
        PassengerDTO passengerDTO = new PassengerDTO();
        Mockito.doNothing().when(ticketService).addTicketByTrainDTOPassengerDTO(trainDTO,passengerDTO);
    }
}
