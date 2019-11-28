package com.trains.service;


import com.trains.model.dto.SearchStationDTO;
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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class SearchStationServiceTest {
    private SearchStationDTO searchStationDTO;
    private TrainWay trainWay;
    private TrainFromStationAToB trainFromStationAToB;

    @Mock
    private SearchStationService searchStationService;

    @Before
    public void initSearchStation(){
        searchStationDTO = new SearchStationDTO();
        Date date = new Date(12-12-1995);
        searchStationDTO.setEndTime(LocalTime.parse("00:00"));
        searchStationDTO.setArrivalStation("Piter");
        searchStationDTO.setDepartureStation("Moscow");
        searchStationDTO.setId(1);
        searchStationDTO.setStartTime(LocalTime.parse("00:00"));
        searchStationDTO.setDepartureDate(date);

        trainWay = new TrainWay();
        trainWay.setDaysInWay(1);
        trainWay.setId(1);
        trainWay.setDepartureTime(Time.valueOf("12:12:00"));
        trainWay.setStation(new Station());
        trainWay.setArrivalTime(Time.valueOf("13:13:00"));
        trainWay.setTrains(new ArrayList<>());

        trainFromStationAToB = new TrainFromStationAToB();
        trainFromStationAToB.setTrainID(1);
        trainFromStationAToB.setArrivalStation("piter");
        trainFromStationAToB.setDeprtureStation("moscow");
        trainFromStationAToB.setCountFreeSits(100);
        trainFromStationAToB.setArrivalTime(LocalTime.of(12,12));
        trainFromStationAToB.setDepartureTime(LocalTime.of(13,13));
    }


    @Test
    public void allTrains() {
        List<SearchStationDTO> actual = new ArrayList<>();
        actual.add(searchStationDTO);
        Mockito.when(searchStationService.getAllStations()).thenReturn(actual);
    }

    @Test
    public void add() {
        Mockito.doNothing().when(searchStationService).add(searchStationDTO);
    }

    @Test
    public void delete() {
        Mockito.doNothing().when(searchStationService).delete(searchStationDTO);
    }

    @Test
    public void edit() {
        Mockito.doNothing().when(searchStationService).edit(searchStationDTO);
    }

    @Test
    public void getById() {
        Mockito.when(searchStationService.getById(1)).thenReturn(searchStationDTO);
    }

    @Test
    public void delByID() {
        Mockito.doNothing().when(searchStationService).delByID(1);
    }

    @Test
    public void getTrainWaysFromStationAToB() {
        List<TrainWay> actual = new ArrayList<>();
        actual.add(trainWay);
        Mockito.when(searchStationService.getTrainWaysFromStationAToB(trainWay.getStation().getNameStation(),trainWay.getStation().getNameStation())).thenReturn(actual);
    }

    @Test
    public void getTrainsWithCorrectDate() {
        List<TrainFromStationAToB> actual = new ArrayList<>();
        actual.add(trainFromStationAToB);
        Mockito.when(searchStationService.getTrainsWithCorrectDate(new ArrayList<>(),new ArrayList<>(),trainFromStationAToB.getDeprtureStation(),
                trainFromStationAToB.getArrivalStation(), LocalDate.now())).thenReturn(actual);
    }

    @Test
    public void getTrainsFromStations() {
        List<TrainFromStationAToB> actual = new ArrayList<>();
        actual.add(trainFromStationAToB);
        Mockito.when(searchStationService.getTrainsFromStations(trainFromStationAToB.getDeprtureStation(),
                trainFromStationAToB.getArrivalStation(),trainFromStationAToB.getDepartureTime(),trainFromStationAToB.getArrivalTime(),LocalDate.now())).thenReturn(actual);
    }

    @Test
    public void addInformationAboutSearch() {
        Mockito.doNothing().when(searchStationService).addInformationAboutSearch("piter","moscow",LocalDate.now(),"12:12:12","13:13:13");
    }

    @Test
    public void addTrainBySchedule() {
        Mockito.doNothing().when(searchStationService).addTrainBySchedule(LocalDate.now());
    }
}
