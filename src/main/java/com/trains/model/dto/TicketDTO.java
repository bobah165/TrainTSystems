package com.trains.model.dto;

import com.trains.model.entity.Passenger;
import com.trains.model.entity.Train;

import javax.persistence.*;
import java.sql.Date;

public class TicketDTO {

    private int id;
    private Train train;
    private Passenger passenger;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

}
