package com.trains.service;

import com.trains.dao.TicketDAO;
import com.trains.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TicketService {
    private TicketDAO ticketDAO;

    @Autowired
    public void setTicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public List<Ticket> allTickets() {
        return ticketDAO.allTickets();
    }

    public void add(Ticket ticket) { ticketDAO.add(ticket); }

    public void delete(Ticket ticket) {ticketDAO.delete(ticket); }

    public void edit(Ticket ticket) {ticketDAO.edit(ticket); }

    public Ticket getById(int idTicket) { return ticketDAO.getById(idTicket); }

}
