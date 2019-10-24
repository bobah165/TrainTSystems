package com.trains.model.dto;

import com.trains.model.entity.Ticket;
import com.trains.model.entity.Timetable;
import com.trains.model.entity.TrainWay;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

public class TrainDTO {

    private int id;
    private int trainNumber;
    private int countSits;
    private List<Ticket> tickets;
    private List<Timetable> timetables;
    private TrainWay trainWay;
    private Date departureDate;

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public TrainWay getTrainWay() {
        return trainWay;
    }

    public void setTrainWay(TrainWay trainWay) {
        this.trainWay = trainWay;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountSits() {
        return countSits;
    }

    public void setCountSits(int countSits) {
        this.countSits = countSits;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }
}
