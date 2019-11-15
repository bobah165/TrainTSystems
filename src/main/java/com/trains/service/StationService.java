package com.trains.service;

import com.trains.dao.StationDAO;
import com.trains.dao.TrainFromStationDAO;
import com.trains.model.dto.StationDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.dto.TrainFromStationDTO;
import com.trains.model.entity.Station;
import com.trains.model.entity.Train;
import com.trains.model.entity.TrainFromStation;
import com.trains.util.MessageSender;
import com.trains.util.mapperForDTO.StationMapper;
import com.trains.util.mapperForDTO.TrainFromStationMapper;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StationService {
    private StationMapper stationMapper;
    private StationDAO stationDAO;
    private TrainFromStationDAO trainFromStationDAO;
    private TrainFromStationMapper trainFromStationMapper;

    @Autowired
    public void setTrainFromStationMapper(TrainFromStationMapper trainFromStationMapper) {
        this.trainFromStationMapper = trainFromStationMapper;
    }


    @Autowired
    public void setTrainFromStationDAO(TrainFromStationDAO trainFromStationDAO) {
        this.trainFromStationDAO = trainFromStationDAO;
    }

    @Autowired
    public void setStationDAO(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    @Autowired
    public void setStationMapper(StationMapper stationMapper) {
        this.stationMapper = stationMapper;
    }

    public List<StationDTO> allStations() {
        List<Station> stations = stationDAO.allStations();
        List<StationDTO> stationDTOS = new ArrayList<>();
        for (Station station: stations) {
            stationDTOS.add(stationMapper.mapEntityToDto(station));
        }

    return stationDTOS;
    }

    public List<StationDTO> allStatinPagination(int page) {
        List<Station> stations = stationDAO.allStatinPagination(page);
        List<StationDTO> stationDTOS = new ArrayList<>();
        for (Station station: stations) {
            stationDTOS.add(stationMapper.mapEntityToDto(station));
        }
        return stationDTOS;
    }

    public int stationCountForPage() {
        return stationDAO.stationCountForPage();
    }

    public void add(StationDTO stationDTO) {
        Station station = stationMapper.mapDtoToEntity(stationDTO);
        stationDAO.add(station); }

    public void delete(StationDTO stationDTO) {
        Station station = stationMapper.mapDtoToEntity(stationDTO);
        stationDAO.delete(station); }

    public void edit(StationDTO stationDTO) {
        Station station = stationMapper.mapDtoToEntity(stationDTO);
        stationDAO.edit(station); }

    public StationDTO getById(int id) {
        Station station = stationDAO.getById(id);
        StationDTO stationDTO = stationMapper.mapEntityToDto(station);
        return stationDTO;
    }

    public List<TrainFromStationDTO> getTrainFromStation(int idStation, Date departureDate, LocalTime startTime, LocalTime endTime) {
        List<TrainFromStationDTO> trainFromStations = stationDAO.getTrainFromStation(idStation,departureDate,startTime,endTime);
        return trainFromStations;
    }

    public void delByID (int id) { stationDAO.delByID(id); }

    public StationDTO getByName (String name) {
        Station station = stationDAO.getByName(name);
        StationDTO stationDTO = stationMapper.mapEntityToDto(station);
        return stationDTO;
    }

}
