package com.trains.service;

import com.trains.model.dto.SearchStationDTO;
import com.trains.model.dto.TicketInformDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.entity.Station;
import com.trains.model.entity.TrainWay;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TicketInformServiceTest {
    private TicketInformDTO ticketInformDTO;
    private SearchStationDTO searchStationDTO;
    private TrainDTO trainDTO;
    private Station station;
    private TrainWay trainWay;

    @Mock
    private TicketInformService ticketInformService;

    @Before
    public void initTicketInform() {
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
    }

    @Test
    public void allTickets() {
        List<TicketInformDTO> actual = new ArrayList<>();
        actual.add(ticketInformDTO);
        Mockito.when(ticketInformService.getAllTickets()).thenReturn(actual);
    }

    @Test
    public void add() {
        Mockito.doNothing().when(ticketInformService).add(ticketInformDTO);
    }

    @Test
    public void delete() {
            Mockito.doNothing().when(ticketInformService).delete(ticketInformDTO);
    }

    @Test
    public void edit() {
        Mockito.doNothing().when(ticketInformService).edit(ticketInformDTO);
    }

    @Test
    public void getById() {
        Mockito.when(ticketInformService.getById(1)).thenReturn(ticketInformDTO);
    }

    @Test
    public void delByID() {
        Mockito.doNothing().when(ticketInformService).delByID(1);
    }

//    @Test
//    public void fullInformation(){
//        Mockito.when(ticketInformService.fillInformationByStationAndWay(searchStationDTO, new ArrayList<>(),trainDTO)).thenReturn(ticketInformDTO);
//    }

//    @Test
//    public void checkDeapartureTime() {
//        Mockito.when(ticketInformService.checkLegalTimeForBuyTicket(ticketInformDTO)).thenReturn(true);
//    }
}
