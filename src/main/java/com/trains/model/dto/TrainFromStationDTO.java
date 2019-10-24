package com.trains.model.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class TrainFromStationDTO {
    private int idTrain;
    private String nameStation;
    private Time departureTime;
    private Time arrivalTime;

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
