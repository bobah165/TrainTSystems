package com.trains.service;

import com.trains.dao.TicketDAO;
import com.trains.model.dto.PassengerDTO;
import com.trains.model.dto.TicketDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.entity.Passenger;
import com.trains.model.entity.Ticket;
import com.trains.model.entity.Train;
import com.trains.util.mapperForDTO.PassengerMapper;
import com.trains.util.mapperForDTO.TicketMapper;
import com.trains.util.mapperForDTO.TrainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TicketService {
    private TicketMapper ticketMapper;
    private TicketDAO ticketDAO;
    private TrainMapper trainMapper;
    private PassengerMapper passengerMapper;

    @Autowired
    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Autowired
    public void setTicketMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    @Autowired
    public void setTrainMapper(TrainMapper trainMapper) {
        this.trainMapper = trainMapper;
    }

    @Autowired
    public void setPassengerMapper(PassengerMapper passengerMapper) {
        this.passengerMapper = passengerMapper;
    }

    public List<TicketDTO> allTickets() {
        List<Ticket> tickets = ticketDAO.allTickets();
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for (Ticket ticket: tickets) {
            ticketDTOS.add(ticketMapper.mapEntityToDto(ticket));
        }
        return ticketDTOS;
    }

    public void add(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.mapDtoToEntity(ticketDTO);
        ticketDAO.add(ticket); }

    public void delete(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.mapDtoToEntity(ticketDTO);
        ticketDAO.delete(ticket); }

    public void edit(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.mapDtoToEntity(ticketDTO);
        ticketDAO.edit(ticket);
    }

    public TicketDTO getById(int id) {
        Ticket ticket = ticketDAO.getById(id);
        TicketDTO ticketDTO = ticketMapper.mapEntityToDto(ticket);
        return ticketDTO; }

    public void delByID (int id) { ticketDAO.delByID(id); }


    public void addTicketByTrainDTOPassengerDTO (TrainDTO trainDTO, PassengerDTO passengerDTO) {
        ticketDAO.addTicketByTrainDTOPassengerDTO(trainMapper.mapDtoToEntity(trainDTO),passengerMapper.mapDtoToEntity(passengerDTO));
    }

    public boolean checkTicketByNameSurnameBirthday (String name, String surname, Date birthday, TrainDTO train)
    {
        List<Ticket> tickets = ticketDAO.allTickets();
        for (Ticket ticketDTO: tickets) {
            boolean b = ticketDTO.getPassenger().getName().equals(name);
            boolean b1 = ticketDTO.getPassenger().getSurname().equals(surname);
            boolean b2 = ticketDTO.getPassenger().getBirthday().isEqual(birthday.toLocalDate());
            boolean b3 = ticketDTO.getTrain().getId()==train.getId();
            if (b&&b1&&b2&&b3){
                return true;
            }
        }
        return false;
    }

}
