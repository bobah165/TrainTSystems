package com.trains.dao;


import com.trains.model.entity.Passenger;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class PassengerDAOTest {
    private Passenger passenger;

    @Mock
    private PassengerDAO passengerDAO;

    @Before
    public void init() {
        passenger = new Passenger();
        Date date = new Date(12-12-1987);

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
        Mockito.when(passengerDAO.getAllPassengers()).thenReturn(actual);
        assertEquals(actual,passengerDAO.getAllPassengers());
    }

    @Test
    public void delByID() {
        Mockito.doNothing().when(passengerDAO).delByID(1);
    }

    @Test
    public void getById() {
        Mockito.when(passengerDAO.getById(1)).thenReturn(passenger);
        assertEquals(passenger,passengerDAO.getById(1));
    }

    @Test
    public void getPassengerId() {
        Mockito.when(passengerDAO.getPassengerId(passenger.getName(),passenger.getSurname(),Date.valueOf(passenger.getBirthday()))).thenReturn(1);
        assertEquals(1,passengerDAO.getPassengerId(passenger.getName(),passenger.getSurname(),Date.valueOf(passenger.getBirthday())));
    }

    @Test
    public void addPassengerByNameSurnameDate() {
        Mockito.doNothing().when(passengerDAO).addPassengerByNameSurnameDate(passenger.getName(),passenger.getSurname(),Date.valueOf(passenger.getBirthday()));
    }


    @Test
    public void getPassengerBylogin() {
        Mockito.when(passengerDAO.getPassengerBylogin(passenger.getLogin())).thenReturn(passenger);
        assertEquals(passenger,passengerDAO.getPassengerBylogin("none"));
    }
}
