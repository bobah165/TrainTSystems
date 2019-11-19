package com.trains.controller;


import com.trains.model.dto.TrainWayDTO;
import com.trains.service.TrainWayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/way")
public class TrainWayController {
    private TrainWayService trainWayService;
    private static Logger logger = LoggerFactory.getLogger(TrainController.class);

    @Autowired
    public void setTrainWayService(TrainWayService trainWayService) {
        this.trainWayService = trainWayService;
    }

    @GetMapping(value = "/")
    public ModelAndView getAllTrains() {
        List<TrainWayDTO> ways = trainWayService.getAllWays();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-way-view/ways");
        modelAndView.addObject("wayList",ways);
        return modelAndView;
    }


    @GetMapping(value = "/edit/{id}")
    public ModelAndView getEditPage (@PathVariable("id") int id) {
        TrainWayDTO way = trainWayService.getById(id);
        logger.info("Get train way by id = "+id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-way-view/edit-way");
        modelAndView.addObject("way",way);
        return modelAndView;
    }


    @PostMapping(value = "/edit")
    public ModelAndView update (@ModelAttribute("way") TrainWayDTO way) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/way/");
        trainWayService.edit(way);
        logger.info("Edit way "+way);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView getTrainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-way-view/add-way");
        logger.info("Read view train-way-view/add-way");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView create(@ModelAttribute("way") TrainWayDTO way) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/way/");
        trainWayService.add(way);
        logger.info("Add way "+way);
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/way/");
        trainWayService.delByID(id);
        logger.info("Get train way by id = "+id);
        return modelAndView;
    }
}
