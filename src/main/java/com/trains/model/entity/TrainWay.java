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

    @Column(name = "departure_time" )
    private Time departureTime;
    @Column (name = "arrival_time")
    private Time arrivalTime;
    @Column(name = "days_in_way")
    private int daysInWay;

    @NaturalId(mutable = true)
    @ManyToOne(fetch = FetchType.EAGER)
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

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time shedule) {
        this.departureTime = shedule;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time stopTime) {
        this.arrivalTime = stopTime;
    }

    public int getDaysInWay() {
        return daysInWay;
    }

    public void setDaysInWay(int daysInWay) {
        this.daysInWay = daysInWay;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "Train Way {" +
                "id=" + id +
                ", number is " + numberWay +
                ", station is "+station.getNameStation()+
                " }";
    }
}
