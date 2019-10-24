package com.trains.controller;

import com.trains.model.dto.TrainWayDTO;
import com.trains.service.TrainWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/way")
public class TrainWayController {
    private TrainWayService trainWayService;

    @Autowired
    public void setTrainWayService(TrainWayService trainWayService) {
        this.trainWayService = trainWayService;
    }

    @GetMapping(value = "/")
    public ModelAndView getAllTrains() {
        List<TrainWayDTO> ways = trainWayService.allWays();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-way-view/ways");
        modelAndView.addObject("wayList",ways);
        return modelAndView;
    }


    @GetMapping(value = "/edit/{id}")
    public ModelAndView getEditPage (@PathVariable("id") int id) {
        TrainWayDTO way = trainWayService.getById(id);
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
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView getTrainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-way-view/add-way");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView create(@ModelAttribute("way") TrainWayDTO way) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/way/");
        trainWayService.add(way);
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/way/");
        trainWayService.delByID(id);
        return modelAndView;
    }
}
