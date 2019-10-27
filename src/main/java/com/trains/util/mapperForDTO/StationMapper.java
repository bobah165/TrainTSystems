package com.trains.util.mapperForDTO;

import com.trains.model.dto.StationDTO;
import com.trains.model.entity.Station;
import org.springframework.stereotype.Component;

@Component
public class StationMapper {

    public Station mapDtoToEntity (StationDTO stationDTO) {
        Station station = new Station();
        station.setNameStation(stationDTO.getNameStation());
        station.setId(stationDTO.getId());
        return station;
    }

    public StationDTO mapEntityToDto (Station station) {
        StationDTO stationDTO = new StationDTO();
        stationDTO.setId(station.getId());
        stationDTO.setNameStation(station.getNameStation());
        return stationDTO;
    }
}
