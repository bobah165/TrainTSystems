package com.trains.controller;


import com.trains.model.dto.TicketInformDTO;
import com.trains.service.TicketInformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/inform")
public class TicketInformController {
    private TicketInformService ticketInformService;
    private static Logger logger = LoggerFactory.getLogger(TicketInformController.class);

    @Autowired
    public void setTicketInformService(TicketInformService ticketInformService) {
        this.ticketInformService = ticketInformService;
    }

    @GetMapping(value = "/")
    public ModelAndView allTickets() {
        List<TicketInformDTO> tickets = ticketInformService.getAllTickets();
        logger.info("Get all info about tickets");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ticket-info/ticket-info");
        modelAndView.addObject("ticketsList",tickets);
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView getEditPage (@PathVariable("id") int id) {
        TicketInformDTO ticket = ticketInformService.getById(id);
        logger.info("Get ticket info by id = "+id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ticket-view/edit-ticket");
        modelAndView.addObject("ticket",ticket);
        return modelAndView;
    }


    @PostMapping(value = "/edit")
    public ModelAndView update (@ModelAttribute("ticket") TicketInformDTO ticket) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/ticket/");
        ticketInformService.edit(ticket);
        logger.info("Edit ticket info "+ticket);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView getAddPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ticket-view/add-ticket");
        logger.info("Read view /ticket-view/add-ticket");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView create(@ModelAttribute("ticket") TicketInformDTO ticket) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/ticket/");
        ticketInformService.add(ticket);
        logger.info("Add ticket info "+ticket);
        return modelAndView;
    }

    @GetMapping (value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/ticket/");
        ticketInformService.delByID(id);
        logger.info("Delete ticket info by id = "+id);
        return modelAndView;
    }

}
