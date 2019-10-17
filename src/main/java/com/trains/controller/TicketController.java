package com.trains.controller;

import com.trains.model.Ticket;
import com.trains.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    private TicketService ticketService;

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(value = "/")
    public ModelAndView allPassengers() {
        List<Ticket> tickets = ticketService.allTickets();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ticket-view/tickets");
        modelAndView.addObject("ticketsList",tickets);
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView update (@PathVariable("id") int id) {
        Ticket ticket = ticketService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ticket-view/edit-ticket");
        modelAndView.addObject("ticket",ticket);
        return modelAndView;
    }


    @PostMapping(value = "/edit")
    public ModelAndView editPassenger(@ModelAttribute("ticket") Ticket ticket) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/ticket/");
        ticketService.edit(ticket);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView getPassPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ticket-view/add-ticket");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView create(@ModelAttribute("ticket") Ticket ticket) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/ticket/");
        ticketService.add(ticket);
        return modelAndView;
    }

    @GetMapping (value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/ticket/");
        Ticket ticket = ticketService.getById(id);
        ticketService.delete(ticket);
        return modelAndView;
    }

}