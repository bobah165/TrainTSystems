package com.trains.service;


import com.trains.dao.FreeSeatsDAO;
import com.trains.model.dto.FreeSeatsDTO;
import com.trains.model.entity.FreeSeats;
import com.trains.util.mapperForDTO.FreeSeatsMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class FreeSeatsServiceTest {

    private FreeSeatsDTO freeSeatsDTO;
    private FreeSeats freeSeats;

    @Mock
    private FreeSeatsDAO freeSeatsDAO;

    @Mock
    private FreeSeatsMapper freeSeatsMapper;

    @InjectMocks
    private FreeSeatsService freeSeatsService;

    @Before
    public void initFreeSeats() {
        MockitoAnnotations.initMocks(this);

        freeSeatsDTO = new FreeSeatsDTO();
        freeSeatsDTO.setId(1);
        freeSeatsDTO.setIdTrain(1);
        freeSeatsDTO.setStationName("Rostov");
        freeSeatsDTO.setFreeSeats(800);

        freeSeats = new FreeSeats();
        freeSeats.setId(1);
        freeSeats.setIdTrain(1);
        freeSeats.setStationName("Rostov");
        freeSeats.setFreeSeats(800);
    }


    @Test
    public void allSeats() {
        List<FreeSeats> actual = new ArrayList<>();
        actual.add(freeSeats);

        List<FreeSeatsDTO> actualDTO = new ArrayList<>();
        actualDTO.add(freeSeatsDTO);

        Mockito.when(freeSeatsMapper.mapEntityToDto(freeSeats)).thenReturn(freeSeatsDTO);
        Mockito.when(freeSeatsDAO.getAllSeats()).thenReturn(actual);
        Assertions.assertEquals(freeSeatsService.getAllSeats(),actualDTO);
    }

    @Test
    public void add() {
        freeSeatsService.add(any(FreeSeatsDTO.class));
        Mockito.verify(freeSeatsDAO,Mockito.atLeastOnce()).add(any(FreeSeatsDTO.class));
    }

    @Test
    public void delete() {
        freeSeatsService.delete(any(FreeSeatsDTO.class));
        Mockito.verify(freeSeatsDAO,Mockito.atLeastOnce()).delete(any(FreeSeatsDTO.class));
    }

    @Test
    public void edit() {
        freeSeatsService.edit(any(FreeSeatsDTO.class));
        Mockito.verify(freeSeatsDAO,Mockito.atLeastOnce()).edit(any(FreeSeatsDTO.class));
    }

    @Test
    public void getById() {
        Mockito.when(freeSeatsDAO.getById(1)).thenReturn(freeSeats);
        Mockito.when(freeSeatsMapper.mapEntityToDto(freeSeats)).thenReturn(freeSeatsDTO);
        FreeSeatsDTO expected = freeSeatsService.getById(1);
        Assertions.assertEquals(expected,freeSeatsDTO);
    }

    @Test
    public void getByStationAndTrainID() {
        Mockito.when(freeSeatsDAO.getByStationAndTrainID(freeSeatsDTO.getStationName(),freeSeatsDTO.getIdTrain())).thenReturn(freeSeats);
        Mockito.when(freeSeatsMapper.mapEntityToDto(freeSeats)).thenReturn(freeSeatsDTO);
        FreeSeatsDTO expected = freeSeatsService.getByStationAndTrainID(freeSeatsDTO.getStationName(),freeSeatsDTO.getIdTrain());
        Assertions.assertEquals(expected,freeSeatsDTO);
    }
    @Test
    public void checkFreeSeats() {
        Mockito.when(freeSeatsDAO.getByStationAndTrainID(freeSeatsDTO.getStationName(),freeSeatsDTO.getIdTrain())).thenReturn(freeSeats);
        Mockito.when(freeSeatsMapper.mapEntityToDto(freeSeats)).thenReturn(freeSeatsDTO);
        FreeSeatsDTO expected = freeSeatsService.getByStationAndTrainID(freeSeatsDTO.getStationName(),freeSeatsDTO.getIdTrain());
        Assertions.assertEquals(expected,freeSeatsDTO);
    }
}
