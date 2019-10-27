package com.trains.controller;

import com.trains.model.dto.*;
import com.trains.model.entity.Ticket;
import com.trains.model.entity.Train;
import com.trains.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
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
        SearchStationDTO searchStationDTO = searchStationService.getById(1);
        TrainDTO trainDTO = new TrainDTO();
        for (TrainDTO train: trains){
            String depDate1 = train.getDepartureDate().toString();
            String depDate2 = searchStationDTO.getDepartureDate().toString();
            if ((depDate1.equals(depDate2)) && train.getId()==trainID) {
                trainDTO = train;
            }
        }

        Time departureTime = Time.valueOf("00:00:00");
        Time arrivalTime = Time.valueOf("00:00:00");
        List<TrainWayDTO> trainWayDTOS = trainWayService.allWays();
        for (TrainWayDTO trainWayDTO:trainWayDTOS){
            int currentWay = trainDTO.getTrainWay().getNumberWay();
            if (trainWayDTO.getNumberWay()== currentWay){
                if (trainWayDTO.getStation().getNameStation().equals(searchStationDTO.getDepartureStation())){
                    departureTime=Time.valueOf(trainWayDTO.getShedule());
                }
                if(trainWayDTO.getStation().getNameStation().equals(searchStationDTO.getArrivalStation())){
                    arrivalTime = Time.valueOf(trainWayDTO.getShedule());
                }
            }
        }

        TicketInformDTO ticketInform = new TicketInformDTO();
        ticketInform.setId(1); // по ID пользователя

        ticketInform.setIdTrain(trainDTO.getId());
        ticketInform.setDepartureStation(searchStationDTO.getDepartureStation());
        ticketInform.setArrivalStation(searchStationDTO.getArrivalStation());
        ticketInform.setArrivalDate(Date.valueOf("1990-01-01"));
        ticketInform.setDepartureDate(searchStationDTO.getDepartureDate());
        ticketInform.setDepartureTime(departureTime.toString());
        ticketInform.setArrivalTime(arrivalTime.toString());

        ticketInform.setBirthday(Date.valueOf("1990-01-01"));
        ticketInform.setName("none");
        ticketInform.setSurname("none");


        ticketInformService.add(ticketInform);
        modelAndView.setViewName("passenger-view/buy-ticket");


        return modelAndView;
    }

    @PostMapping(value = "/")
    public ModelAndView getPassengerInformation(@RequestParam("name") String name,
                                                @RequestParam("surname") String surname,
                                                @RequestParam("birthday") Date birthday) {
        ModelAndView modelAndView = new ModelAndView();

        PassengerDTO passenger = passengerService.getById(1);//будет ID зарегистрированного пользователя
        int i = passengerService.getPassengerId(name,surname,birthday);
        if (i<0) {
            passenger.setBirthday(birthday);
            passenger.setName(name);
            passenger.setSurname(surname);
            passengerService.edit(passenger);
        } else {
            passenger = passengerService.getById(passengerService.getPassengerId(name, surname, birthday));
        }

        TicketInformDTO ticketInformDTO = ticketInformService.getById(1); // будет ID зарегистрированного пользователя
        ticketInformDTO.setName(name);
        ticketInformDTO.setSurname(surname);
        ticketInformDTO.setBirthday(birthday);
        ticketInformDTO.setIdPassenger(passenger.getId());

        ticketInformService.edit(ticketInformDTO);

        // проверка покупки билета за 10 минут
        if((LocalTime.parse(ticketInformDTO.getDepartureTime()).minusMinutes(10)).isAfter((new Time(System.currentTimeMillis())).toLocalTime())) {
            modelAndView.setViewName("redirect:/ticket/message/");
            return modelAndView;
        }

        TrainDTO train = trainService.getById(ticketInformDTO.getIdTrain());

        // проверка на зарегистртрованных пассажиров
        List<TicketDTO> ticketDTOS = ticketService.allTickets();
        for (TicketDTO ticketDTO: ticketDTOS) {
            boolean b = ticketDTO.getPassenger().getName().equals(passenger.getName());
            boolean b1 = ticketDTO.getPassenger().getSurname().equals(passenger.getSurname());
            boolean b2 = ticketDTO.getPassenger().getBirthday().isEqual(passenger.getBirthday().toLocalDate());
            boolean b3 = ticketDTO.getTrain().getId()==train.getId();
            if (b&&b1&&b2&&b3){
                modelAndView.setViewName("redirect:/ticket/message/");
                return modelAndView;
            }
        }

        //добавление билета
       ticketService.addTicketByTrainDTOPassengerDTO(train,passenger);

        // учет добавленного билета в количестве свободных мест от станции до станции
        List<TrainWayDTO> trainOneWAy = new ArrayList<>();
        List<TrainWayDTO> trainWayDTOS = trainWayService.allWays();
        for (TrainWayDTO trainWayDTO: trainWayDTOS){
            trainOneWAy.add(trainWayDTO);
            }

        // поиск станций на которые покупается билет
        int numberDepartureStaitionInList =0;
        int numberArrivlStaitionInList =0;
        int counter =0;
        for (TrainWayDTO trainWayDTO: trainOneWAy) {
            counter++;
            if(trainWayDTO.getStation().getNameStation().equals(ticketInformDTO.getDepartureStation())) {
                if(trainWayDTO.getFreeSeats()>0) {
                    trainWayDTO.setFreeSeats(train.getCountSits() - 1);
                    trainWayService.edit(trainWayDTO);
                    numberDepartureStaitionInList = counter;
                }
            }
            if (trainWayDTO.getStation().getNameStation().equals(ticketInformDTO.getArrivalStation())) {
                    numberArrivlStaitionInList = counter;
            }
        }

        if(++numberDepartureStaitionInList!=numberArrivlStaitionInList) {

            for (int j = numberDepartureStaitionInList; j<numberArrivlStaitionInList;j++ ) {
                if(trainOneWAy.get(j).getFreeSeats()>0) {
                    trainOneWAy.get(j).setFreeSeats(train.getCountSits() - 1);
                    trainWayService.edit(trainOneWAy.get(j));
                }
            }
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
        return modelAndView;
    }

}
