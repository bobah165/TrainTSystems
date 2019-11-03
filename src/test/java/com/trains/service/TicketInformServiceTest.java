package com.trains.service;

import com.trains.model.dto.TicketInformDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class TicketInformServiceTest {
    private TicketInformDTO ticketInformDTO;
    @Mock
    private TicketInformService ticketInformService;

    @Before
    public void initTicketInform() {
        ticketInformDTO = new TicketInformDTO();
        Date date = new Date(01-01-1987);

        ticketInformDTO.setIdPassenger(1);
        ticketInformDTO.setId(1);
        ticketInformDTO.setBirthday(date);
        ticketInformDTO.setSurname("smith");
        ticketInformDTO.setName("bob");
        ticketInformDTO.setArrivalDate(date);
        ticketInformDTO.setDepartureTime("12:12:12");
        ticketInformDTO.setDepartureStation("Piter");
        ticketInformDTO.setIdTrain(1);
        ticketInformDTO.setDepartureDate(date);
        ticketInformDTO.setArrivalTime("13:13:13");
        ticketInformDTO.setArrivalStation("Moscow");
    }

    @Test
    public void allTickets() {
        List<TicketInformDTO> actual = new ArrayList<>();
        actual.add(ticketInformDTO);
        Mockito.when(ticketInformService.allTickets()).thenReturn(actual);
    }

    @Test
    public void add() {
        Mockito.doNothing().when(ticketInformService).add(ticketInformDTO);
    }

    @Test
    public void delete() {
            Mockito.doNothing().when(ticketInformService).delete(ticketInformDTO);
    }

    @Test
    public void edit() {
        Mockito.doNothing().when(ticketInformService).edit(ticketInformDTO);
    }

    @Test
    public void getById() {
        Mockito.when(ticketInformService.getById(1)).thenReturn(ticketInformDTO);
    }

    @Test
    public void delByID() {
        Mockito.doNothing().when(ticketInformService).delByID(1);
    }
}
