package com.trains.util.mapperForDTO;

import com.trains.model.dto.TrainWayDTO;
import com.trains.model.entity.TrainWay;
import org.springframework.stereotype.Component;

import java.sql.Time;

@Component
public class TrainWayMapper {

    public TrainWay mapDtoToEntity (TrainWayDTO trainWayDTO) {
        TrainWay trainWay = new TrainWay();
        trainWay.setDaysInWay(trainWayDTO.getDaysInWay());
        trainWay.setFreeSeats(trainWayDTO.getFreeSeats());
        trainWay.setId(trainWayDTO.getId());
        trainWay.setShedule(Time.valueOf(trainWayDTO.getShedule()));
        trainWay.setStation(trainWayDTO.getStation());
        trainWay.setStopTime(Time.valueOf(trainWayDTO.getStopTime()));
        trainWay.setNumberWay(trainWayDTO.getNumberWay());
        trainWay.setTrains(trainWayDTO.getTrains());
        return trainWay;
    }

    public TrainWayDTO mapEntityToDto (TrainWay trainWay) {
        TrainWayDTO trainWayDTO = new TrainWayDTO();
        trainWayDTO.setDaysInWay(trainWay.getDaysInWay());
        trainWayDTO.setFreeSeats(trainWay.getFreeSeats());
        trainWayDTO.setId(trainWay.getId());
        trainWayDTO.setShedule(trainWay.getShedule().toString());
        trainWayDTO.setStation(trainWay.getStation());
        trainWayDTO.setStopTime(trainWay.getStopTime().toString());
        trainWayDTO.setNumberWay(trainWay.getNumberWay());
        trainWayDTO.setTrains(trainWay.getTrains());
        return trainWayDTO;
    }
}
