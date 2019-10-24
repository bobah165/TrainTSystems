package com.trains.util.mapperForDTO;

import com.trains.model.dto.PassengerDTO;
import com.trains.model.entity.Passenger;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class PassengerMapper {

    public Passenger mapDtoToEntity(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        passenger.setBirthday(passengerDTO.getBirthday().toLocalDate());
        passenger.setEmail(passengerDTO.getEmail());
        passenger.setId(passengerDTO.getId());
        passenger.setLogin(passengerDTO.getLogin());
        passenger.setName(passengerDTO.getName());
        passenger.setPassword(passengerDTO.getPassword());
        passenger.setSurname(passengerDTO.getSurname());
        passenger.setTickets(passengerDTO.getTickets());
        passenger.setUser(passengerDTO.getUser());
        return passenger;
    }

    public PassengerDTO mapEntityToDto (Passenger passenger) {
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setBirthday(Date.valueOf(passenger.getBirthday()));
        passengerDTO.setEmail(passenger.getEmail());
        passengerDTO.setId(passenger.getId());
        passengerDTO.setLogin(passenger.getLogin());
        passengerDTO.setName(passenger.getName());
        passengerDTO.setPassword(passenger.getPassword());
        passengerDTO.setSurname(passenger.getSurname());
        passengerDTO.setTickets(passenger.getTickets());
        passengerDTO.setUser(passenger.getUser());
        return passengerDTO;
    }
}
