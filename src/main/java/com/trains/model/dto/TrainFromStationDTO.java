package com.trains.model.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class TrainFromStationDTO {
    private int id;
    private int idTrain;
    private String nameStation;
    private Time departureTime;
    private Time arrivalTime;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "TrainEntity{" +
                "id train is "+idTrain+
                ", Station name is " + nameStation +
                ", departure time is " + departureTime+
                ", arrival time is " + arrivalTime+ " }";
    }
}
