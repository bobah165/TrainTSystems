package com.trains.service;


import com.trains.dao.StationDAO;
import com.trains.dao.TrainDAO;
import com.trains.dao.TrainWayDAO;
import com.trains.model.dto.StationDTO;
import com.trains.model.dto.TrainFromStationDTO;
import com.trains.model.entity.Station;
import com.trains.model.entity.TrainWay;
import com.trains.util.mapperForDTO.StationMapper;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class StationServiceTest {
    @Mock
    private StationDTO stationDTO;

    private Station station;
    private TrainFromStationDTO trainFromStationDTO;
    private StationDTO stationdto;
    private TrainWay trainWay;

    @Mock
    private StationDAO stationDAO;

    @Mock
    private TrainDAO trainDAO;

    @Mock
    private TrainWayDAO trainWayDAO;

    @Mock
    private StationMapper stationMapper;

    @InjectMocks
    private StationService stationService;

    @Before
    public void initStation(){
        MockitoAnnotations.initMocks(this);

        stationdto = new StationDTO();

        stationdto.setNameStation("Piter");
        stationdto.setId(1);

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
        List<StationDTO> actualDTO = new ArrayList<>();
        actualDTO.add(stationdto);

        List<Station> actual = new ArrayList<>();
        actual.add(station);

        Mockito.when(stationMapper.mapEntityToDto(station)).thenReturn(stationdto);

        Mockito.when(stationDAO.getAllStations()).thenReturn(actual);
        Assertions.assertEquals(stationService.getAllStations(),actualDTO);
    }

    @Test
    public void add() {
        stationService.add(stationDTO);
        Mockito.verify(stationDAO,Mockito.atLeastOnce()).add(any(StationDTO.class));
    }

    @Test
    public void delete() {
        stationService.delete(stationDTO);
        Mockito.verify(stationDAO,Mockito.atLeastOnce()).delete(any(StationDTO.class));
    }

    @Test
    public void edit() {
        stationService.edit(stationDTO);
        Mockito.verify(stationDAO,Mockito.atLeastOnce()).edit(any(StationDTO.class));
    }

    @Test
    public void getById() {
        Mockito.when(stationDAO.getById(1)).thenReturn(station);
        Mockito.when(stationMapper.mapEntityToDto(station)).thenReturn(stationdto);
        Assertions.assertEquals(stationService.getById(1),stationdto);
    }

    @Test
    public void delByID() {
        stationService.delByID(any(int.class));
        Mockito.verify(stationDAO,Mockito.atLeastOnce()).delByID(any(int.class));
    }


    @Test
    public void getByName() {
        Mockito.when(stationDAO.getByName(station.getNameStation())).thenReturn(station);
        Mockito.when(stationMapper.mapEntityToDto(station)).thenReturn(stationdto);
        Assertions.assertEquals(stationService.getByName(station.getNameStation()),stationdto);
    }

    @Test
    public void getStationsForPagination() {
        List<StationDTO> actual = new ArrayList<>();
        actual.add(stationdto);
        Mockito.when(stationService.getStationsForPagination(1)).thenReturn(actual);
    }

    @Test
    public void getCountStationsForPagination() {
        Mockito.when(stationDAO.getCountStationsForPagination()).thenReturn(1);
        Assertions.assertEquals(stationService.getCountStationsForPagination(),1);
    }

    @Test
    public void getTrainFromStation() {
        List<TrainFromStationDTO> actual = new ArrayList<>();
        actual.add(trainFromStationDTO);

        List<TrainWay> trainWays = new ArrayList<>();
        trainWays.add(trainWay);

        Mockito.when(trainWayDAO.getWaysByStationId(1)).thenReturn(trainWays);
        Mockito.when(stationDAO.getById(1)).thenReturn(station);
        Mockito.when(trainWayDAO.getTrainWayByStationAndWay(station.getNameStation(),trainWay.getNumberWay())).thenReturn(trainWay);

        Assertions.assertEquals(stationService.getTrainFromStation(1,LocalDate.of(2019,12,12),
                trainFromStationDTO.getArrivalTime(),trainFromStationDTO.getDepartureTime()),new ArrayList<>());
    }

    @Test
    public void getTrainsFromStationInDate() {
        List<TrainFromStationDTO> actual = new ArrayList<>();
        actual.add(trainFromStationDTO);

        Mockito.when(trainWayDAO.getTrainWayByStationAndWay(station.getNameStation(),trainWay.getNumberWay())).thenReturn(trainWay);

        Assertions.assertEquals(stationService.getTrainsFromStationInDate(LocalDate.of(2019,12,12),station,actual,trainWay),actual);
    }
}
