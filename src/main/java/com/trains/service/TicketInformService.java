package com.trains.service;

import com.trains.dao.TicketInformDAO;
import com.trains.model.dto.*;
import com.trains.model.entity.TicketInform;
import com.trains.util.mapperForDTO.TicketInformMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TicketInformService {
    private TicketInformDAO ticketInformDAO;
    private TicketInformMapper ticketInformMapper;

    @Autowired
    public void setTicketInformDAO(TicketInformDAO ticketInformDAO) {
        this.ticketInformDAO = ticketInformDAO;
    }

    @Autowired
    public void setTicketInformMapper(TicketInformMapper ticketInformMapper) {
        this.ticketInformMapper = ticketInformMapper;
    }

    public List<TicketInformDTO> getAllTickets() {
        List<TicketInform> tickets = ticketInformDAO.getAllTickets();
        List<TicketInformDTO> ticketDTOS = new ArrayList<>();
        for (TicketInform ticket: tickets) {
            ticketDTOS.add(ticketInformMapper.mapEntityToDto(ticket));
        }
        return ticketDTOS;
    }

    public void add(TicketInformDTO ticketDTO) {
        TicketInform ticket = ticketInformMapper.mapDtoToEntity(ticketDTO);
        ticketInformDAO.add(ticket); }

    public void delete(TicketInformDTO ticketDTO) {
        TicketInform ticket = ticketInformMapper.mapDtoToEntity(ticketDTO);
        ticketInformDAO.delete(ticket); }

        public void deleteTicketInfoOfCurrentPassenger() {
            int idCurrentPassenger = ((PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            TicketInform ticket = ticketInformDAO.getById(idCurrentPassenger);
            ticketInformDAO.delete(ticket);
        }

    public void edit(TicketInformDTO ticketDTO) {
        TicketInform ticket = ticketInformMapper.mapDtoToEntity(ticketDTO);
        ticketInformDAO.edit(ticket);
    }

    public TicketInformDTO getById(int id) {
        TicketInform ticket = ticketInformDAO.getById(id);
        TicketInformDTO ticketDTO = ticketInformMapper.mapEntityToDto(ticket);
        return ticketDTO; }

    public void delByID (int id) { ticketInformDAO.delByID(id); }

}
