package com.trains.controller;

import com.trains.model.dto.*;

import com.trains.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;


@Controller
@RequestMapping("/buy")
public class BuyTicketController {
    private SearchStationService searchStationService;
    private TrainService trainService;
    private TicketInformService ticketInformService;
    private TrainWayService trainWayService;
    private PassengerService passengerService;
    private TicketService ticketService;
    private FreeSeatsService freeSeatsService;
    private static Logger logger = LoggerFactory.getLogger(BuyTicketController.class);

    @Autowired
    public void setFreeSeatsService(FreeSeatsService freeSeatsService) {
        this.freeSeatsService = freeSeatsService;
    }

    @Autowired
    public void setTrainWayService(TrainWayService trainWayService) {
        this.trainWayService = trainWayService;
    }

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @Autowired
    public void setTicketInformService(TicketInformService ticketInformService) {
        this.ticketInformService = ticketInformService;
    }

    @Autowired
    public void setSearchStationService(SearchStationService searchStationService) {
        this.searchStationService = searchStationService;
    }

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(value = "/{trainID}")
    public ModelAndView addTicket(@PathVariable("trainID") int trainID){
        ModelAndView modelAndView = new ModelAndView();
        List<TrainDTO> trains = trainService.allTrains();
        logger.info("Get all trains");
        SearchStationDTO searchStationDTO = searchStationService.getById(1);
        logger.info("Get information search station "+searchStationDTO);
        TrainDTO trainDTO = trainService.getTrainByDate(trains,searchStationDTO,trainID);
        List<TrainWayDTO> trainWayDTOS = trainWayService.allWays();
        logger.info("Get all ways");
        TicketInformDTO ticketInform = ticketInformService.fullInformation(searchStationDTO,trainWayDTOS,trainDTO);
        ticketInformService.add(ticketInform);
        logger.info("Add ticket information "+ticketInform);
        modelAndView.setViewName("passenger-view/buy-ticket");

        return modelAndView;
    }

    @PostMapping(value = "/")
    public ModelAndView getPassengerInformation(@RequestParam("name") String name,
                                                @RequestParam("surname") String surname,
                                                @RequestParam("birthday") Date birthday) {
        ModelAndView modelAndView = new ModelAndView();

        // проверка на наличие билета у зарегистртрованных пассажиров на поезд
        TicketInformDTO ticketInformDTO = ticketInformService.getById(1); // будет ID зарегистрированного пользователя
        logger.info("Get ticket information "+ticketInformDTO);
        TrainDTO train = trainService.getById(ticketInformDTO.getIdTrain()); //информация о поезде на который покупается билет
        logger.info("Get train in which passenger buy ticket "+train);

        if (ticketService.checkTicketByNameSurnameBirthday(name,surname,birthday, train)){
            modelAndView.setViewName("redirect:/ticket/message/");
            return modelAndView;
        };

        // добавление пассажира: если такой пассажир не зарегистрирован, то добавляем, если есть то вытаскиваем о нем
        // информацию из БД
        int i = passengerService.getPassengerId(name,surname,birthday);
        logger.info("Get passenger id by name, surname, birthday, id is "+i);
        if (i<0) {
            passengerService.addPassengerByNameSurnameDate(name, surname,birthday);
            logger.info("Add passenger by name "+name+" surname "+surname+" birthday "+birthday);
        }
        PassengerDTO passenger = passengerService.getById(passengerService.getPassengerId(name, surname, birthday));
        logger.info("Get passenger by id "+passenger);

        // заполняем информаци о пассажире в общую информационну таблицу о билете
        ticketInformDTO.setName(name);
        ticketInformDTO.setSurname(surname);
        ticketInformDTO.setBirthday(birthday);
        ticketInformDTO.setIdPassenger(passenger.getId());

        ticketInformService.edit(ticketInformDTO);
        logger.info("Edit ticket information "+ticketInformDTO);

        // проверка покупки билета за 10 минут до отправления поезда
        if(!ticketInformService.checkDeapartureTime(ticketInformDTO)) {
            modelAndView.setViewName("redirect:/ticket/message/");
            return modelAndView;
         }

        // проверка на наличие свободных мест, если места есть, то внесение изменений в БД
        if (freeSeatsService.checkFreeSeats(train,ticketInformDTO)) {
            //добавление билета
            ticketService.addTicketByTrainDTOPassengerDTO(train, passenger);
            logger.info("Add ticket by train " + train + " and passenger " + passenger);
        } else  {
            //вывод информации,что билет не куплен
            modelAndView.setViewName("redirect:/ticket/message/");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/buy/ticket/");
        return modelAndView;
    }

   @GetMapping(value = "/ticket")
    public ModelAndView getInfoTicket() {
        ModelAndView modelAndView = new ModelAndView();
        TicketInformDTO ticketList = ticketInformService.getById(1);
        modelAndView.setViewName("ticket-info/ticket-info");
        modelAndView.addObject("ticketInfo",ticketList);
        ticketInformService.delete(ticketList);
        logger.info("Delete ticket information "+ticketList);
        return modelAndView;
    }

}
