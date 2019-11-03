package com.trains.dao;


import com.trains.model.dto.PassengerDTO;
import com.trains.model.dto.TicketDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TicketDAOTest {
    private Ticket ticket;
    private Train train;
    private Passenger passenger;

    @Mock
    private TicketDAO ticketDAO;

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

        ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setTrain(train);
        ticket.setId(1);
    }


    @Test
    public void allTickets() {
        List<Ticket> actual = new ArrayList<>();
        actual.add(ticket);
        Mockito.when(ticketDAO.allTickets()).thenReturn(actual);
    }

    @Test
    public void getById() {
        Mockito.when(ticketDAO.getById(1)).thenReturn(ticket);
    }

    @Test
    public void delByID() {
        Mockito.doNothing().when(ticketDAO).delByID(1);
    }

    @Test
    public void getByTrain() {
        List<Ticket> actual = new ArrayList<>();
        actual.add(ticket);
        Mockito.when(ticketDAO.getByTrain(train)).thenReturn(actual);
    }

    @Test
    public void addTicketByTrainDTOPassengerDTO() {
        Mockito.doNothing().when(ticketDAO).addTicketByTrainDTOPassengerDTO(train,passenger);
    }
}
