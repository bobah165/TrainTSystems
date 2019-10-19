package com.trains.util;

import com.trains.model.dto.TimetableDTO;
import com.trains.model.entity.Timetable;
import org.springframework.stereotype.Component;

@Component
public class TimetableMapper {

    public Timetable mapDtoToEntity (TimetableDTO timetableDTO) {
        Timetable timetable = new Timetable();
        timetable.setArrivalTime(timetableDTO.getArrivalTime());
        timetable.setCountFreeSits(timetableDTO.getCountFreeSits());
        timetable.setDepartureTime(timetableDTO.getDepartureTime());
        timetable.setId(timetableDTO.getId());
        timetable.setStation(timetableDTO.getStation());
        timetable.setTrain(timetableDTO.getTrain());
        return timetable;
    }

    public TimetableDTO mapEntityToDto (Timetable timetable) {
        TimetableDTO timetableDTO = new TimetableDTO();
        timetableDTO.setArrivalTime(timetable.getArrivalTime());
        timetableDTO.setCountFreeSits(timetable.getCountFreeSits());
        timetableDTO.setDepartureTime(timetable.getDepartureTime());
        timetableDTO.setId(timetable.getId());
        timetableDTO.setStation(timetable.getStation());
        timetableDTO.setTrain(timetable.getTrain());
        return timetableDTO;
    }
}
