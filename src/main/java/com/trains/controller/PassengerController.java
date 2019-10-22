package com.trains.controller;


import com.trains.model.dto.PassengerDTO;
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
    List<PassengerDTO> passengers = passengerService.allPassengers();
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("passenger-view/passengers");
    modelAndView.addObject("passengersList",passengers);
    return modelAndView;
}


    @GetMapping(value = "/edit/{id}")
    public ModelAndView getEditPage (@PathVariable("id") int id) {
        PassengerDTO passenger = passengerService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("passenger-view/edit-passenger");
        modelAndView.addObject("passenger",passenger);
        return modelAndView;
    }


    @PostMapping(value = "/edit")
    public ModelAndView update (@ModelAttribute("passenger") PassengerDTO passenger) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/passenger/");
        passengerService.edit(passenger);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView getPassPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("passenger-view/add-passenger");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView create(@ModelAttribute("passenger") PassengerDTO passenger) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/passenger/");
        passengerService.add(passenger);
        return modelAndView;
    }

    @GetMapping (value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/passenger/");
        passengerService.delByID(id);
        return modelAndView;
    }

//    @GetMapping (value = "/buy")
//    public ModelAndView buyPageTicket(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("passenger-view/buy-ticket");
//        return modelAndView;
//    }
//
//    @PostMapping (value = "/buy")
//    public ModelAndView buyPassenger(@ModelAttribute("passenger") PassengerDTO passengerDTO) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/ticket/add/");
//        passengerService.add(passengerDTO);
//        return modelAndView;
//    }


}
