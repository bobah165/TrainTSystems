package com.trains.service;


import com.trains.dao.TrainWayDAO;
import com.trains.model.dto.FreeSeatsDTO;
import com.trains.model.dto.TrainWayDTO;
import com.trains.model.entity.Station;
import com.trains.model.entity.TrainWay;
import com.trains.util.mapperForDTO.TrainWayMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TrainWayServiceTest {
    private TrainWayDTO trainWayDTO;
    private Station station;
    private TrainWay trainWay;

    @Mock
    private TrainWayDAO trainWayDAO;

    @Mock
    private TrainWayMapper trainWayMapper;

    @InjectMocks
    private TrainWayService trainWayService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        station = new Station();
        station.setNameStation("piter");
        station.setId(1);
        station.setTrainWays(new ArrayList<>());

        trainWayDTO = new TrainWayDTO();
        trainWayDTO.setDaysInWay(1);
        trainWayDTO.setId(1);
        trainWayDTO.setShedule("12:12:00");
        trainWayDTO.setStation(station);
        trainWayDTO.setStopTime("13:13:00");
        trainWayDTO.setTrains(new ArrayList<>());
        trainWayDTO.setFreeSeats(200);

        trainWay = new TrainWay();
        trainWay.setDaysInWay(1);
        trainWay.setId(1);
        trainWay.setArrivalTime(new Time(12-12-00));
        trainWay.setStation(station);
        trainWay.setDepartureTime(new Time(13-13-00));
        trainWay.setTrains(new ArrayList<>());
        trainWay.setDaysInWay(10);
    }


    @Test
    public void allWays() {
            List<TrainWayDTO> actualDTO = new ArrayList<>();
            actualDTO.add(trainWayDTO);

            List<TrainWay> actual = new ArrayList<>();
            actual.add(trainWay);

            Mockito.when(trainWayDAO.getAllWays()).thenReturn(actual);
            Mockito.when(trainWayMapper.mapEntityToDto(trainWay)).thenReturn(trainWayDTO);
            Assertions.assertEquals(trainWayService.getAllWays(),actualDTO);
        }

        @Test
        public void add() {
            trainWayService.add(any(TrainWayDTO.class));
            Mockito.verify(trainWayDAO,Mockito.atLeastOnce()).add(any(TrainWayDTO.class));
        }

        @Test
        public void delete() {
            trainWayService.delete(any(TrainWayDTO.class));
            Mockito.verify(trainWayDAO,Mockito.atLeastOnce()).delete(any(TrainWayDTO.class));
        }

        @Test
        public void edit() {
            trainWayService.edit(any(TrainWayDTO.class));
            Mockito.verify(trainWayDAO,Mockito.atLeastOnce()).edit(any(TrainWayDTO.class));
        }

        @Test
        public void getById() {
            Mockito.when(trainWayDAO.getById(1)).thenReturn(trainWay);
            Mockito.when(trainWayMapper.mapEntityToDto(trainWay)).thenReturn(trainWayDTO);
            Assertions.assertEquals(trainWayService.getById(1),trainWayDTO);
        }

        @Test
        public void delByID() {
            trainWayService.delByID(anyInt());
            Mockito.verify(trainWayDAO,Mockito.atLeastOnce()).delByID(anyInt());
        }
}
