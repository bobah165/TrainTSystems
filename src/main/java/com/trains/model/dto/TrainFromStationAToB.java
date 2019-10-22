package com.trains.model.dto;

import java.sql.Date;

public class TrainFromStationAToB {
    private int trainID;
    private String deprtureStation;
    private String arrivalStation;
    private Date departureTime;
    private Date arrivalTime;
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

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getCountFreeSits() {
        return countFreeSits;
    }

    public void setCountFreeSits(int countFreeSits) {
        this.countFreeSits = countFreeSits;
    }

}
