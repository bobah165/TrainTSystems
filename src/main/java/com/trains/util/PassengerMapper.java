package com.trains.util;

import com.trains.model.dto.PassengerDTO;
import com.trains.model.entity.Passenger;
import org.springframework.stereotype.Component;

@Component
public class PassengerMapper {

    public Passenger mapDtoToEntity(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        passenger.setBirthday(passengerDTO.getBirthday());
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
        passengerDTO.setBirthday(passenger.getBirthday());
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
