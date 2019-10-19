package com.trains.service;

import com.trains.dao.TicketDAO;
import com.trains.model.dto.TicketDTO;
import com.trains.model.entity.Ticket;
import com.trains.util.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TicketService {
    private TicketMapper ticketMapper;
    private TicketDAO ticketDAO;

    @Autowired
    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Autowired
    public void setTicketMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
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

}
