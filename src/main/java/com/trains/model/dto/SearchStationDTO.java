package com.trains.model.dto;


import java.sql.Date;
import java.time.LocalTime;

public class SearchStationDTO {

    private int id;
    private String departureStation;
    private String arrivalStation;
    private Date departureDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Search Station DTO {" +
                "id=" + id +
                ", departure station is " + departureStation +
                ", arrival station is "+arrivalStation+
                ", departure date " + departureDate +
                " }";
    }
}
