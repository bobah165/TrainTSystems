package com.trains.util.mapperForDTO;

import com.trains.model.dto.TrainFromStationDTO;
import com.trains.model.entity.TrainFromStation;
import org.springframework.stereotype.Component;

@Component
public class TrainFromStationMapper {

    public TrainFromStation mapDtoToEntity (TrainFromStationDTO trainFromStationDTO) {
       TrainFromStation trainFromStation = new TrainFromStation();
       trainFromStation.setArrivalTime(trainFromStationDTO.getArrivalTime());
        trainFromStation.setDaysInWay(trainFromStationDTO.getDaysInWay());
       trainFromStation.setDepartureTime(trainFromStationDTO.getDepartureTime());
       trainFromStation.setId(trainFromStationDTO.getId());
       trainFromStation.setIdTrain(trainFromStationDTO.getIdTrain());
       trainFromStation.setNameStation(trainFromStationDTO.getNameStation());
        return trainFromStation;
    }

    public TrainFromStationDTO mapEntityToDto (TrainFromStation trainFromStation) {
        TrainFromStationDTO trainFromStationDTO = new TrainFromStationDTO();
        trainFromStationDTO.setDaysInWay(trainFromStation.getDaysInWay());
        trainFromStationDTO.setNameStation(trainFromStation.getNameStation());
        trainFromStationDTO.setDepartureTime(trainFromStation.getDepartureTime());
        trainFromStationDTO.setArrivalTime(trainFromStation.getArrivalTime());
        trainFromStationDTO.setIdTrain(trainFromStation.getIdTrain());
        trainFromStationDTO.setId(trainFromStation.getId());
        return trainFromStationDTO;
    }


}
