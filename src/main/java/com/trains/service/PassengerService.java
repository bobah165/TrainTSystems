package com.trains.service;

import com.trains.dao.PassengerDAO;
import com.trains.dto.PassFromTrainDTO;
import com.trains.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class PassengerService {
    private PassengerDAO passengerDAO;

    @Autowired
    public void setPassengerDAO(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    public List<Passenger> allPassengers() {
        return passengerDAO.allPassengers();
    }

    public void add(Passenger passenger) { passengerDAO.add(passenger); }

    public void delete(Passenger passenger) {passengerDAO.delete(passenger); }

    public void edit(Passenger passenger) {passengerDAO.edit(passenger); }

    public Passenger getById(int idPassenger) {
        return passengerDAO.getById(idPassenger);
    }

    public List<PassFromTrainDTO> getPass(int id) {return passengerDAO.getPassFromTrain(id); }
}


