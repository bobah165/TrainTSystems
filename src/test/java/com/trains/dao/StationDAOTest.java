package com.trains.dao;

import com.trains.model.dto.TrainFromStationDTO;
import com.trains.model.entity.Station;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class StationDAOTest {
    private Station station;
    private TrainFromStationDTO trainFromStationDTO;

    @Mock
    private StationDAO stationDAO;

    @Before
    public void initStation(){
        station = new Station();

        station.setNameStation("Piter");
        station.setId(1);

        trainFromStationDTO = new TrainFromStationDTO();
        trainFromStationDTO.setIdTrain(1);
        trainFromStationDTO.setArrivalTime(LocalTime.of(12,12));
        trainFromStationDTO.setDepartureTime(LocalTime.of(12,12));
        trainFromStationDTO.setNameStation("Piter");
        LocalDate date  = LocalDate.of(2019,12,12);
    }

    @Test
    public void allStations() {
        List<Station> actual = new ArrayList<>();
        actual.add(station);
        Mockito.when(stationDAO.getAllStations()).thenReturn(actual);
    }

    @Test
    public void getById() {
        Mockito.when(stationDAO.getById(1)).thenReturn(station);
    }

    @Test
    public void getByName() {
        Mockito.when(stationDAO.getByName(station.getNameStation())).thenReturn(station);
    }

    @Test
    public void delByID() {
        Mockito.doNothing().when(stationDAO).delByID(1);
    }

    @Test
    public void getStationsForPagination() {
        List<Station> actual = new ArrayList<>();
        actual.add(station);
        Mockito.when(stationDAO.getStationsForPagination(1)).thenReturn(actual);
    }

    @Test
    public void getCountStationsForPagination(){
        Mockito.when(stationDAO.getCountStationsForPagination()).thenReturn(1);
    }
}
