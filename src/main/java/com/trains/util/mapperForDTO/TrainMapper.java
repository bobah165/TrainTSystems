package com.trains.util.mapperForDTO;

import com.trains.model.dto.TrainDTO;
import com.trains.model.entity.Train;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class TrainMapper {

    public Train mapDtoToEntity (TrainDTO trainDTO) {
        Train train = new Train();
        train.setCountSits(trainDTO.getCountSits());
        train.setId(trainDTO.getId());
        train.setTickets(trainDTO.getTickets());
        train.setTrainNumber(trainDTO.getTrainNumber());
        train.setTrainWay(trainDTO.getTrainWay());
        train.setDepartureDate(trainDTO.getDepartureDate().toLocalDate());
        train.setSchedule(trainDTO.getSchedule());
        return train;
    }

    public TrainDTO mapEntityToDto (Train train) {
        TrainDTO trainDTO = new TrainDTO();
        trainDTO.setCountSits(train.getCountSits());
        trainDTO.setId(train.getId());
        trainDTO.setTickets(train.getTickets());
        trainDTO.setTrainNumber(train.getTrainNumber());
        trainDTO.setTrainWay(train.getTrainWay());
        trainDTO.setDepartureDate(Date.valueOf(train.getDepartureDate()));
        trainDTO.setSchedule(train.getSchedule());
        return trainDTO;
    }
}
