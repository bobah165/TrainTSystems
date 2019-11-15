package com.trains.model.dto;

import java.time.LocalTime;

public class TrainFromStationDTO {
    private int id;
    private int idTrain;
    private String nameStation;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int daysInWay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDaysInWay() {
        return daysInWay;
    }

    public void setDaysInWay(int daysInWay) {
        this.daysInWay = daysInWay;
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

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Train Schedule {" +
                "id train is "+idTrain+
                ", Station name is " + nameStation +
                ", departure time is " + departureTime+
                ", arrival time is " + arrivalTime+ " }";
    }
}
