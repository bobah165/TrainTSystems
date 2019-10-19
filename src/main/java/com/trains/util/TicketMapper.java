package com.trains.util;

import com.trains.model.dto.TicketDTO;
import com.trains.model.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public Ticket mapDtoToEntity (TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setArrivalDate(ticketDTO.getArrivalDate());
        ticket.setArrivalStation(ticketDTO.getArrivalStation());
        ticket.setDepartureDate(ticketDTO.getDepartureDate());
        ticket.setDepartureStation(ticketDTO.getDepartureStation());
        ticket.setId(ticketDTO.getId());
        ticket.setPassenger(ticketDTO.getPassenger());
        ticket.setTrain(ticketDTO.getTrain());
        return ticket;
    }

    public TicketDTO mapEntityToDto (Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setArrivalDate(ticket.getArrivalDate());
        ticketDTO.setArrivalStation(ticket.getArrivalStation());
        ticketDTO.setDepartureDate(ticket.getDepartureDate());
        ticketDTO.setDepartureStation(ticket.getDepartureStation());
        ticketDTO.setId(ticket.getId());
        ticketDTO.setPassenger(ticket.getPassenger());
        ticketDTO.setTrain(ticket.getTrain());
        return ticketDTO;
    }
}
