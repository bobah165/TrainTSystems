package com.trains.dao;

import com.trains.model.entity.Station;
import com.trains.model.entity.TrainWay;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TrainWayDAOTest {
    private TrainWay trainWay;
    private Station station;

    @Mock
    private TrainWayDAO trainWayDAO;

    @Before
    public void init() {
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
    }


    @Test
    public void allWays() {
        List<TrainWay> actual = new ArrayList<>();
        actual.add(trainWay);
        Mockito.when(trainWayDAO.getAllWays()).thenReturn(actual);
    }

    @Test
    public void delByID() {
        Mockito.doNothing().when(trainWayDAO).delByID(1);

    }

    @Test
    public void getById() {
        Mockito.when(trainWayDAO.getById(1)).thenReturn(trainWay);
    }
}
