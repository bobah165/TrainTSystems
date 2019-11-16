package com.trains.service;


import com.trains.model.dto.FreeSeatsDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class FreeSeatsServiceTest {
    private FreeSeatsDTO freeSeatsDTO;
    @Mock
    private FreeSeatsService freeSeatsService;

    @Before
    public void initFreeSeats() {
        freeSeatsDTO = new FreeSeatsDTO();
        freeSeatsDTO.setId(1);
        freeSeatsDTO.setIdTrain(1);
        freeSeatsDTO.setStationName("Rostov");
        freeSeatsDTO.setFreeSeats(800);
    }

    @Test
    public void allSeats() {
        List<FreeSeatsDTO> actual = new ArrayList<>();
        actual.add(freeSeatsDTO);
        Mockito.when(freeSeatsService.getAllSeats()).thenReturn(actual);

    }

    @Test
    public void add() {
        Mockito.doNothing().when(freeSeatsService).add(freeSeatsDTO);
    }

    @Test
    public void delete() {
        Mockito.doNothing().when(freeSeatsService).delete(freeSeatsDTO);
    }

    @Test
    public void edit() {
        Mockito.doNothing().when(freeSeatsService).edit(freeSeatsDTO);
    }

    @Test
    public void getById() {
        Mockito.when(freeSeatsService.getById(1)).thenReturn(freeSeatsDTO);
    }

    @Test
    public void getByStationAndTrainID() {
        Mockito.when(freeSeatsService.getByStationAndTrainID(freeSeatsDTO.getStationName(),freeSeatsDTO.getIdTrain())).thenReturn(freeSeatsDTO);
    }
    @Test
    public void checkFreeSeats() {
        Mockito.when(freeSeatsService.getByStationAndTrainID(freeSeatsDTO.getStationName(),freeSeatsDTO.getIdTrain())).thenReturn(freeSeatsDTO);
    }
}
