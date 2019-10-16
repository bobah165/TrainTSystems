package com.trains.controller;


import com.trains.dto.PassFromTrainDTO;
import com.trains.service.PassengerService;
import com.trains.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/passfromtrain")
public class PassFromTrainDTOController {
    private PassengerService passengerService;
    private TrainService trainService;

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("/{id}")
    public ModelAndView getPass(@PathVariable("id") int id) {
        List<PassFromTrainDTO> passengers = passengerService.getPass(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("passenger-view/get-passenger");
        modelAndView.addObject("passengersList",passengers);
        return modelAndView;
    }
}
