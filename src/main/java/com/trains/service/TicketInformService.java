package com.trains.service;

import com.trains.dao.TicketInformDAO;
import com.trains.model.dto.*;
import com.trains.model.entity.SearchStations;
import com.trains.model.entity.TicketInform;
import com.trains.model.entity.Train;
import com.trains.model.entity.TrainWay;
import com.trains.util.mapperForDTO.SearchStationMapper;
import com.trains.util.mapperForDTO.TicketInformMapper;
import com.trains.util.mapperForDTO.TrainMapper;
import com.trains.util.mapperForDTO.TrainWayMapper;
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
    private SearchStationMapper searchStationMapper;
    private TrainWayMapper trainWayMapper;
    private TrainMapper trainMapper;

    @Autowired
    public void setTrainMapper(TrainMapper trainMapper) {
        this.trainMapper = trainMapper;
    }

    @Autowired
    public void setTrainWayMapper(TrainWayMapper trainWayMapper) {
        this.trainWayMapper = trainWayMapper;
    }

    @Autowired
    public void setSearchStationMapper(SearchStationMapper searchStationMapper) {
        this.searchStationMapper = searchStationMapper;
    }

    @Autowired
    public void setTicketInformDAO(TicketInformDAO ticketInformDAO) {
        this.ticketInformDAO = ticketInformDAO;
    }

    @Autowired
    public void setTicketInformMapper(TicketInformMapper ticketInformMapper) {
        this.ticketInformMapper = ticketInformMapper;
    }

    public List<TicketInformDTO> getAllTickets() {
        List<TicketInform> tickets = ticketInformDAO.getAllTickets();
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

    public TicketInformDTO fillInformationByStationAndWay(SearchStationDTO searchStationDTO, List<TrainWayDTO> trainWayDTOS, TrainDTO trainDTO) {
        SearchStations searchStations = searchStationMapper.mapDtoToEntity(searchStationDTO);
        List<TrainWay> trainWays = new ArrayList<>();
        for(TrainWayDTO trainWayDTO: trainWayDTOS){
            trainWays.add(trainWayMapper.mapDtoToEntity(trainWayDTO));
        }
        Train train = trainMapper.mapDtoToEntity(trainDTO);
        TicketInform ticketInform = ticketInformDAO.fullInformation(searchStations,trainWays,train);

        return ticketInformMapper.mapEntityToDto(ticketInform);
    }

    public boolean checkLegalTimeForBuyTicket(TicketInformDTO  ticketInformDTO) {
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

    public void addPassengerInformationToTicket(String name, String surname, Date birthday, int idPassenger){
        ticketInformDAO.addPassengerInformationToTicket(name,surname,birthday,idPassenger);
    }

}
