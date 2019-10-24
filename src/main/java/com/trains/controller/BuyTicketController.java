package com.trains.controller;

import com.trains.model.dto.*;
import com.trains.model.entity.Timetable;
import com.trains.service.*;
import com.trains.util.mapperForDTO.PassengerMapper;
import com.trains.util.mapperForDTO.StationMapper;
import com.trains.util.mapperForDTO.TrainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/buy")
public class BuyTicketController {
    private PassengerService passengerService;
    private TrainService trainService;
    private TicketService ticketService;
    private StationService stationService;
    private TimetableService timetableService;

    private String departureStation;
    private String arrivalStation;


    private int trainID;
    private TrainMapper trainMapper;
    private PassengerMapper passengerMapper;
    private StationMapper stationMapper;

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    public void setTimetableService(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @Autowired
    public void setTrainMapper(TrainMapper trainMapper) {
        this.trainMapper = trainMapper;
    }

    @Autowired
    public void setPassengerMapper(PassengerMapper passengerMapper) {
        this.passengerMapper = passengerMapper;
    }

    @Autowired
    public void setStationMapper(StationMapper stationMapper) {
        this.stationMapper = stationMapper;
    }

    //форма для нахождения поездов слудующих от одной станции к другой
    @GetMapping(value = "/trainfromstations")
    public ModelAndView getTrainPageWithStations() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("train-view/find-train-by-stations");
        return modelAndView;
    }

    @PostMapping(value = "/trainfromstations")
    public ModelAndView getTrainsFromStations(@RequestParam String stationA,
                                              @RequestParam String stationB) {
        ModelAndView modelAndView = new ModelAndView();
        departureStation = stationA;
        arrivalStation = stationB;
        modelAndView.setViewName("redirect:/buy/trainstation");
        return modelAndView;
    }



    //таблица поездов по результатам поиска
//    @GetMapping(value = "/trainstation")
//    public ModelAndView getTrainstAB (){
//        ModelAndView modelAndView = new ModelAndView();
//        List<TrainFromStationAToB> trainFromStationAToBS =  trainService.getTrainsFromStations(departureStation,arrivalStation);
//        modelAndView.setViewName("train-view/get-train-from-stations");
//        modelAndView.addObject("trainListfromAtoB",trainFromStationAToBS);
//        return modelAndView;
//    }

    //начало процесса оформления билета, форма для заполнения данных пассажира
    @GetMapping(value = "/passenger/{id}")
    public ModelAndView buyPageTicket(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        trainID = id;
        modelAndView.setViewName("passenger-view/buy-ticket");
        return modelAndView;
    }


    // оформаление билета по инмени, фамилии и дате рождения пассажира
    @PostMapping(value = "/passenger")
    public ModelAndView buyPassenger(@RequestParam String name,
                                     @RequestParam String surname,
                                     @RequestParam Date birthday) {
        ModelAndView modelAndView = new ModelAndView();

        // находим пассажира, его данные в БД по имени, фамилии и дню рождения
        PassengerDTO currentPassenger = new PassengerDTO();
        List<PassengerDTO> passengerDTOS = passengerService.allPassengers();
        for (PassengerDTO passengerDTO: passengerDTOS) {
            if(passengerDTO.getName().equals(name)&&
                passengerDTO.getSurname().equals(surname)&&
            passengerDTO.getBirthday().equals(birthday)) {
                currentPassenger = passengerDTO;
                break;
            }
        }

        //получение инфомации о поезде
        TrainDTO trainDTO = trainService.getById(trainID);

        //проверка наличия пассажира в поезде
        List<TicketDTO> ticketDTOList = ticketService.getByTrain(trainMapper.mapDtoToEntity(trainDTO));
        for (TicketDTO ticket: ticketDTOList) {
            if (ticket.getPassenger().getId()==currentPassenger.getId()){
                modelAndView.setViewName("redirect:/ticket/message");
                return modelAndView;
            }
        }

        // создаем билет для пассажира
        TicketDTO ticket = new TicketDTO();
       ticket.setDepartureStation(departureStation);
       ticket.setArrivalStation(arrivalStation);
        //добавление информацию о поезде в билет
        ticket.setTrain(trainMapper.mapDtoToEntity(trainDTO));
        ticket.setPassenger(passengerMapper.mapDtoToEntity(currentPassenger));
        //получаем информацию о датах
        StationDTO stationA = stationService.getByName(departureStation);
        StationDTO stationB = stationService.getByName(arrivalStation);
        // проверка наличия свободных мест
        TimetableDTO timetableA = timetableService.getTimetableByTrainAndStation(trainMapper.mapDtoToEntity(trainDTO),stationMapper.mapDtoToEntity(stationA));
        if (timetableA.getCountFreeSits()==0) {
            modelAndView.setViewName("redirect:/ticket/message");
            return modelAndView;
        }
        TimetableDTO timetableB = timetableService.getTimetableByTrainAndStation(trainMapper.mapDtoToEntity(trainDTO),stationMapper.mapDtoToEntity(stationB));
        String arDate = timetableA.getArrivalTime();
        String depDate = timetableB.getDepartureTime();
        ticket.setArrivalDate(Date.valueOf("2019-10-10"));
        ticket.setDepartureDate(Date.valueOf("2019-10-11"));
        ticketService.add(ticket);

        //задаем количество свободных мест с учетом нового пассажира
        timetableA.setCountFreeSits(timetableA.getCountFreeSits()-1);

        modelAndView.setViewName("redirect:/ticket/");

        return modelAndView;
    }

}
