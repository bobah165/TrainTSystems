package com.trains.controller;


import com.trains.model.dto.PassengerDTO;
import com.trains.service.PassengerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/passenger")
public class PassengerController {
    private PassengerService passengerService;
    private static Logger logger = Logger.getLogger(PassengerController.class);

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

@GetMapping(value = "/")
    public ModelAndView allPassengers() {
    List<PassengerDTO> passengers = passengerService.allPassengers();
    logger.info("Get all passengers from all trains");
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("passenger-view/passengers");
    logger.info("Read view passenger-view/passengers");
    modelAndView.addObject("passengersList",passengers);
    return modelAndView;
}


    @GetMapping(value = "/edit/{id}")
    public ModelAndView getEditPage (@PathVariable("id") int id) {
        PassengerDTO passenger = passengerService.getById(id);
        logger.info("Get passenger by ID "+passenger.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("passenger-view/edit-passenger");
        logger.info("Read view /passenger-view/edit-passenger");
        modelAndView.addObject("passenger",passenger);
        return modelAndView;
    }


    @PostMapping(value = "/edit")
    public ModelAndView update (@ModelAttribute("passenger") PassengerDTO passenger) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/passenger/");
        passengerService.edit(passenger);
        logger.info("Edit passenger "+passenger);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView getPassPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("passenger-view/add-passenger");
        logger.info("Read view /passenger-view/add-passenger");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView create(@ModelAttribute("passenger") PassengerDTO passenger) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/passenger/");
        passengerService.add(passenger);
        logger.info("Add passenger "+passenger);
        return modelAndView;
    }

    @GetMapping (value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/passenger/");
        passengerService.delByID(id);
        logger.info("delete passenger by id = " +id);
        return modelAndView;
    }


}
