package com.trains.controller;


import com.trains.model.dto.StationDTO;
import com.trains.model.dto.TrainFromStationDTO;

import com.trains.service.StationService;
import com.trains.service.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/station")
public class StationController {
    private StationService stationService;
    private TrainService trainService;
    private static Logger logger = LoggerFactory.getLogger(StationController.class);

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping(value = "/")
    public ModelAndView allPassengers() {
        List<StationDTO> stations = stationService.allStations();
        logger.info("Get all stations");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("station-view/stations");
        logger.info("Read view /station-view/stations");
        modelAndView.addObject("stationsList", stations);
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView getEditPage (@PathVariable("id") int id) {
        StationDTO station = stationService.getById(id);
        logger.info("Get station by id = "+id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("station-view/edit-station");
        logger.info("Read view /station-view/edit-station");
        modelAndView.addObject("station",station);
        return modelAndView;
    }


    @PostMapping(value = "/edit")
    public ModelAndView update(@ModelAttribute("station") StationDTO station) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/station/");
        stationService.edit(station);
        logger.info("Edit station "+station);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView getPassPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("station-view/add-station");
        logger.info("Read view /station-view/add-station");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView create(@ModelAttribute("station") StationDTO station) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/station/");
        stationService.add(station);
        logger.info("Add station "+station);
        return modelAndView;
    }


    @GetMapping (value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/station/");
        stationService.delByID(id);
        logger.info("Delete station by id = "+id);
        return modelAndView;
    }



    @GetMapping(value = "/findtrains")
    public ModelAndView findTrainsPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("station-view/find-trains");
        return modelAndView;
    }

    @PostMapping(value = "/findtrains")
    public ModelAndView getTrains(@RequestParam String nameStation,
                                  @RequestParam Date departureDate,
                                  @RequestParam String startTime,
                                  @RequestParam String endTime) {
        ModelAndView modelAndView = new ModelAndView();
        StationDTO station = stationService.getByName(nameStation);
        logger.info("get station "+station+" by name "+nameStation);
        List<TrainFromStationDTO> trainFromStation = stationService.getTrainFromStation(station.getId(),departureDate,LocalTime.parse(startTime),LocalTime.parse(endTime));
        modelAndView.setViewName("station-view/get-trains");
        modelAndView.addObject("trainsList",trainFromStation);
        return modelAndView;
    }

}
