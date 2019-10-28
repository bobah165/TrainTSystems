package com.trains.controller;

import com.trains.model.dto.*;
import com.trains.model.entity.FreeSeats;
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
    private FreeSeatsService freeSeatsService;

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

        // проверка на зарегистртрованных пассажиров
        TicketInformDTO ticketInformDTO = ticketInformService.getById(1); // будет ID зарегистрированного пользователя
        TrainDTO train = trainService.getById(ticketInformDTO.getIdTrain());
        List<TicketDTO> ticketDTOS = ticketService.allTickets();
        for (TicketDTO ticketDTO: ticketDTOS) {
            boolean b = ticketDTO.getPassenger().getName().equals(name);
            boolean b1 = ticketDTO.getPassenger().getSurname().equals(surname);
            boolean b2 = ticketDTO.getPassenger().getBirthday().isEqual(birthday.toLocalDate());
            boolean b3 = ticketDTO.getTrain().getId()==train.getId();
            if (b&&b1&&b2&&b3){
                modelAndView.setViewName("redirect:/ticket/message/");
                return modelAndView;
            }
        }

        PassengerDTO passenger = passengerService.getById(1);//будет ID зарегистрированного пользователя
       // PassengerDTO passenger = new PassengerDTO();
        int i = passengerService.getPassengerId(name,surname,birthday);
        if (i<0) {
            passenger.setBirthday(birthday);
            passenger.setName(name);
            passenger.setSurname(surname);

//            passenger.setLogin("none");
//            passenger.setPassword("none");
//            passenger.setUser("passenger");
//            passenger.setEmail(passengerLogin.getEmail());

            passengerService.edit(passenger);
            //passengerService.edit(passenger);
        } else {
            passenger = passengerService.getById(passengerService.getPassengerId(name, surname, birthday));
        }


        ticketInformDTO.setName(name);
        ticketInformDTO.setSurname(surname);
        ticketInformDTO.setBirthday(birthday);
        ticketInformDTO.setIdPassenger(passenger.getId());

        ticketInformService.edit(ticketInformDTO);

        // проверка покупки билета за 10 минут
        LocalTime depTime10minutes = LocalTime.parse(ticketInformDTO.getDepartureTime()).minusMinutes(10);
        LocalTime depTime = LocalTime.parse(ticketInformDTO.getDepartureTime());
        LocalTime currentTime = new Time(System.currentTimeMillis()).toLocalTime();
        if(currentTime.isAfter(depTime10minutes)&&currentTime.isBefore(depTime)) {
            modelAndView.setViewName("redirect:/ticket/message/");
            return modelAndView;
        }


        //добавление билета
       ticketService.addTicketByTrainDTOPassengerDTO(train,passenger);


        // учет добавленного билета в количестве свободных мест от станции до станции
        //находим все станции для маршрута поезда
        List<TrainWayDTO> trainOneWAy = new ArrayList<>();
        List<TrainWayDTO> trainWayDTOS = trainWayService.allWays();
        for (TrainWayDTO trainWayDTO: trainWayDTOS){
            if (trainWayDTO.getNumberWay()==train.getTrainWay().getNumberWay())
            trainOneWAy.add(trainWayDTO);
            }

        //добавляем данные о поезде в таблицу free_sets
        List<FreeSeatsDTO> freeSeatsDTOS = freeSeatsService.allSeats();
        List<FreeSeatsDTO> freeSeatInWay = new ArrayList<>();
        for (FreeSeatsDTO freeSeatsDTO: freeSeatsDTOS){
            for(TrainWayDTO trainWayDTO:trainOneWAy) {
                if (freeSeatsDTO.getIdTrain() == train.getId()
                        && freeSeatsDTO.getStationName().equals(trainWayDTO.getStation().getNameStation())) {
                    freeSeatInWay.add(freeSeatsDTO);
                }
            }
        }
        // если в таблице нет информации то заполняем ее
        if (freeSeatInWay.isEmpty()) {
            for (TrainWayDTO trainWayDTO:trainOneWAy) {
                FreeSeatsDTO freeSeats = new FreeSeatsDTO();
                freeSeats.setStationName(trainWayDTO.getStation().getNameStation());
                freeSeats.setIdTrain(train.getId());
                freeSeats.setFreeSeats(train.getCountSits());
                freeSeatsService.add(freeSeats);
            }
        }

        // поиск станций на которые покупается билет

        for (FreeSeatsDTO freeSeatsDTO: freeSeatInWay) {
            // поиск станции отправления
            if(freeSeatsDTO.getStationName().equals(ticketInformDTO.getDepartureStation())) {
                if(freeSeatsDTO.getFreeSeats()>0) {
                    freeSeatsDTO.setFreeSeats(freeSeatsDTO.getFreeSeats() - 1);
                    freeSeatsService.edit(freeSeatsDTO);
                }
            }
        }

        FreeSeatsDTO freeSeatsDTOdeparture = freeSeatsService.getByStationAndTrainID(ticketInformDTO.getDepartureStation(),ticketInformDTO.getIdTrain());
        FreeSeatsDTO freeSeatsDTOarrival = freeSeatsService.getByStationAndTrainID(ticketInformDTO.getArrivalStation(),ticketInformDTO.getIdTrain());
        if(((freeSeatsDTOdeparture.getId()+1)!=freeSeatsDTOarrival.getId())||
                ((freeSeatsDTOdeparture.getId()-1)!=freeSeatsDTOarrival.getId())) {

                int depNumber =  freeSeatInWay.indexOf(freeSeatsDTOdeparture);
                int arrNumber = freeSeatInWay.indexOf(freeSeatsDTOarrival);
            if (depNumber>arrNumber) {
                for (int j = (depNumber-1); j>arrNumber;j-- ) {
                    if(freeSeatInWay.get(j).getFreeSeats()>0) {
                        freeSeatInWay.get(j).setFreeSeats(freeSeatInWay.get(j).getFreeSeats() - 1);
                        freeSeatsService.edit(freeSeatInWay.get(j));
                    }
                }
            }

            if (depNumber<arrNumber) {
                for (int j = depNumber+1; j<arrNumber;j++ ) {
                    if(freeSeatInWay.get(j).getFreeSeats()>0) {
                        freeSeatInWay.get(j).setFreeSeats(freeSeatInWay.get(j).getFreeSeats()-1);
                        freeSeatsService.edit(freeSeatInWay.get(j));
                    }
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
