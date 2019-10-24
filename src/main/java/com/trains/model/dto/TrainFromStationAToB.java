package com.trains.model.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class TrainFromStationAToB {
    private int trainID;
    private String deprtureStation;
    private String arrivalStation;
    private Time departureTime;
    private Time arrivalTime;
    private int countFreeSits;

    public int getTrainID() {
        return trainID;
    }

    public void setTrainID(int trainID) {
        this.trainID = trainID;
    }

    public String getDeprtureStation() {
        return deprtureStation;
    }

    public void setDeprtureStation(String deprtureStation) {
        this.deprtureStation = deprtureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
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

    public int getCountFreeSits() {
        return countFreeSits;
    }

    public void setCountFreeSits(int countFreeSits) {
        this.countFreeSits = countFreeSits;
    }

}
