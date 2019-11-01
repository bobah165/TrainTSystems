package com.trains.controller;

import com.trains.model.dto.PassengersFromTrainDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.service.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/train")
public class TrainController {
    private TrainService trainService;
    private String departureStation;
    private String arrivalStation;
    private static Logger logger = LoggerFactory.getLogger(TrainController.class);

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping(value = "/")
    public ModelAndView getAllTrains() {
        List<TrainDTO> trains = trainService.allTrains();
        logger.info("Get all trains");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-view/trains");
        logger.info("Read view /train-view/trains");
        modelAndView.addObject("trainList",trains);
        return modelAndView;
    }


    @GetMapping(value = "/edit/{id}")
    public ModelAndView getEditPage (@PathVariable("id") int id) {
        TrainDTO train = trainService.getById(id);
        logger.info("Get train by id = "+id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-view/edit-train");
        logger.info("Read file /train-view/edit-train");
        modelAndView.addObject("train",train);
        return modelAndView;
    }


    @PostMapping (value = "/edit")
    public ModelAndView update (@ModelAttribute("train") TrainDTO train) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/train/");
        trainService.edit(train);
        logger.info("Edit train "+train);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView getTrainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-view/add-train");
        return modelAndView;
    }



    @PostMapping(value = "/add")
    public ModelAndView create(@ModelAttribute("train") TrainDTO train,
                               @RequestParam String schedule) {
        ModelAndView modelAndView = new ModelAndView();
        trainService.add(train);
        logger.info("Add train "+train);
        if (schedule.equals("everyday")) {
            for (int i=0;i<14;i++) {
            LocalDate date = train.getDepartureDate().toLocalDate();
            date = date.plusDays(1);
            TrainDTO train1 = train;
            train.setDepartureDate(Date.valueOf(date));
            trainService.add(train1);
            }
        }

        modelAndView.setViewName("redirect:/train/");
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/train/");
        trainService.delByID(id);
        logger.info("Delete train by id = "+id);
        return modelAndView;
    }

    @GetMapping(value = "/passfromtrain/{id}")
    public ModelAndView getPassFromTrain (@PathVariable("id") int id) {
        List<PassengersFromTrainDTO> passengersFromTrainDTOS = trainService.getPassengerFromTrain(id);
        logger.info("Get passengers from train");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-view/get-passengers");
        logger.info("Read view /train-view/get-passengers");
        modelAndView.addObject("passfromtrainList",passengersFromTrainDTOS);
        return modelAndView;
    }

}
