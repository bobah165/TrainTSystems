package com.trains.controller;

import com.trains.model.dto.PassengerDTO;
import com.trains.model.dto.PassengersFromTrainDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.dto.TrainFromStationAToB;
import com.trains.model.entity.Train;
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
    private String departureStation;
    private String arrivalStation;

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping(value = "/")
    public ModelAndView getAllTrains() {
        List<TrainDTO> trains = trainService.allTrains();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-view/trains");
        modelAndView.addObject("trainList",trains);
        return modelAndView;
    }


    @GetMapping(value = "/edit/{id}")
    public ModelAndView getEditPage (@PathVariable("id") int id) {
        TrainDTO train = trainService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-view/edit-train");
        modelAndView.addObject("train",train);
        return modelAndView;
    }


    @PostMapping (value = "/edit")
    public ModelAndView update (@ModelAttribute("train") TrainDTO train) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/train/");
        trainService.edit(train);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView getTrainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-view/add-train");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView create(@ModelAttribute("train") TrainDTO train) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/train/");
        trainService.add(train);
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/train/");
        trainService.delByID(id);
        return modelAndView;
    }

    @GetMapping(value = "/passfromtrain/{id}")
    public ModelAndView getPassFromTrain (@PathVariable("id") int id) {
        List<PassengersFromTrainDTO> passengersFromTrainDTOS = trainService.getPassengerFromTrain(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-view/get-passengers");
        modelAndView.addObject("passfromtrainList",passengersFromTrainDTOS);
        return modelAndView;
    }

//    @GetMapping(value = "/trainfromstations")
//    public ModelAndView getTrainPageWithStations() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("train-view/find-train-by-stations");
//        return modelAndView;
//    }
//
//    @PostMapping(value = "/trainfromstations")
//    public ModelAndView getTrainsFromStations(@RequestParam String stationA,
//                                              @RequestParam String stationB) {
//        ModelAndView modelAndView = new ModelAndView();
//        departureStation = stationA;
//        arrivalStation = stationB;
//        modelAndView.setViewName("redirect:/train/trainstation");
//        return modelAndView;
//    }
//
//    @GetMapping(value = "/trainstation")
//    public ModelAndView getTrainstAB (){
//        ModelAndView modelAndView = new ModelAndView();
//        List<TrainFromStationAToB> trainFromStationAToBS =  trainService.getTrainsFromStations(departureStation,arrivalStation);
//        modelAndView.setViewName("train-view/get-train-from-stations");
//        modelAndView.addObject("trainListfromAtoB",trainFromStationAToBS);
//        return modelAndView;
//    }

}
