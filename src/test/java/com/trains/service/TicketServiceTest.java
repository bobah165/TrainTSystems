package com.trains.service;

import com.trains.dao.TicketDAO;
import com.trains.model.dto.PassengerDTO;
import com.trains.model.dto.TicketDTO;
import com.trains.model.entity.*;
import com.trains.util.mapperForDTO.TicketMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TicketServiceTest {
    private TicketDTO ticketDTO;
    private Ticket ticket;
    private Train train;
    private Passenger passenger;

    @Mock
    private TicketDAO ticketDAO;

    @Mock
    private TicketMapper ticketMapper;

    @InjectMocks
    private TicketService ticketService;

    @Before
    public void initTicket() {
        MockitoAnnotations.initMocks(this);

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

        ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setTrain(train);
        ticket.setId(1);
    }


    @Test
    public void allTickets() {
        List<TicketDTO> actualDTO = new ArrayList<>();
        actualDTO.add(ticketDTO);

        List<Ticket> actual = new ArrayList<>();
        actual.add(ticket);

        Mockito.when(ticketDAO.allTickets()).thenReturn(actual);
        Mockito.when(ticketMapper.mapEntityToDto(ticket)).thenReturn(ticketDTO);
        Assertions.assertEquals(ticketService.allTickets(),actualDTO);
    }

    @Test
    public void add() {
        ticketService.add(any(TicketDTO.class));
        Mockito.verify(ticketDAO,Mockito.atLeastOnce()).add(any(TicketDTO.class));
    }

    @Test
    public void delete() {
        ticketService.delete(any(TicketDTO.class));
        Mockito.verify(ticketDAO,Mockito.atLeastOnce()).delete(any(TicketDTO.class));
    }

    @Test
    public void edit() {
        ticketService.edit(any(TicketDTO.class));
        Mockito.verify(ticketDAO,Mockito.atLeastOnce()).edit(any(TicketDTO.class));
    }

    @Test
    public void getById() {
        Mockito.when(ticketDAO.getById(1)).thenReturn(ticket);
        Mockito.when(ticketMapper.mapEntityToDto(any(Ticket.class))).thenReturn(ticketDTO);
        Assertions.assertEquals(ticketService.getById(1),ticketDTO);
    }

    @Test
    public void delByID() {
        ticketService.delByID(1);
        Mockito.verify(ticketDAO,Mockito.atLeastOnce()).delByID(1);
    }
}
