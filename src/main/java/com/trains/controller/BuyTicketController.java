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
import java.sql.Time;
import java.time.LocalTime;
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
//        LocalTime depTime10minutes = LocalTime.parse(ticketInformDTO.getDepartureTime()).minusMinutes(10);
//        LocalTime depTime = LocalTime.parse(ticketInformDTO.getDepartureTime());
//        LocalTime currentTime = new Time(System.currentTimeMillis()).toLocalTime();
//        if(currentTime.isAfter(depTime10minutes)&&currentTime.isBefore(depTime)) {
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

        // учет добавленного билета в количестве свободных мест от станции до станции
//        //находим все станции для маршрута поезда
//        List<TrainWayDTO> trainOneWAy = new ArrayList<>();
//        List<TrainWayDTO> trainWayDTOS = trainWayService.allWays();
//        logger.info("Get all trains ways");
//        for (TrainWayDTO trainWayDTO: trainWayDTOS){
//            if (trainWayDTO.getNumberWay()==train.getTrainWay().getNumberWay())
//            trainOneWAy.add(trainWayDTO);
//            }
//
//        //добавляем данные о поезде в таблицу free_sets
//        List<FreeSeatsDTO> freeSeatsDTOS = freeSeatsService.allSeats();
//        logger.info("Get all free seats");
//        List<FreeSeatsDTO> freeSeatInWay = new ArrayList<>();
//        for (FreeSeatsDTO freeSeatsDTO: freeSeatsDTOS){
//            for(TrainWayDTO trainWayDTO:trainOneWAy) {
//                if (freeSeatsDTO.getIdTrain() == train.getId()
//                        && freeSeatsDTO.getStationName().equals(trainWayDTO.getStation().getNameStation())) {
//                    freeSeatInWay.add(freeSeatsDTO);
//                    logger.info("Add free seats information "+freeSeatsDTO);
//                }
//            }
//        }
//
//        // если в таблице нет информации о свободных местах то заполняем ее
//        if (freeSeatInWay.isEmpty()) {
//            for (TrainWayDTO trainWayDTO:trainOneWAy) {
//                FreeSeatsDTO freeSeats = new FreeSeatsDTO();
//                freeSeats.setStationName(trainWayDTO.getStation().getNameStation());
//                freeSeats.setIdTrain(train.getId());
//                freeSeats.setFreeSeats(train.getCountSits());
//                freeSeatsService.add(freeSeats);
//            }
//        }
//
//        // поиск станций на которые покупается билет
//        for (FreeSeatsDTO freeSeatsDTO: freeSeatInWay) {
//
//            // поиск станции отправления
//            if(freeSeatsDTO.getStationName().equals(ticketInformDTO.getDepartureStation())) {
//                if(freeSeatsDTO.getFreeSeats()>0) {
//                    freeSeatsDTO.setFreeSeats(freeSeatsDTO.getFreeSeats() - 1);
//                    freeSeatsService.edit(freeSeatsDTO);
//                    logger.info("Edit free seats "+freeSeatsDTO);
//                }
//            }
//        }
//
//        // добавление билетов (уменьшение числа свободных мест)
//        FreeSeatsDTO freeSeatsDTOdeparture = freeSeatsService.getByStationAndTrainID(ticketInformDTO.getDepartureStation(),ticketInformDTO.getIdTrain());
//        FreeSeatsDTO freeSeatsDTOarrival = freeSeatsService.getByStationAndTrainID(ticketInformDTO.getArrivalStation(),ticketInformDTO.getIdTrain());
//        if(((freeSeatsDTOdeparture.getId()+1)!=freeSeatsDTOarrival.getId())||
//                ((freeSeatsDTOdeparture.getId()-1)!=freeSeatsDTOarrival.getId())) {
//
//            int depNumber =  freeSeatInWay.indexOf(freeSeatsDTOdeparture);
//            int arrNumber = freeSeatInWay.indexOf(freeSeatsDTOarrival);
//            if (depNumber>arrNumber) {
//                for (int j = (depNumber-1); j>arrNumber;j-- ) {
//                    if(freeSeatInWay.get(j).getFreeSeats()>0) {
//                        freeSeatInWay.get(j).setFreeSeats(freeSeatInWay.get(j).getFreeSeats() - 1);
//                        freeSeatsService.edit(freeSeatInWay.get(j));
//                    } else {
//                        modelAndView.setViewName("redirect:/ticket/message/");
//                        return modelAndView;
//                    }
//                }
//            }
//
//            if (depNumber<arrNumber) {
//                for (int j = depNumber+1; j<arrNumber;j++ ) {
//                    if(freeSeatInWay.get(j).getFreeSeats()>0) {
//                        freeSeatInWay.get(j).setFreeSeats(freeSeatInWay.get(j).getFreeSeats()-1);
//                        freeSeatsService.edit(freeSeatInWay.get(j));
//                    } else {
//                        modelAndView.setViewName("redirect:/ticket/message/");
//                        return modelAndView;
//                    }
//                }
//            }
//        }
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
