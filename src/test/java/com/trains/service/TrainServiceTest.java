package com.trains.service;


import com.trains.model.dto.PassengersFromTrainDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.dto.TrainFromStationAToB;
import com.trains.model.entity.Station;
import com.trains.model.entity.TrainWay;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TrainServiceTest {
    private TrainDTO trainDTO;
    private TrainWay trainWay;
    private Station station;
    private PassengersFromTrainDTO passengersFromTrainDTO;
    private TrainFromStationAToB trainFromStationAToB;

    @Mock
    private TrainService trainService;

    @Before
    public void initTrain() {
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

        passengersFromTrainDTO = new PassengersFromTrainDTO();
        LocalDate localDate = LocalDate.of(2019,10,25);
        passengersFromTrainDTO.setSurname("none");
        passengersFromTrainDTO.setName("none");
        passengersFromTrainDTO.setTicketID(1);
        passengersFromTrainDTO.setBirthday(localDate);

        trainFromStationAToB = new TrainFromStationAToB();
        trainFromStationAToB.setCountFreeSits(200);
        trainFromStationAToB.setArrivalTime(Time.valueOf("00:00:00"));
        trainFromStationAToB.setDepartureTime(Time.valueOf("00:00:00"));
        trainFromStationAToB.setDeprtureStation("piter");
        trainFromStationAToB.setArrivalStation("moscow");
        trainFromStationAToB.setTrainID(1);
    }



    @Test
    public void allStations() {
        List<TrainDTO> actual = new ArrayList<>();
        actual.add(trainDTO);
        Mockito.when(trainService.allTrains()).thenReturn(actual);
    }

    @Test
    public void add() {
        Mockito.doNothing().when(trainService).add(trainDTO);
    }

    @Test
    public void delete() {
        Mockito.doNothing().when(trainService).delete(trainDTO);
    }

    @Test
    public void edit() {
        Mockito.doNothing().when(trainService).edit(trainDTO);
    }

    @Test
    public void getById() {
        Mockito.when(trainService.getById(1)).thenReturn(trainDTO);
    }

    @Test
    public void delByID() {
        Mockito.doNothing().when(trainService).delByID(1);
    }

    @Test
    public void getPassengerFromTrain() {
        List<PassengersFromTrainDTO> actual = new ArrayList<>();
        actual.add(passengersFromTrainDTO);
        Mockito.when(trainService.getPassengerFromTrain(1)).thenReturn(actual);


    }

    @Test
    public void getTrainsFromStations() {
        List<TrainFromStationAToB> actual = new ArrayList<>();
        actual.add(trainFromStationAToB);
        Mockito.when(trainService.getTrainsFromStations(trainFromStationAToB.getDeprtureStation(),
                trainFromStationAToB.getArrivalStation(),trainFromStationAToB.getDepartureTime(),
                trainFromStationAToB.getArrivalTime(),LocalDate.of(2019,10,25))).thenReturn(actual);

    }

    @Test
    public void getDateOfStation() {
        Mockito.when(trainService.getDateOfStation(1,"piter")).thenReturn("12-12-2019");
    }
}
