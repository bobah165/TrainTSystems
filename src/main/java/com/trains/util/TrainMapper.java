package com.trains.util;

import com.trains.model.dto.TrainDTO;
import com.trains.model.entity.Train;
import org.springframework.stereotype.Component;

@Component
public class TrainMapper {

    public Train mapDtoToEntity (TrainDTO trainDTO) {
        Train train = new Train();
        train.setCountSits(trainDTO.getCountSits());
        train.setId(trainDTO.getId());
        train.setTickets(trainDTO.getTickets());
        train.setTimetables(trainDTO.getTimetables());
        return train;
    }

    public TrainDTO mapEntityToDto (Train train) {
        TrainDTO trainDTO = new TrainDTO();
        trainDTO.setCountSits(train.getCountSits());
        trainDTO.setId(train.getId());
        trainDTO.setTickets(train.getTickets());
        trainDTO.setTimetables(train.getTimetables());
        return trainDTO;
    }
}
