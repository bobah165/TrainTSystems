package com.trains.service;


import com.trains.model.dto.TrainFromStationDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TrainFromStationServiceTest {
    private TrainFromStationDTO trainFromStationDTO;

    @Before
    public void init() {
        trainFromStationDTO = new TrainFromStationDTO();
        trainFromStationDTO.setNameStation("Piter");
        trainFromStationDTO.setIdTrain(1);
        trainFromStationDTO.setArrivalTime(LocalTime.of(10,10));
        trainFromStationDTO.setDepartureTime(LocalTime.of(11,11));
        trainFromStationDTO.setDaysInWay(1);
    }


    @Mock
    private TrainFromStationService trainFromStationService;


    @Test
    public void getAllTrains() {
        List<TrainFromStationDTO> actual = new ArrayList<>();
        actual.add(trainFromStationDTO);
        Mockito.when(trainFromStationService.getAllTrains()).thenReturn(actual);
    }

    @Test
    public void add() {
        Mockito.doNothing().when(trainFromStationService).add(trainFromStationDTO);
    }

    @Test
    public void delete() {
        Mockito.doNothing().when(trainFromStationService).delete(trainFromStationDTO);
    }

    @Test
    public void edit() {
        Mockito.doNothing().when(trainFromStationService).edit(trainFromStationDTO);
    }

    @Test
    public void getById() {
        Mockito.when(trainFromStationService.getById(trainFromStationDTO.getId())).thenReturn(trainFromStationDTO);
    }

    @Test
    public void fillTheTable() {
        Mockito.doNothing().when(trainFromStationService).fillTheTable();
    }
}
