package com.trains.service;

import com.trains.dao.PassengerDAOImpl;
import com.trains.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PassengerService {
    private PassengerDAOImpl passengerDAO;

    @Autowired
    public void setPassengerDAO(PassengerDAOImpl passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    @Transactional
    public List<Passenger> allPassengers() {
        return passengerDAO.allPassengers();
    }
    @Transactional
    public void add(Passenger passenger) { passengerDAO.add(passenger); }
    @Transactional
    public void delete(Passenger passenger) {passengerDAO.delete(passenger); }
    @Transactional
    public void edit(Passenger passenger) {passengerDAO.edit(passenger); }
    @Transactional
    public Passenger getById(int idPassenger) {
        return passengerDAO.getById(idPassenger);
    }
}


