package com.trains.dao;


import com.trains.model.dto.SearchStationDTO;
import com.trains.model.entity.SearchStations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class SearchStationDAOTest {
    private SearchStations searchStation;

    @Mock
    private SearchStationDAO searchStationDAO;

    @Before
    public void initSearchStation(){
        searchStation = new SearchStations();
        Date date = new Date(12-12-1995);

        searchStation.setEndTime(LocalTime.parse("00:00"));
        searchStation.setArrivalStation("Piter");
        searchStation.setDepartureStation("Moscow");
        searchStation.setId(1);
        searchStation.setStartTime(LocalTime.parse("00:00"));
        searchStation.setDepartureDate(date.toLocalDate());
    }

    @Test
    public void getById() {
        Mockito.when(searchStationDAO.getById(1)).thenReturn(searchStation);
    }

    @Test
    public void allTrains() {
        List<SearchStations> actual = new ArrayList<>();
        actual.add(searchStation);
        Mockito.when(searchStationDAO.allTrains()).thenReturn(actual);
    }

    @Test
    public void delByID() {
        Mockito.doNothing().when(searchStationDAO).delByID(1);
    }

}
