package com.trains.dao;


import com.trains.model.entity.TrainFromStation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TrainFromStationDAOTest {
    private TrainFromStation trainFromStation;


    @Mock
    private TrainFromStationDAO trainFromStationDAO;

    @Before
    public void init() {
        trainFromStation = new TrainFromStation();
        trainFromStation.setNameStation("piter");
        trainFromStation.setDepartureTime(LocalTime.of(12,11));
        trainFromStation.setArrivalTime(LocalTime.of(13,13));
        trainFromStation.setIdTrain(1);
        trainFromStation.setDaysInWay(1);
    }


    @Test
    public void getAllTrain() {
        List<TrainFromStation> actual = new ArrayList<>();
        actual.add(trainFromStation);
        Mockito.when(trainFromStationDAO.getAllTrain()).thenReturn(actual);
    }

    @Test
    public void deleteByTrainNumber() {
        Mockito.doNothing().when(trainFromStationDAO).deleteByTrainNumber(1);
    }
}
