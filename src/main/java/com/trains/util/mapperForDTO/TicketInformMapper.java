package com.trains.util.mapperForDTO;

import com.trains.model.dto.TicketInformDTO;
import com.trains.model.entity.TicketInform;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalTime;

@Component
public class TicketInformMapper {

    public TicketInform mapDtoToEntity (TicketInformDTO ticketInformDTO) {
        TicketInform ticket = new TicketInform();

        ticket.setArrivalDate(ticketInformDTO.getArrivalDate().toLocalDate());
        ticket.setDepartureDate(ticketInformDTO.getDepartureDate().toLocalDate());
        ticket.setBirthday(ticketInformDTO.getBirthday().toLocalDate());
        ticket.setArrivalStation(ticketInformDTO.getArrivalStation());
        ticket.setArrivalTime(LocalTime.parse(ticketInformDTO.getArrivalTime()));
        ticket.setDepartureStation(ticketInformDTO.getDepartureStation());
        ticket.setDepartureTime(LocalTime.parse(ticketInformDTO.getDepartureTime()));
        ticket.setId(ticketInformDTO.getId());
        ticket.setIdPassenger(ticketInformDTO.getIdPassenger());
        ticket.setIdTrain(ticketInformDTO.getIdTrain());
        ticket.setName(ticketInformDTO.getName());
        ticket.setSurname(ticketInformDTO.getSurname());
        return ticket;
    }

    public TicketInformDTO mapEntityToDto (TicketInform ticketInform) {
        TicketInformDTO ticketDTO = new TicketInformDTO();
        ticketDTO.setArrivalDate(Date.valueOf(ticketInform.getArrivalDate()));
        ticketDTO.setArrivalStation(ticketInform.getArrivalStation());
        ticketDTO.setArrivalTime(ticketInform.getArrivalTime().toString());
        ticketDTO.setBirthday(Date.valueOf(ticketInform.getBirthday()));
        ticketDTO.setDepartureDate(Date.valueOf(ticketInform.getDepartureDate()));
        ticketDTO.setDepartureStation(ticketInform.getDepartureStation());
        ticketDTO.setDepartureTime(ticketInform.getDepartureTime().toString());
        ticketDTO.setId(ticketInform.getId());
        ticketDTO.setIdPassenger(ticketInform.getIdPassenger());
        ticketDTO.setIdTrain(ticketInform.getIdTrain());
        ticketDTO.setName(ticketInform.getName());
        ticketDTO.setSurname(ticketInform.getSurname());
        return ticketDTO;
    }
}
