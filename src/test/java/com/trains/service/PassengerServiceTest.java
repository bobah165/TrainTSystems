package com.trains.service;

import com.trains.model.dto.PassengerDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class PassengerServiceTest {
    private PassengerDTO passengerDTO;

    @Mock
    private PassengerService passengerService;

    @Before
    public void initPassnger() {
        passengerDTO = new PassengerDTO();
        Date date = new Date(12-12-1987);

        passengerDTO.setUser("passenger");
        passengerDTO.setEmail("none@mial.ru");
        passengerDTO.setPassword("1234");
        passengerDTO.setLogin("none");
        passengerDTO.setSurname("smith");
        passengerDTO.setName("bob");
        passengerDTO.setBirthday(date);
        passengerDTO.setId(1);
        passengerDTO.setTickets(new ArrayList<>());
    }


    @Test
    public void allPassengers() {
        List<PassengerDTO> actual = new ArrayList<>();
        actual.add(passengerDTO);
        Mockito.when(passengerService.allPassengers()).thenReturn(actual);
    }

    @Test
    public void add() {
        Mockito.doNothing().when(passengerService).add(passengerDTO);
    }

    @Test
    public void delete() {
        Mockito.doNothing().when(passengerService).delete(passengerDTO);
    }

    @Test
    public void edit() {
        Mockito.doNothing().when(passengerService).edit(passengerDTO);
    }

    @Test
    public void getById() {
        Mockito.when(passengerService.getById(1)).thenReturn(passengerDTO);
    }


    @Test
    public void delByID() {
        Mockito.doNothing().when(passengerService).delByID(1);
    }

    @Test
    public void getPassengerId() {
        Mockito.when(passengerService.getPassengerId(passengerDTO.getName(),passengerDTO.getSurname(),passengerDTO.getBirthday())).thenReturn(1);
    }

    @Test
    public void addPassengerByNameSurnameDate() {
        Mockito.doNothing().when(passengerService).addPassengerByNameSurnameDate(passengerDTO.getName(),passengerDTO.getSurname(),passengerDTO.getBirthday());
    }

    @Test
    public void getPassengerBylogin() {
        Mockito.when(passengerService.getPassengerBylogin(passengerDTO.getLogin())).thenReturn(passengerDTO);
    }
}
