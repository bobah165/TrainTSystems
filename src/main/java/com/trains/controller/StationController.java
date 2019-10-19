package com.trains.controller;

import com.trains.model.dto.StationDTO;
import com.trains.model.dto.TrainFromStationDTO;
import com.trains.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/station")
public class StationController {
    private StationService stationService;

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping(value = "/")
    public ModelAndView allPassengers() {
        List<StationDTO> stations = stationService.allStations();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("station-view/stations");
        modelAndView.addObject("stationsList", stations);
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView getEditPage (@PathVariable("id") int id) {
        StationDTO station = stationService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("station-view/edit-station");
        modelAndView.addObject("station",station);
        return modelAndView;
    }


    @PostMapping(value = "/edit")
    public ModelAndView update(@ModelAttribute("station") StationDTO station) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/station/");
        stationService.edit(station);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView getPassPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("station-view/add-station");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView create(@ModelAttribute("station") StationDTO station) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/station/");
        stationService.add(station);
        return modelAndView;
    }


    @GetMapping (value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/station/");
        stationService.delByID(id);
        return modelAndView;
    }

    @GetMapping("/trainfromstation/{id}")
    public ModelAndView getTatinFromStation (@PathVariable("id") int id) {
        List<TrainFromStationDTO> trainFromStation = stationService.getTrainFromStation(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("station-view/get-trains");
        modelAndView.addObject("trainsList",trainFromStation);
        return modelAndView;
    }

}
