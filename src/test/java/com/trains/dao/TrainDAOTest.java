package com.trains.dao;


import com.trains.model.dto.PassengersFromTrainDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.dto.TrainFromStationAToB;
import com.trains.model.entity.Station;
import com.trains.model.entity.Train;
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
public class TrainDAOTest {
    private Train train;
    private TrainWay trainWay;
    private Station station;
    private PassengersFromTrainDTO passengersFromTrainDTO;
    private TrainFromStationAToB trainFromStationAToB;

    @Mock
    private TrainDAO trainDAO;

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


        train = new Train();
        Date date = new Date(11-11-2019);
        train.setDepartureDate(date.toLocalDate());
        train.setTrainWay(trainWay);
        train.setTrainNumber(1);
        train.setCountSits(600);
        train.setTickets(new ArrayList<>());
        train.setId(1);

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
    public void allTrain() {
        List<Train> actual = new ArrayList<>();
        actual.add(train);
        Mockito.when(trainDAO.allTrain()).thenReturn(actual);
    }

    @Test
    public void getById() {
        Mockito.when(trainDAO.getById(1)).thenReturn(train);
    }

    @Test
    public void delByID() {
        Mockito.doNothing().when(trainDAO).delByID(1);
    }

    @Test
    public void getPassengerFromTrain() {
        List<PassengersFromTrainDTO> actual = new ArrayList<>();
        actual.add(passengersFromTrainDTO);
        Mockito.when(trainDAO.getPassengerFromTrain(1)).thenReturn(actual);
    }

    @Test
    public void getTrainsFromStations() {
        List<TrainFromStationAToB> actual = new ArrayList<>();
        actual.add(trainFromStationAToB);
        Mockito.when(trainDAO.getTrainsFromStations(trainFromStationAToB.getDeprtureStation(),
                trainFromStationAToB.getArrivalStation(),trainFromStationAToB.getDepartureTime(),
                trainFromStationAToB.getArrivalTime(),LocalDate.of(2019,10,25))).thenReturn(actual);

    }
}
