package com.trains.dao;


import com.trains.model.dto.FreeSeatsDTO;
import com.trains.model.entity.FreeSeats;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class FreeSeatsDAOTest {
    private FreeSeats freeSeatsDTO;

    @Mock
    private FreeSeatsDAO freeSeatsDAO;

    @Before
    public void init() {
        freeSeatsDTO = new FreeSeats();
        freeSeatsDTO.setId(1);
        freeSeatsDTO.setIdTrain(1);
        freeSeatsDTO.setStationName("Rostov");
        freeSeatsDTO.setFreeSeats(800);

    }


    @Test
    public void getById() {
        Mockito.when(freeSeatsDAO.getById(1)).thenReturn(freeSeatsDTO);
    }

    @Test
    public void allSeats() {
        List<FreeSeats> actual = new ArrayList<>();
        actual.add(freeSeatsDTO);
        Mockito.when(freeSeatsDAO.allSeats()).thenReturn(actual);
    }

    @Test
    public void getByStationAndTrainID() {
        Mockito.when(freeSeatsDAO.getByStationAndTrainID(freeSeatsDTO.getStationName(),freeSeatsDTO.getIdTrain())).thenReturn(freeSeatsDTO);
    }

}
