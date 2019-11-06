package com.trains.service;

import com.trains.dao.SearchStationDAO;
import com.trains.model.dto.PassengerDTO;
import com.trains.model.dto.SearchStationDTO;
import com.trains.model.dto.TrainFromStationAToB;
import com.trains.model.entity.SearchStations;
import com.trains.util.mapperForDTO.SearchStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class SearchStationService {
    private SearchStationDAO searchStationDAO;
    private SearchStationMapper searchStationMapper;

    @Autowired
    public void setSearchStationDAO(SearchStationDAO searchStationDAO) {
        this.searchStationDAO = searchStationDAO;
    }

    @Autowired
    public void setSearchStationMapper(SearchStationMapper searchStationMapper) {
        this.searchStationMapper = searchStationMapper;
    }

    public void add(SearchStationDTO searchStationDTO) {
        SearchStations searchStations = searchStationMapper.mapDtoToEntity(searchStationDTO);
        searchStationDAO.add(searchStations);
    }

    public void delete(SearchStationDTO searchStationDTO) {
        SearchStations searchStations = searchStationMapper.mapDtoToEntity(searchStationDTO);
        searchStationDAO.delete(searchStations);
    }

    public void edit(SearchStationDTO searchStationDTO) {
        SearchStations searchStations = searchStationMapper.mapDtoToEntity(searchStationDTO);
        searchStationDAO.edit(searchStations);
    }

    public SearchStationDTO getById(int id) {
        SearchStations searchStations = searchStationDAO.getById(id);
        SearchStationDTO searchStationDTO = searchStationMapper.mapEntityToDto(searchStations);
        return searchStationDTO;
    }

    public List<SearchStationDTO> allTrains() {
        List<SearchStations> searchStations = searchStationDAO.allTrains();
        List<SearchStationDTO> searchStationDTOS = new ArrayList<>();
        for (SearchStations searchStation: searchStations) {
            searchStationDTOS.add(searchStationMapper.mapEntityToDto(searchStation));
        }
        return searchStationDTOS;
    }

    public void delByID (int id) {searchStationDAO.delByID(id);}

    public List<TrainFromStationAToB> getTrainsFromStations (String stationNameA, String stationNameB, LocalTime startTime, LocalTime endTime, LocalDate departureDate) {
        return searchStationDAO.getTrainsFromStations(stationNameA,stationNameB,startTime,endTime, departureDate);
    }


    public void addInformationAboutSearch (String stationA, String stationB, LocalDate departureDate, String startTime, String endTime) {

        int idCurrentPassenger = ((PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        SearchStations searchStation = new SearchStations();
        searchStation.setId(idCurrentPassenger);
        searchStation.setDepartureDate(departureDate);
        searchStation.setStartTime(LocalTime.parse(startTime));
        searchStation.setDepartureStation(stationA);
        searchStation.setArrivalStation(stationB);
        searchStation.setEndTime(LocalTime.parse(endTime));

        searchStationDAO.add(searchStation);
    }
}
