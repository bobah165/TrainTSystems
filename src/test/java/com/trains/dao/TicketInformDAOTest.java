package com.trains.dao;


import com.trains.model.dto.SearchStationDTO;
import com.trains.model.dto.TicketInformDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.entity.Station;
import com.trains.model.entity.TicketInform;
import com.trains.model.entity.TrainWay;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TicketInformDAOTest {
    private TicketInform ticketInform;
    private SearchStationDTO searchStationDTO;
    private TrainDTO trainDTO;
    private Station station;
    private TrainWay trainWay;

    @Mock
    private TicketInformDAO ticketInformDAO;

    @Before
    public void initTicketInform() {
        ticketInform = new TicketInform();
        Date date = new Date(01-01-1987);

        ticketInform.setIdPassenger(1);
        ticketInform.setId(1);
        ticketInform.setBirthday(date.toLocalDate());
        ticketInform.setSurname("smith");
        ticketInform.setName("bob");
        ticketInform.setArrivalDate(date.toLocalDate());
        ticketInform.setDepartureTime(Time.valueOf("12:12:12"));
        ticketInform.setDepartureStation("Piter");
        ticketInform.setIdTrain(1);
        ticketInform.setDepartureDate(date.toLocalDate());
        ticketInform.setArrivalTime(Time.valueOf("13:13:13"));
        ticketInform.setArrivalStation("Moscow");

        station = new Station();
        station.setNameStation("piter");
        station.setId(1);
        station.setTrainWays(new ArrayList<>());

        trainWay = new TrainWay();
        trainWay.setDaysInWay(1);
        trainWay.setId(1);
        trainWay.setArrivalTime(Time.valueOf("12:12:00"));
        trainWay.setStation(station);
        trainWay.setDepartureTime(Time.valueOf("13:13:00"));
        trainWay.setTrains(new ArrayList<>());

        searchStationDTO = new SearchStationDTO();
        searchStationDTO.setDepartureDate(date);
        searchStationDTO.setStartTime("12:12:12");
        searchStationDTO.setId(1);
        searchStationDTO.setDepartureStation("piter");
        searchStationDTO.setArrivalStation("moscow");
        searchStationDTO.setEndTime("14:13:13");

        trainDTO = new TrainDTO();
        trainDTO.setId(1);
        trainDTO.setTickets(new ArrayList<>());
        trainDTO.setCountSits(800);
        trainDTO.setTrainNumber(1);
        trainDTO.setDepartureDate(date);
        trainDTO.setTrainWay(trainWay);
    }

    @Test
    public void allTickets() {
        List<TicketInform> actual = new ArrayList<>();
        actual.add(ticketInform);
        Mockito.when(ticketInformDAO.allTickets()).thenReturn(actual);
    }

    @Test
    public void getById() {
        Mockito.when(ticketInformDAO.getById(1)).thenReturn(ticketInform);
    }

    @Test
    public void delByID() {
        Mockito.doNothing().when(ticketInformDAO).delByID(1);
    }
}
