package com.trains.controller;

import com.trains.model.dto.*;
import com.trains.service.*;
import com.trains.util.EmailSender;
import com.trains.util.PDFWriterForTicket;
import com.trains.util.validator.TrainDTOValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/train")
public class TrainController {
    private TrainDTOValidator trainDTOValidator;
    private TrainService trainService;
    private TicketService ticketService;
    private TicketInformService ticketInformService;
    private int page;
    private static Logger logger = LoggerFactory.getLogger(TrainController.class);

    @Autowired
    public void setTrainDTOValidator(TrainDTOValidator trainDTOValidator) {
        this.trainDTOValidator = trainDTOValidator;
    }

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @Autowired
    public void setTicketInformService(TicketInformService ticketInformService) {
        this.ticketInformService = ticketInformService;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping(value = "/")
    public ModelAndView getAllTrains(@RequestParam(defaultValue = "1") int page) {
        ModelAndView modelAndView = new ModelAndView();
        List<TrainDTO> trainList = trainService.allTrainsPagination(page);
        int trainCount = trainService.getCountOfPage();
        int pageCount = (trainCount+9)/10;
        modelAndView.setViewName("train-view/trains");
        modelAndView.addObject("page",page);
        modelAndView.addObject("trainList",trainList);
        modelAndView.addObject("trainCount",trainCount);
        modelAndView.addObject("pageCount",pageCount);
        this.page = page;
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
    public ModelAndView update (@ModelAttribute("train") @Valid TrainDTO train, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        trainDTOValidator.validate(train,result);
        if(result.hasErrors()) {
            modelAndView.setViewName("redirect:/train/addeditmessage/");
            return modelAndView;
        }
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
    public ModelAndView create(@ModelAttribute("train") @Valid TrainDTO train, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        trainDTOValidator.validate(train,result);
        if(result.hasErrors()) {
            modelAndView.setViewName("redirect:/train/addeditmessage/");
            return modelAndView;
        }
        trainService.add(train);
        logger.info("Add train "+train);
        modelAndView.setViewName("redirect:/train/");
        return modelAndView;
    }


    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        List<PassengersFromTrainDTO> passengersFromTrainDTOS = trainService.getPassengerFromTrain(id);
        ModelAndView modelAndView = new ModelAndView();
        if (passengersFromTrainDTOS.isEmpty()) {
            modelAndView.setViewName("redirect:/train/?page=" + this.page);
            trainService.delByID(id);
            logger.info("Delete train by id = "+id);}
        else modelAndView.setViewName("redirect:/train/message/");
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



    @GetMapping(value = "/message")
    public ModelAndView getMessage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-view/train-message");
        return modelAndView;
    }



    @GetMapping(value = "/addeditmessage")
    public ModelAndView getAddEditMessage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-view/train-add-edit-message");
        return modelAndView;
    }



    @GetMapping(value = "/buy/{trainID}")
    public ModelAndView addTicket(@PathVariable("trainID") int trainID){
        ModelAndView modelAndView = new ModelAndView();
        PassengerDTO passengerDTO = (PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        trainService.addTicketInfByTrainId(trainID);
        modelAndView.addObject("passenger",passengerDTO);
        modelAndView.setViewName("passenger-view/buy-ticket");
        logger.info("Add ticket information by Train ID");

        return modelAndView;
    }


    @PostMapping(value = "/buy")
    public ModelAndView getPassengerInformation(@RequestParam("name") String name,
                                                @RequestParam("surname") String surname,
                                                @RequestParam("birthday") Date birthday) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            PassengerDTO passenger = trainService.сheckPassengerByNameSurnameBirthday(name, surname, birthday);
            trainService.checkFreeSeatsInTrain();
            ticketService.addTicketByTrainDTOPassengerDTO(passenger);
        } catch (NullPointerException ex) {
            logger.error("This passenger already exist");
            modelAndView.setViewName("redirect:/ticket/message/");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/train/buy/ticket/");
        return modelAndView;
    }



    @GetMapping(value = "/buy/ticket")
    public ModelAndView getInfoTicket() {
        ModelAndView modelAndView = new ModelAndView();
        int idCurrentPassenger = ((PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        TicketInformDTO ticketList = ticketInformService.getById(idCurrentPassenger);
        PDFWriterForTicket.writePDFOfticket(ticketList);
        trainService.deleteIfNoPassengerInTrain();
        modelAndView.setViewName("ticket-info/ticket-info");
        modelAndView.addObject("ticketInfo",ticketList);
        ticketInformService.delete(ticketList);
        EmailSender.sendMail();
        logger.info("Delete ticket information "+ticketList);
        return modelAndView;
    }

}
