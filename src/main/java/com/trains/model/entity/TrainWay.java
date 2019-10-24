package com.trains.model.entity;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "train_ways")
public class TrainWay {

    @Id
    @Column(name = "id_way")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId(mutable = true)
    @Column(name = "number_way")
    private int numberWay;

    @Column(name = "shedule" )
    private Time shedule;
    @Column (name = "stop_time")
    private Time stopTime;
    @Column(name = "days_in_way")
    private int daysInWay;
    @Column(name = "free_seats")
    private int freeSeats;

    @NaturalId(mutable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_station")
    private Station station;

    @OneToMany(mappedBy = "trainWay", cascade = CascadeType.ALL,orphanRemoval = true)
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

    public Time getShedule() {
        return shedule;
    }

    public void setShedule(Time shedule) {
        this.shedule = shedule;
    }

    public Time getStopTime() {
        return stopTime;
    }

    public void setStopTime(Time stopTime) {
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
