package com.trains.controller;

import com.trains.model.dto.TrainFromStationDTO;
import com.trains.service.TrainFromStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class TrainFromStaionController {
    private TrainFromStationService trainFromStationService;

    @Autowired
    public void setTrainFromStationService(TrainFromStationService trainFromStationService) {
        this.trainFromStationService = trainFromStationService;
    }

    @GetMapping("/")
    @ResponseBody
    public List<TrainFromStationDTO> getSchedule() {
        trainFromStationService.fillTheTable();
        return trainFromStationService.getAllTrains();
    }
}
