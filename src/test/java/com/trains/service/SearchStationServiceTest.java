package com.trains.service;


import com.trains.model.dto.SearchStationDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class SearchStationServiceTest {
    private SearchStationDTO searchStationDTO;

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

}
