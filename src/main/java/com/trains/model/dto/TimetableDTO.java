package com.trains.model.dto;

import com.trains.model.entity.Station;
import com.trains.model.entity.Train;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class TimetableDTO {
    private int id;
    private Train train;
    private Station station;
    private String arrivalTime;
    private String departureTime;
    private int countFreeSits;

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

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getCountFreeSits() {
        return countFreeSits;
    }

    public void setCountFreeSits(int countFreeSits) {
        this.countFreeSits = countFreeSits;
    }
}
