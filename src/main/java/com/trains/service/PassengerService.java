package com.trains.service;

import com.trains.dao.PassengerDAO;
import com.trains.model.dto.PassengerDTO;
import com.trains.model.entity.Passenger;
import com.trains.util.mapperForDTO.PassengerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PassengerService {
    private PassengerDAO passengerDAO;
    private PassengerMapper passengerMapper;

    @Autowired
    public void setPassengerDAO(PassengerDAO passengerDAO) {this.passengerDAO = passengerDAO; }

    @Autowired
    public void setPassengerMapper(PassengerMapper passengerMapper) {
        this.passengerMapper = passengerMapper;
    }

    public List<PassengerDTO> allPassengers() {
        List<Passenger> passengers = passengerDAO.allPassengers();
        List<PassengerDTO> passengerDTOS = new ArrayList<>();
        for (Passenger passenger: passengers) {
            passengerDTOS.add(passengerMapper.mapEntityToDto(passenger));
        }
        return passengerDTOS;
    }

    public void add(PassengerDTO passengerDTO) {
        Passenger passenger = passengerMapper.mapDtoToEntity(passengerDTO);
        passengerDAO.add(passenger);
    }

    public void delete(PassengerDTO passengerDTO) {
        Passenger passenger = passengerMapper.mapDtoToEntity(passengerDTO);
        passengerDAO.delete(passenger); }

    public void edit(PassengerDTO passengerDTO) {
        Passenger passenger = passengerMapper.mapDtoToEntity(passengerDTO);
        passengerDAO.edit(passenger); }

    public PassengerDTO getById(int id) {
        Passenger passenger = passengerDAO.getById(id);
        PassengerDTO passengerDTO = passengerMapper.mapEntityToDto(passenger);
        return passengerDTO;
    }

    public void delByID (int id) {passengerDAO.delByID(id); }

    public int getPassengerId (String name, String surname, Date birthday) {
        return passengerDAO.getPassengerId(name, surname,birthday);
    }

    public void addPassengerByNameSurnameDate (String name, String surname, Date birthday) {
        passengerDAO.addPassengerByNameSurnameDate(name,surname,birthday);
    }
}


