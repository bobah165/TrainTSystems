package com.trains.model.dto;

import com.trains.model.entity.Station;
import com.trains.model.entity.Train;

import java.util.List;


public class TrainWayDTO {
    private int id;
    private String shedule;
    private String stopTime;
    private int daysInWay;
    private int freeSeats;
    private Station station;
    private int numberWay;
    private List<Train> trains;

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public int getNumberWay() {
        return numberWay;
    }

    public void setNumberWay(int numberWay) {
        this.numberWay = numberWay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShedule() {
        return shedule;
    }

    public void setShedule(String shedule) {
        this.shedule = shedule;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public int getDaysInWay() {
        return daysInWay;
    }

    public void setDaysInWay(int daysInWay) {
        this.daysInWay = daysInWay;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
