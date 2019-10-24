package com.trains.util.mapperForDTO;

import com.trains.model.dto.TimetableDTO;
import com.trains.model.entity.Timetable;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;

@Component
public class TimetableMapper {

    public Timetable mapDtoToEntity (TimetableDTO timetableDTO) {
        Timetable timetable = new Timetable();
        timetable.setArrivalTime(Time.valueOf(timetableDTO.getArrivalTime()+":00"));
        timetable.setCountFreeSits(timetableDTO.getCountFreeSits());
        timetable.setDepartureTime(Time.valueOf(timetableDTO.getDepartureTime()+":00"));
        timetable.setId(timetableDTO.getId());
        timetable.setStation(timetableDTO.getStation());
        timetable.setTrain(timetableDTO.getTrain());
        return timetable;
    }

    public TimetableDTO mapEntityToDto (Timetable timetable) {
        TimetableDTO timetableDTO = new TimetableDTO();
        timetableDTO.setArrivalTime(timetable.getArrivalTime().toString());
        timetableDTO.setCountFreeSits(timetable.getCountFreeSits());
        timetableDTO.setDepartureTime(timetable.getDepartureTime().toString());
        timetableDTO.setId(timetable.getId());
        timetableDTO.setStation(timetable.getStation());
        timetableDTO.setTrain(timetable.getTrain());
        return timetableDTO;
    }
}
