package com.trains.model.entity;


import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Table(name = "schedule")
public class TrainFromStation {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_train")
    private int idTrain;
    @Column(name = "name_station")
    private String nameStation;
    @Column(name = "departure_time")
    private LocalTime departureTime;
    @Column(name = "arrival_time")
    private LocalTime arrivalTime;
    @Column(name="days_in_way")
    private int daysInWay;

    public int getDaysInWay() {
        return daysInWay;
    }

    public void setDaysInWay(int days) {
        this.daysInWay = days;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Train Way {" +
                "id=" + id +
                ", train is " + idTrain +
                ", station is "+nameStation+
                " }";
    }


}
