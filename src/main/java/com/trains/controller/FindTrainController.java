package com.trains.controller;

import com.trains.model.dto.SearchStationDTO;
import com.trains.model.dto.TrainFromStationAToB;
import com.trains.service.SearchStationService;
import com.trains.service.TrainService;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Controller
@RequestMapping("/findtrain")
public class FindTrainController {
    private TrainService trainService;
    private SearchStationService searchStationService;
    private static Logger logger =Logger.getLogger(FindTrainController.class);


    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @Autowired
    public void setSearchStationService(SearchStationService searchStationService) {
        this.searchStationService = searchStationService;
    }

    //форма для нахождения поездов слудующих от одной станции к другой
    @GetMapping(value = "/trainfromstations")
    public ModelAndView getTrainPageWithStations() {
        ModelAndView modelAndView = new ModelAndView();
      //  modelAndView.setViewName("train-view/find-train-by-stations");
        modelAndView.setViewName("train-view/new-train");
        logger.debug("Read view train-view/new-train");
        return modelAndView;
    }

    @PostMapping(value = "/trainfromstations")
    public ModelAndView getTrainsFromStations(@RequestParam String stationA,
                                              @RequestParam String stationB,
                                              @RequestParam Date departureDate,
                                              @RequestParam String startTime,
                                              @RequestParam String endTime) {
        ModelAndView modelAndView = new ModelAndView();
        SearchStationDTO searchStationDTO = new SearchStationDTO();
        searchStationDTO.setId(1); // надо подставить ID залогиневшегося пользователя
        searchStationDTO.setDepartureDate(departureDate);
        searchStationDTO.setStartTime(startTime);
        searchStationDTO.setDepartureStation(stationA);
        searchStationDTO.setArrivalStation(stationB);
        searchStationDTO.setEndTime(endTime);
        modelAndView.setViewName("redirect:/findtrain/trainstation/");
        if (!searchStationService.allTrains().isEmpty()) {
            searchStationService.edit(searchStationDTO);
            logger.info("Edit search station "+searchStationDTO);
        } else searchStationService.add(searchStationDTO);

        return modelAndView;
    }



    //таблица поездов по результатам поиска
    @GetMapping(value = "/trainstation")
    public ModelAndView getTrainstAB (){
        ModelAndView modelAndView = new ModelAndView();
        SearchStationDTO searchStationDTO = searchStationService.getById(1); //подставить ID залогинившегося пользователя
        logger.info("Get by id search sttion "+searchStationDTO);
        List<TrainFromStationAToB> trainFromStationAToBS =
                trainService.getTrainsFromStations(searchStationDTO.getDepartureStation(), searchStationDTO.getArrivalStation(),
                        Time.valueOf(searchStationDTO.getStartTime()),Time.valueOf(searchStationDTO.getEndTime()),searchStationDTO.getDepartureDate().toLocalDate());
        modelAndView.setViewName("train-view/get-train-from-stations");
        modelAndView.addObject("trainListfromAtoB",trainFromStationAToBS);
        return modelAndView;
    }

}
