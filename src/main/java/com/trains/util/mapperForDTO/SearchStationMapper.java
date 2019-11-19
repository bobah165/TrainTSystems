package com.trains.util.mapperForDTO;

import com.trains.model.dto.SearchStationDTO;
import com.trains.model.entity.SearchStations;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class SearchStationMapper {

    public SearchStations mapDtoToEntity (SearchStationDTO searchStationDTO) {
        SearchStations searchStations = new SearchStations();
        searchStations.setArrivalStation(searchStationDTO.getArrivalStation());
        searchStations.setDepartureDate(searchStationDTO.getDepartureDate().toLocalDate());
        searchStations.setDepartureStation(searchStationDTO.getDepartureStation());
        searchStations.setStartTime(searchStationDTO.getStartTime());
        searchStations.setId(searchStationDTO.getId());
        searchStations.setEndTime(searchStationDTO.getEndTime());
        return searchStations;
    }

    public SearchStationDTO mapEntityToDto (SearchStations searchStations) {
        SearchStationDTO searchStationDTO = new SearchStationDTO();
        searchStationDTO.setArrivalStation(searchStations.getArrivalStation());
        searchStationDTO.setDepartureDate(Date.valueOf(searchStations.getDepartureDate()));
        searchStationDTO.setDepartureStation(searchStations.getDepartureStation());
        searchStationDTO.setStartTime(searchStations.getStartTime());
        searchStationDTO.setId(searchStations.getId());
        searchStationDTO.setEndTime(searchStations.getEndTime());
        return searchStationDTO;
    }
}
