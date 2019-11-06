package com.trains.controller;


import com.trains.model.dto.PassengerDTO;
import com.trains.model.dto.SearchStationDTO;
import com.trains.model.dto.TrainFromStationAToB;
import com.trains.service.SearchStationService;
import com.trains.service.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Controller
@RequestMapping("/findtrain")
public class SearchStationController {
    private SearchStationService searchStationService;
    private static Logger logger = LoggerFactory.getLogger(SearchStationController.class);

    @Autowired
    public void setSearchStationService(SearchStationService searchStationService) {
        this.searchStationService = searchStationService;
    }

    //форма для нахождения поездов слудующих от одной станции к другой
    @GetMapping(value = "/trainfromstations")
    public ModelAndView getTrainPageWithStations() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-view/find-train");
        logger.info("Read view train-view/find-train");
        return modelAndView;
    }

    @PostMapping(value = "/trainfromstations")
    public ModelAndView getTrainsFromStations(@RequestParam String stationA,
                                              @RequestParam String stationB,
                                              @RequestParam Date departureDate,
                                              @RequestParam String startTime,
                                              @RequestParam String endTime) {
        ModelAndView modelAndView = new ModelAndView();
        searchStationService.addInformationAboutSearch(stationA,stationB,departureDate.toLocalDate(),startTime,endTime);
        logger.info("Edit or add search station ");
        modelAndView.setViewName("redirect:/findtrain/trainstation/");
        return modelAndView;
    }


    //таблица поездов по результатам поиска
    @GetMapping(value = "/trainstation")
    public ModelAndView getTrainstAB (){
        ModelAndView modelAndView = new ModelAndView();
        int idCurrentPassenger = ((PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        SearchStationDTO searchStationDTO = searchStationService.getById(idCurrentPassenger);
        logger.info("Get by id search sttion "+searchStationDTO);
        List<TrainFromStationAToB> trainFromStationAToBS =
                searchStationService.getTrainsFromStations(searchStationDTO.getDepartureStation(), searchStationDTO.getArrivalStation(),
                        searchStationDTO.getStartTime(),searchStationDTO.getEndTime(),searchStationDTO.getDepartureDate().toLocalDate());
        modelAndView.setViewName("train-view/get-train-from-stations");
        modelAndView.addObject("trainListfromAtoB",trainFromStationAToBS);
        return modelAndView;
    }

}
