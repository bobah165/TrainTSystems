package com.trains.controller;

import com.trains.model.Station;
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
        List<Station> stations = stationService.allStations();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("station-view/stations");
        modelAndView.addObject("stationsList", stations);
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView update (@PathVariable("id") int id) {
        Station station = stationService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("station-view/edit-station");
        modelAndView.addObject("station",station);
        return modelAndView;
    }


    @PostMapping(value = "/edit")
    public ModelAndView editPassenger(@ModelAttribute("station") Station station) {
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
    public ModelAndView create(@ModelAttribute("station") Station station) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/station/");
        stationService.add(station);
        return modelAndView;
    }

    @GetMapping (value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/station/");
        Station station = stationService.getById(id);
        stationService.delete(station);
        return modelAndView;
    }

}
