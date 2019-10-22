package com.trains.service;

import com.trains.dao.StationDAO;
import com.trains.model.dto.StationDTO;
import com.trains.model.dto.TrainFromStationDTO;
import com.trains.model.entity.Station;
import com.trains.util.StationMapper;
import org.hibernate.loader.collection.OneToManyLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StationService {
    private StationMapper stationMapper;
    private StationDAO stationDAO;

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

    public List<TrainFromStationDTO> getTrainFromStation(int id) {
        return stationDAO.getTrainFromStation(id);
    }

    public void delByID (int id) { stationDAO.delByID(id); }

    public StationDTO getByName (String name) {
        Station station = stationDAO.getByName(name);
        StationDTO stationDTO = stationMapper.mapEntityToDto(station);
        return stationDTO;
    }

}
