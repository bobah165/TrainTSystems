package com.trains.service;


import com.trains.dao.SearchStationDAO;
import com.trains.dao.TrainWayDAO;
import com.trains.model.dto.SearchStationDTO;
import com.trains.model.dto.TrainFromStationAToB;
import com.trains.model.entity.SearchStations;
import com.trains.model.entity.Station;
import com.trains.model.entity.TrainWay;
import com.trains.util.mapperForDTO.SearchStationMapper;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class SearchStationServiceTest {
    private SearchStationDTO searchStationDTO;
    private SearchStations searchStations;
    private TrainWay trainWayA;
    private TrainWay trainWayB;
    private TrainFromStationAToB trainFromStationAToB;

    @Mock
    private SearchStationDAO searchStationDAO;

    @Mock
    private TrainWayDAO trainWayDAO;

    @Mock
    private SearchStationMapper searchStationMapper;

    @InjectMocks
    private SearchStationService searchStationService;

    @Before
    public void initSearchStation(){
        MockitoAnnotations.initMocks(this);

        searchStationDTO = new SearchStationDTO();
        Date date = new Date(12-12-1995);
        searchStationDTO.setEndTime(LocalTime.parse("00:00"));
        searchStationDTO.setArrivalStation("Piter");
        searchStationDTO.setDepartureStation("Moscow");
        searchStationDTO.setId(1);
        searchStationDTO.setStartTime(LocalTime.parse("00:00"));
        searchStationDTO.setDepartureDate(date);

        searchStations = new SearchStations();

        searchStations.setEndTime(LocalTime.parse("00:00"));
        searchStations.setArrivalStation("Piter");
        searchStations.setDepartureStation("Moscow");
        searchStations.setId(1);
        searchStations.setStartTime(LocalTime.parse("00:00"));
        searchStations.setDepartureDate(date.toLocalDate());

        trainWayA = new TrainWay();
        trainWayA.setDaysInWay(1);
        trainWayA.setId(1);
        trainWayA.setDepartureTime(Time.valueOf("12:12:00"));
        trainWayA.setStation(new Station());
        trainWayA.setArrivalTime(Time.valueOf("13:13:00"));
        trainWayA.setTrains(new ArrayList<>());

        trainWayB = new TrainWay();
        trainWayB.setDaysInWay(2);
        trainWayB.setId(2);
        trainWayB.setDepartureTime(Time.valueOf("12:12:00"));
        trainWayB.setStation(new Station());
        trainWayB.setArrivalTime(Time.valueOf("13:13:00"));
        trainWayB.setTrains(new ArrayList<>());

        trainFromStationAToB = new TrainFromStationAToB();
        trainFromStationAToB.setTrainID(1);
        trainFromStationAToB.setArrivalStation("piter");
        trainFromStationAToB.setDeprtureStation("moscow");
        trainFromStationAToB.setCountFreeSits(100);
        trainFromStationAToB.setArrivalTime(LocalTime.of(12,12));
        trainFromStationAToB.setDepartureTime(LocalTime.of(13,13));
    }


    @Test
    public void getAllStations() {

        List<SearchStations> actual = new ArrayList<>();
        actual.add(searchStations);

        List<SearchStationDTO> actualDTO = new ArrayList<>();
        actualDTO.add(searchStationDTO);

        Mockito.when(searchStationDAO.allTrains()).thenReturn(actual);
        Mockito.when(searchStationMapper.mapEntityToDto(searchStations)).thenReturn(searchStationDTO);
        Assertions.assertEquals(searchStationService.getAllStations(),actualDTO);
    }

    @Test
    public void add() {
        searchStationService.add(any(SearchStationDTO.class));
        Mockito.verify(searchStationDAO,Mockito.atLeastOnce()).add(any(SearchStationDTO.class));
    }

    @Test
    public void delete() {
        searchStationService.delete(any(SearchStationDTO.class));
        Mockito.verify(searchStationDAO,Mockito.atLeastOnce()).delete(any(SearchStationDTO.class));
    }

    @Test
    public void edit() {
        searchStationService.edit(any(SearchStationDTO.class));
        Mockito.verify(searchStationDAO,Mockito.atLeastOnce()).edit(any(SearchStationDTO.class));
    }

    @Test
    public void getById() {
        Mockito.when(searchStationDAO.getById(1)).thenReturn(searchStations);
        Mockito.when(searchStationMapper.mapEntityToDto(searchStations)).thenReturn(searchStationDTO);
        Assertions.assertEquals(searchStationService.getById(1),searchStationDTO);
    }

    @Test
    public void delByID() {
        searchStationService.delByID(anyInt());
        Mockito.verify(searchStationDAO,Mockito.atLeastOnce()).delByID(anyInt());
    }

    @Test
    public void getTrainWaysFromStationAToB() {
        List<TrainWay> actual = new ArrayList<>();
        actual.add(trainWayA);

        List<TrainWay> actualB = new ArrayList<>();
        actualB.add(trainWayB);

        Station stationA = new Station();
        stationA.setId(1);
        stationA.setNameStation("piter");
        stationA.setTrainWays(actual);

        Station stationB = new Station();
        stationA.setId(2);
        stationA.setNameStation("moscow");
        stationA.setTrainWays(actual);

        Mockito.when(searchStationDAO.getStationByName("piter")).thenReturn(stationA);
        Mockito.when(searchStationDAO.getStationByName("moscow")).thenReturn(stationB);

        Mockito.when(trainWayDAO.getWaysByStationId(1)).thenReturn(actual);
        Mockito.when(trainWayDAO.getWaysByStationId(2)).thenReturn(actualB);
        Mockito.when(trainWayDAO.getWaysByNumberWay(trainWayA.getNumberWay())).thenReturn(actual);

        Assertions.assertEquals(searchStationService.getTrainWaysFromStationAToB("piter","moscow"),new ArrayList<>());
    }
//
//    @Test
//    public void getTrainsWithCorrectDate() {
//        List<TrainFromStationAToB> actual = new ArrayList<>();
//        actual.add(trainFromStationAToB);
//        Mockito.when(searchStationService.getTrainsWithCorrectDate(new ArrayList<>(),new ArrayList<>(),trainFromStationAToB.getDeprtureStation(),
//                trainFromStationAToB.getArrivalStation(), LocalDate.now())).thenReturn(actual);
//    }
//
//    @Test
//    public void getTrainsFromStations() {
//        List<TrainFromStationAToB> actual = new ArrayList<>();
//        actual.add(trainFromStationAToB);
//        Mockito.when(searchStationService.getTrainsFromStations(trainFromStationAToB.getDeprtureStation(),
//                trainFromStationAToB.getArrivalStation(),trainFromStationAToB.getDepartureTime(),trainFromStationAToB.getArrivalTime(),LocalDate.now())).thenReturn(actual);
//    }
//
}
