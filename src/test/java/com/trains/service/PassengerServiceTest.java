package com.trains.service;

import com.trains.dao.PassengerDAO;
import com.trains.model.dto.PassengerDTO;
import com.trains.model.entity.Passenger;
import com.trains.util.mapperForDTO.PassengerMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class PassengerServiceTest {

    private PassengerDTO passengerDTO;
    private Passenger passenger;

    @Mock
    private PassengerDAO passengerDAO;

    @Mock
    private PassengerMapper passengerMapper;

    @InjectMocks
    private PassengerService passengerService;

    @Before
    public void initPassenger() {
        MockitoAnnotations.initMocks(this);

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

        passenger = new Passenger();

        passenger.setUser("passenger");
        passenger.setEmail("none@mial.ru");
        passenger.setPassword("1234");
        passenger.setLogin("none");
        passenger.setSurname("smith");
        passenger.setName("bob");
        passenger.setBirthday(date.toLocalDate());
        passenger.setId(1);
        passenger.setTickets(new ArrayList<>());
    }


    @Test
    public void allPassengers() {
        List<Passenger> actual = new ArrayList<>();
        actual.add(passenger);

        List<PassengerDTO> actualDTO = new ArrayList<>();
        actualDTO.add(passengerDTO);

        Mockito.when(passengerMapper.mapEntityToDto(passenger)).thenReturn(passengerDTO);
        Mockito.when(passengerDAO.getAllPassengers()).thenReturn(actual);
        Assertions.assertEquals(passengerService.getAllPassengers(),actualDTO);
    }

    @Test
    public void add() {
        passengerService.add(any(PassengerDTO.class));
        Mockito.verify(passengerDAO,Mockito.atLeastOnce()).add(any(PassengerDTO.class));
    }

    @Test
    public void delete() {
        passengerService.delete(any(PassengerDTO.class));
        Mockito.verify(passengerDAO,Mockito.atLeastOnce()).delete(any(PassengerDTO.class));
    }

    @Test
    public void edit() {
        passengerService.edit(any(PassengerDTO.class));
        Mockito.verify(passengerDAO,Mockito.atLeastOnce()).edit(any(PassengerDTO.class));
    }

    @Test
    public void getById() {
        Mockito.when(passengerDAO.getById(1)).thenReturn(passenger);
        Mockito.when(passengerMapper.mapEntityToDto(passenger)).thenReturn(passengerDTO);
        Assertions.assertEquals(passengerService.getById(1),passengerDTO);
    }


    @Test
    public void delByID() {
        passengerService.delByID(any(int.class));
        Mockito.verify(passengerDAO,Mockito.atLeastOnce()).delByID(any(int.class));
    }

    @Test
    public void getPassengerId() {
        Mockito.when(passengerDAO.getPassengerId(anyString(),anyString(),any(Date.class))).thenReturn(1);
        Mockito.when(passengerMapper.mapEntityToDto(passenger)).thenReturn(passengerDTO);
        Assertions.assertEquals(passengerService.getPassengerId(anyString(),anyString(),any(Date.class)),1);
    }

    @Test
    public void addPassengerByNameSurnameDate() {
        passengerService.addPassengerByNameSurnameDate(any(String.class),any(String.class),any(Date.class));
        Mockito.verify(passengerDAO, Mockito.atLeastOnce()).addPassengerByNameSurnameDate(any(String.class),any(String.class),any(Date.class));
    }

    @Test
    public void getPassengerByLogin() {
        Mockito.when(passengerDAO.getPassengerBylogin(anyString())).thenReturn(passenger);
        Mockito.when(passengerMapper.mapEntityToDto(passenger)).thenReturn(passengerDTO);
        Assertions.assertEquals(passengerService.getPassengerBylogin(anyString()),passengerDTO);
    }
}
