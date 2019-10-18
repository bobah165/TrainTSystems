package com.trains.dto;

import java.sql.Date;

public class TrainFromStationDTO {
    private int idTrain;
    private String nameStation;
    private Date departureTime;
    private Date arrivalTime;

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

    @Override
    public String toString() {
        return "TrainEntity{" +
                "id train is "+idTrain+
                ", Station name is " + nameStation +
                ", departure time is " + departureTime+
                ", arrival time is " + arrivalTime+ " }";
    }
}
