package com.trains.util.mapperForDTO;

import com.trains.model.dto.FreeSeatsDTO;
import com.trains.model.entity.FreeSeats;
import org.springframework.stereotype.Component;

@Component
public class FreeSeatsMapper {

    public FreeSeats mapDtoToEntity (FreeSeatsDTO freeSeatsDTO) {
        FreeSeats freeSeats = new FreeSeats();
       freeSeats.setFreeSeats(freeSeatsDTO.getFreeSeats());
       freeSeats.setId(freeSeatsDTO.getId());
       freeSeats.setIdTrain(freeSeatsDTO.getIdTrain());
       freeSeats.setStationName(freeSeatsDTO.getStationName());
        return freeSeats;
    }

    public FreeSeatsDTO mapEntityToDto (FreeSeats freeSeats) {
        FreeSeatsDTO freeSeatsDTO = new FreeSeatsDTO();
        freeSeatsDTO.setFreeSeats(freeSeats.getFreeSeats());
        freeSeatsDTO.setId(freeSeats.getId());
        freeSeatsDTO.setIdTrain(freeSeats.getIdTrain());
        freeSeatsDTO.setStationName(freeSeats.getStationName());
        return freeSeatsDTO;
    }
}
