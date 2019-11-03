package com.trains.service;

import com.trains.dao.TicketInformDAO;
import com.trains.model.dto.SearchStationDTO;
import com.trains.model.dto.TicketInformDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.dto.TrainWayDTO;
import com.trains.model.entity.TicketInform;
import com.trains.util.mapperForDTO.TicketInformMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TicketInformService {
    private TicketInformDAO ticketInformDAO;
    private TicketInformMapper ticketInformMapper;

    @Autowired
    public void setTicketInformDAO(TicketInformDAO ticketInformDAO) {
        this.ticketInformDAO = ticketInformDAO;
    }

    @Autowired
    public void setTicketInformMapper(TicketInformMapper ticketInformMapper) {
        this.ticketInformMapper = ticketInformMapper;
    }

    public List<TicketInformDTO> allTickets() {
        List<TicketInform> tickets = ticketInformDAO.allTickets();
        List<TicketInformDTO> ticketDTOS = new ArrayList<>();
        for (TicketInform ticket: tickets) {
            ticketDTOS.add(ticketInformMapper.mapEntityToDto(ticket));
        }
        return ticketDTOS;
    }

    public void add(TicketInformDTO ticketDTO) {
        TicketInform ticket = ticketInformMapper.mapDtoToEntity(ticketDTO);
        ticketInformDAO.add(ticket); }

    public void delete(TicketInformDTO ticketDTO) {
        TicketInform ticket = ticketInformMapper.mapDtoToEntity(ticketDTO);
        ticketInformDAO.delete(ticket); }

    public void edit(TicketInformDTO ticketDTO) {
        TicketInform ticket = ticketInformMapper.mapDtoToEntity(ticketDTO);
        ticketInformDAO.edit(ticket);
    }

    public TicketInformDTO getById(int id) {
        TicketInform ticket = ticketInformDAO.getById(id);
        TicketInformDTO ticketDTO = ticketInformMapper.mapEntityToDto(ticket);
        return ticketDTO; }

    public void delByID (int id) { ticketInformDAO.delByID(id); }

    public TicketInformDTO fullInformation(SearchStationDTO searchStationDTO, List<TrainWayDTO> trainWayDTOS, TrainDTO trainDTO) {

        Time departureTime = Time.valueOf("00:00:00");
        Time arrivalTime = Time.valueOf("00:00:00");
//        List<TrainWayDTO> trainWayDTOS = trainWayService.allWays();
//        logger.info("Get all ways");
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

        return ticketInform;
    }

    public boolean checkDeapartureTime(TicketInformDTO  ticketInformDTO) {
        boolean isTimeCheck = true;
        LocalTime depTime10minutes = LocalTime.parse(ticketInformDTO.getDepartureTime()).minusMinutes(10);
        LocalTime depTime = LocalTime.parse(ticketInformDTO.getDepartureTime());
        LocalTime currentTime = new Time(System.currentTimeMillis()).toLocalTime();
        if(currentTime.isAfter(depTime10minutes)&&currentTime.isBefore(depTime)) {
            isTimeCheck = false;
            return isTimeCheck;
        }
        return isTimeCheck;
    }

}
