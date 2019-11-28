package com.trains.service;


import com.trains.model.dto.StationDTO;
import com.trains.model.dto.TrainFromStationDTO;
import com.trains.model.entity.Station;
import com.trains.model.entity.TrainFromStation;
import com.trains.model.entity.TrainWay;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class StationServiceTest {
    private StationDTO stationDTO;
    private Station station;
    private TrainFromStationDTO trainFromStationDTO;
    private TrainWay trainWay;

    @Mock
    private StationService stationService;

    @Before
    public void initStation(){
        stationDTO = new StationDTO();

        stationDTO.setNameStation("Piter");
        stationDTO.setId(1);

        station = new Station();
        station.setNameStation("Piter");
        station.setId(1);
        station.setTrainWays(new ArrayList<>());

        trainFromStationDTO = new TrainFromStationDTO();
        trainFromStationDTO.setIdTrain(1);
        trainFromStationDTO.setArrivalTime(LocalTime.of(12,12));
        trainFromStationDTO.setDepartureTime(LocalTime.of(12,12));
        trainFromStationDTO.setNameStation("Piter");
        LocalDate date  = LocalDate.of(2019,12,12);

        trainWay = new TrainWay();
        trainWay.setDepartureTime(Time.valueOf("12:12:12"));
        trainWay.setArrivalTime(Time.valueOf("12:12:12"));
        trainWay.setDaysInWay(1);
        trainWay.setId(1);
        trainWay.setStation(station);
        trainWay.setNumberWay(1);
        trainWay.setTrains(new ArrayList<>());
    }


    @Test
    public void allStations() {
        List<StationDTO> actual = new ArrayList<>();
        actual.add(stationDTO);
        Mockito.when(stationService.getAllStations()).thenReturn(actual);
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
    public void getByName() {
        Mockito.when(stationService.getByName(stationDTO.getNameStation())).thenReturn(stationDTO);
    }

    @Test
    public void getStationsForPagination() {
        List<StationDTO> actual = new ArrayList<>();
        actual.add(stationDTO);
        Mockito.when(stationService.getStationsForPagination(1)).thenReturn(actual);
    }

    @Test
    public void getCountStationsForPagination() {
        Mockito.when(stationService.getCountStationsForPagination()).thenReturn(1);
    }

    @Test
    public void getTrainFromStation() {
        List<TrainFromStationDTO> actual = new ArrayList<>();
        actual.add(trainFromStationDTO);
        Mockito.when(stationService.getTrainFromStation(trainFromStationDTO.getId(),LocalDate.of(1990,12,12),
                trainFromStationDTO.getArrivalTime(),trainFromStationDTO.getDepartureTime())).thenReturn(actual);
    }

    @Test
    public void getTrainsFromStationInDate() {
        List<TrainFromStationDTO> actual = new ArrayList<>();
        actual.add(trainFromStationDTO);
        Mockito.when(stationService.getTrainsFromStationInDate(LocalDate.of(1990,12,12),
                station,actual,trainWay)).thenReturn(actual);
    }


}
