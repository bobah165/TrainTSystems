package com.trains.service;


import com.trains.model.dto.TrainWayDTO;
import com.trains.model.entity.Station;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TrainWayServiceTest {
    private TrainWayDTO trainWayDTO;
    private Station station;

    @Mock
    private TrainWayService trainWayService;

    @Before
    public void init() {
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
    }


    @Test
    public void allWays() {
            List<TrainWayDTO> actual = new ArrayList<>();
            actual.add(trainWayDTO);
            Mockito.when(trainWayService.getAllWays()).thenReturn(actual);
        }

        @Test
        public void add() {
            Mockito.doNothing().when(trainWayService).add(trainWayDTO);
        }

        @Test
        public void delete() {
            Mockito.doNothing().when(trainWayService).delete(trainWayDTO);
        }

        @Test
        public void edit() {
            Mockito.doNothing().when(trainWayService).edit(trainWayDTO);
        }

        @Test
        public void getById() {
            Mockito.when(trainWayService.getById(1)).thenReturn(trainWayDTO);
        }

        @Test
        public void delByID() {
            Mockito.doNothing().when(trainWayService).delByID(1);
        }
}
