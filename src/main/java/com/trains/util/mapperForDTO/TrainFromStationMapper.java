package com.trains.util.mapperForDTO;

import com.trains.model.dto.TrainFromStationDTO;
import com.trains.model.entity.TrainFromStation;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;

@Component
public class TrainFromStationMapper {

    public TrainFromStation mapDtoToEntity (TrainFromStationDTO trainFromStationDTO) {
       TrainFromStation trainFromStation = new TrainFromStation();
       trainFromStation.setArrivalTime(trainFromStationDTO.getArrivalTime().toLocalTime());
       trainFromStation.setDate(trainFromStationDTO.getDate().toLocalDate());
       trainFromStation.setDepartureTime(trainFromStationDTO.getDepartureTime().toLocalTime());
       trainFromStation.setId(trainFromStationDTO.getId());
       trainFromStation.setIdTrain(trainFromStationDTO.getIdTrain());
       trainFromStation.setNameStation(trainFromStationDTO.getNameStation());
        return trainFromStation;
    }

    public TrainFromStationDTO mapEntityToDto (TrainFromStation trainFromStation) {
        TrainFromStationDTO trainFromStationDTO = new TrainFromStationDTO();
        trainFromStationDTO.setDate(Date.valueOf(trainFromStation.getDate()));
        trainFromStationDTO.setNameStation(trainFromStation.getNameStation());
        trainFromStationDTO.setDepartureTime(Time.valueOf(trainFromStation.getDepartureTime()));
        trainFromStationDTO.setArrivalTime(Time.valueOf(trainFromStation.getArrivalTime()));
        trainFromStationDTO.setIdTrain(trainFromStation.getIdTrain());
        trainFromStationDTO.setId(trainFromStation.getId());
        return trainFromStationDTO;
    }


}
