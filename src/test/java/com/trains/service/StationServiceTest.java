package com.trains.service;


import com.trains.model.dto.StationDTO;
import com.trains.model.dto.TrainFromStationDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class StationServiceTest {
    private StationDTO stationDTO;
    private TrainFromStationDTO trainFromStationDTO;

    @Mock
    private StationService stationService;

    @Before
    public void initStation(){
        stationDTO = new StationDTO();

        stationDTO.setNameStation("Piter");
        stationDTO.setId(1);

        trainFromStationDTO = new TrainFromStationDTO();
        trainFromStationDTO.setIdTrain(1);
        trainFromStationDTO.setArrivalTime(Time.valueOf("12:12:12"));
        trainFromStationDTO.setDepartureTime(Time.valueOf("13:13:13"));
        trainFromStationDTO.setNameStation("Piter");
        java.sql.Date date  = new java.sql.Date(12-12-2019);
        trainFromStationDTO.setDate(date);
    }


    @Test
    public void allStations() {
        List<StationDTO> actual = new ArrayList<>();
        actual.add(stationDTO);
        Mockito.when(stationService.allStations()).thenReturn(actual);
    }

    @Test
    public void add() {
        Mockito.doNothing().when(stationService).add(stationDTO);
    }

    @Test
    public void delete() {
        Mockito.doNothing().when(stationService).delete(stationDTO);
    }

    @Test
    public void edit() {
        Mockito.doNothing().when(stationService).edit(stationDTO);
    }

    @Test
    public void getById() {
        Mockito.when(stationService.getById(1)).thenReturn(stationDTO);
    }

    @Test
    public void delByID() {
        Mockito.doNothing().when(stationService).delByID(1);
    }

    @Test
    public void getTrainFromStation() {
        List<TrainFromStationDTO> actual = new ArrayList<>();
        actual.add(trainFromStationDTO);
        Mockito.when(stationService.getTrainFromStation(stationDTO.getId(),trainFromStationDTO.getDate(), LocalTime.parse("00:00:00"),LocalTime.parse("23:59:00"))).thenReturn(actual);
    }

    @Test
    public void getByName() {
        Mockito.when(stationService.getByName(stationDTO.getNameStation())).thenReturn(stationDTO);
    }
}
