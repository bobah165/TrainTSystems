package com.trains.controller;


import com.trains.model.Passenger;
import com.trains.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/passenger")
public class PassengerController {
    private PassengerService passengerService;

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

@GetMapping(value = "/")
    public ModelAndView allPassengers() {
    List<Passenger> passengers = passengerService.allPassengers();
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("passengers");
    modelAndView.addObject("passengersList",passengers);
    return modelAndView;
}

    @GetMapping(value = "/edit/{id}")
    public ModelAndView update (@PathVariable("id") int id) {
        Passenger passenger = passengerService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit-passenger");
        modelAndView.addObject("passenger",passenger);
        return modelAndView;
    }


    @PostMapping(value = "/edit")
    public ModelAndView editPassenger(@ModelAttribute("passenger") Passenger passenger) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/passenger/");
        passengerService.edit(passenger);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView getPassPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-passenger");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView create(@ModelAttribute("passenger") Passenger passenger) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/passenger/");
        passengerService.add(passenger);
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int idPassenger) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/passenger/");
        Passenger passenger = passengerService.getById(idPassenger);
        passengerService.delete(passenger);
        return modelAndView;
    }


}
