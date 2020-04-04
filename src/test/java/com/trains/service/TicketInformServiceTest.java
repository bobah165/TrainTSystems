package com.trains.service;

import com.trains.dao.TicketInformDAO;
import com.trains.model.dto.SearchStationDTO;
import com.trains.model.dto.TicketInformDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.entity.Station;
import com.trains.model.entity.TicketInform;
import com.trains.model.entity.TrainWay;
import com.trains.util.mapperForDTO.TicketInformMapper;
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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TicketInformServiceTest {
    @Mock
    private TicketInformDAO ticketInformDAO;

    @Mock
    private TicketInformMapper mapper;

    private TicketInformDTO ticketInformDTO;
    private SearchStationDTO searchStationDTO;
    private TrainDTO trainDTO;
    private Station station;
    private TrainWay trainWay;

    private
    TicketInform ticketInform;

    @InjectMocks
    private TicketInformService ticketInformService;

    @Before
    public void initTicketInform() {
        MockitoAnnotations.initMocks(this);

        ticketInformDTO = new TicketInformDTO();
        Date date = new Date(01-01-1987);


        ticketInformDTO.setIdPassenger(1);
        ticketInformDTO.setId(1);
        ticketInformDTO.setBirthday(date);
        ticketInformDTO.setSurname("smith");
        ticketInformDTO.setName("bob");
        ticketInformDTO.setArrivalDate(date);
        ticketInformDTO.setDepartureTime("12:12:12");
        ticketInformDTO.setDepartureStation("Piter");
        ticketInformDTO.setIdTrain(1);
        ticketInformDTO.setDepartureDate(date);
        ticketInformDTO.setArrivalTime("13:13:13");
        ticketInformDTO.setArrivalStation("Moscow");

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
        searchStationDTO.setStartTime(LocalTime.parse("12:12"));
        searchStationDTO.setId(1);
        searchStationDTO.setDepartureStation("piter");
        searchStationDTO.setArrivalStation("moscow");
        searchStationDTO.setEndTime(LocalTime.parse("14:13"));

        trainDTO = new TrainDTO();
        trainDTO.setId(1);
        trainDTO.setTickets(new ArrayList<>());
        trainDTO.setCountSits(800);
        trainDTO.setTrainNumber(1);
        trainDTO.setDepartureDate(date);
        trainDTO.setTrainWay(trainWay);

        ticketInform = mapper.mapDtoToEntity(ticketInformDTO);
    }

    @Test
    public void allTickets() {
        List<TicketInformDTO> actualDTO = new ArrayList<>();
        actualDTO.add(ticketInformDTO);

        List<TicketInform> actual = new ArrayList<>();
        actual.add(ticketInform);

        Mockito.when(ticketInformDAO.getAllTickets()).thenReturn(actual);
        Mockito.when(mapper.mapEntityToDto(ticketInform)).thenReturn(ticketInformDTO);
        Assertions.assertEquals(ticketInformService.getAllTickets(),actualDTO);
    }

    @Test
    public void add() {
        ticketInformService.add(any(TicketInformDTO.class));
        Mockito.verify(ticketInformDAO,Mockito.atLeastOnce()).add(any(TicketInformDTO.class));
    }

    @Test
    public void delete() {
        ticketInformService.delete(any(TicketInformDTO.class));
        Mockito.verify(ticketInformDAO,Mockito.atLeastOnce()).delete(any(TicketInformDTO.class));
    }

    @Test
    public void edit() {
        ticketInformService.edit(any(TicketInformDTO.class));
        Mockito.verify(ticketInformDAO,Mockito.atLeastOnce()).edit(any(TicketInformDTO.class));
    }

    @Test
    public void getById() {
        Mockito.when(ticketInformDAO.getById(1)).thenReturn(ticketInform);
        Mockito.when(mapper.mapEntityToDto(ticketInform)).thenReturn(ticketInformDTO);
        Assertions.assertEquals(ticketInformService.getById(1),ticketInformDTO);
    }

    @Test
    public void delByID() {
        ticketInformService.delByID(anyInt());
        Mockito.verify(ticketInformDAO,Mockito.atLeastOnce()).delByID(anyInt());
    }

}
