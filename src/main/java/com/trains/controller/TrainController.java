package com.trains.controller;

import com.trains.model.Train;
import com.trains.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/train")
public class TrainController {
    private TrainService trainService;

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping(value = "/")
    public ModelAndView getAllTrains() {
        List<Train> trains = trainService.allTrains();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trains");
        modelAndView.addObject("trainList",trains);
        return modelAndView;
    }


    @GetMapping(value = "/edit/{id}")
    public ModelAndView update (@PathVariable("id") int id) {
        Train train = trainService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit-Train");
        modelAndView.addObject("train",train);
        return modelAndView;
    }


    @PostMapping(value = "/edit")
    public ModelAndView editTrain(@ModelAttribute("train") Train train) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/train/");
        trainService.edit(train);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addTrainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-Train");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView create(@ModelAttribute("train") Train train) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/train/");
        trainService.add(train);
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int idTrain) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/train/");
        Train train = trainService.getById(idTrain);
        trainService.delete(train);
        return modelAndView;
    }

//    @DeleteMapping(value = "/delete/{id}")
//    public void delete(@PathVariable("id") int idTrain) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/");
//        Train train = trainService.getById(idTrain);
//        trainService.delete(train);
//       return modelAndView;
//    }

}
