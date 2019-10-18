package com.trains.controller;

import com.trains.dto.TrainFromStationDTO;
import com.trains.service.TrainFromStationDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/trainfromstation")
public class TrainFromStationDTOController {
    private TrainFromStationDTOService trainFromStationDTOService;

    @Autowired
    public void setTrainFromStationDTOService(TrainFromStationDTOService trainFromStationDTOService) {
        this.trainFromStationDTOService = trainFromStationDTOService;
    }

    @GetMapping("/{id}")
    public ModelAndView getTatinFromStation (@PathVariable("id") int id) {
        List<TrainFromStationDTO> trainFromStation = trainFromStationDTOService.getTrainFromStation(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("station-view/get-trains");
        modelAndView.addObject("trainsList",trainFromStation);
        return modelAndView;
    }


}
