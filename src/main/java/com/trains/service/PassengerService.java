package com.trains.service;

import com.trains.dao.PassengerDAO;
import com.trains.model.dto.PassengerDTO;
import com.trains.model.entity.Passenger;
import com.trains.util.mapperForDTO.PassengerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

@Service
@Transactional
public class PassengerService {
    private PassengerDAO passengerDAO;
    private PassengerMapper passengerMapper;
    private static Logger logger = LoggerFactory.getLogger(PassengerService.class);

    @Autowired
    public void setPassengerDAO(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    @Autowired
    public void setPassengerMapper(PassengerMapper passengerMapper) {
        this.passengerMapper = passengerMapper;
    }


    public List<PassengerDTO> getAllPassengers() {
        List<Passenger> passengers = passengerDAO.getAllPassengers();
        List<PassengerDTO> passengerDTOS = new ArrayList<>();
        for (Passenger passenger : passengers) {
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
        passengerDAO.delete(passenger);
    }


    public void edit(PassengerDTO passengerDTO) {
        Passenger passenger = passengerMapper.mapDtoToEntity(passengerDTO);
        passengerDAO.edit(passenger);
    }


    public PassengerDTO getById(int id) {
        Passenger passenger = passengerDAO.getById(id);
        PassengerDTO passengerDTO = passengerMapper.mapEntityToDto(passenger);
        return passengerDTO;
    }

    public void delByID(int id) {
        passengerDAO.delByID(id);
    }

    public int getPassengerId(String name, String surname, Date birthday) {
        return passengerDAO.getPassengerId(name, surname, birthday);
    }

    public void addPassengerByNameSurnameDate(String name, String surname, Date birthday) {
        passengerDAO.addPassengerByNameSurnameDate(name, surname, birthday);
    }

    public PassengerDTO getPassengerBylogin(String login) {
        Passenger passenger = passengerDAO.getPassengerBylogin(login);
        PassengerDTO passengerDTO = passengerMapper.mapEntityToDto(passenger);
        return passengerDTO;
    }


    public String getTextForDidYouKnow() {
        String textFromFile = "";
        String fileName = "C:/Users/ACER/IdeaProjects/TrainTsystems/src/main/resources/didYouKnow.properties";
        Properties property = new Properties();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            property.load(fis);
            int number = new Random().nextInt(5);
            String readProrerty = "text"+number;
            textFromFile = property.getProperty(readProrerty);
        } catch (IOException e) {
            logger.error("File doesn't read");
        }
        return textFromFile;
    }
}


