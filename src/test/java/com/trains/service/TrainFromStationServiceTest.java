package com.trains.service;


import com.trains.dao.TrainFromStationDAO;
import com.trains.model.dto.TrainFromStationDTO;
import com.trains.model.entity.TrainFromStation;
import com.trains.util.mapperForDTO.TrainFromStationMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TrainFromStationServiceTest {

    @Mock
    private TrainFromStationDAO trainFromStationDAO;

    @Mock
    private TrainFromStationMapper mapper;

    private TrainFromStationDTO trainFromStationDTO;
    private TrainFromStation trainFromStation;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        trainFromStationDTO = new TrainFromStationDTO();
        trainFromStationDTO.setNameStation("Piter");
        trainFromStationDTO.setIdTrain(1);
        trainFromStationDTO.setArrivalTime(LocalTime.of(10,10));
        trainFromStationDTO.setDepartureTime(LocalTime.of(11,11));
        trainFromStationDTO.setDaysInWay(1);

        trainFromStation = mapper.mapDtoToEntity(trainFromStationDTO);
    }


    @InjectMocks
    private TrainFromStationService trainFromStationService;


    @Test
    public void getAllTrains() {
        List<TrainFromStationDTO> actualDTO = new ArrayList<>();
        actualDTO.add(trainFromStationDTO);

        List<TrainFromStation> actual = new ArrayList<>();
        actual.add(trainFromStation);

        Mockito.when(trainFromStationDAO.getAllTrain()).thenReturn(actual);
        Mockito.when(mapper.mapEntityToDto(trainFromStation)).thenReturn(trainFromStationDTO);
        Assertions.assertEquals(trainFromStationService.getAllTrains(),actualDTO);
    }

    @Test
    public void add() {
        trainFromStationService.add(any(TrainFromStationDTO.class));
        Mockito.verify(trainFromStationDAO,Mockito.atLeastOnce()).add(any(TrainFromStationDTO.class));
    }

    @Test
    public void delete() {
        trainFromStationService.delete(any(TrainFromStationDTO.class));
        Mockito.verify(trainFromStationDAO,Mockito.atLeastOnce()).delete(any(TrainFromStationDTO.class));
    }

    @Test
    public void edit() {
        trainFromStationService.edit(any(TrainFromStationDTO.class));
        Mockito.verify(trainFromStationDAO,Mockito.atLeastOnce()).edit(any(TrainFromStationDTO.class));
    }

    @Test
    public void getById() {
        Mockito.when(trainFromStationDAO.getById(1)).thenReturn(trainFromStation);
        Mockito.when(mapper.mapEntityToDto(trainFromStation)).thenReturn(trainFromStationDTO);
        Assertions.assertEquals(trainFromStationService.getById(1),trainFromStationDTO);
    }
}
