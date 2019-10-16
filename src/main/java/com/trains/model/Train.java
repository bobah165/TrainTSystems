package com.trains.model;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trains")
public class Train {
    @Id
    @Column (name = "id_train")
    private int id;
    @Column (name = "start_station")
    private String startStationName;
    @Column (name = "end_station")
    private String endStationName;
    @Column(name = "count_free_sits")
    private int countFreeSits;

    @OneToMany(mappedBy = "train",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Passenger> passengers;


    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public int getId() {
        return id;
    }

    public void setId(int idTrain) {
        this.id = idTrain;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public void setStartStationName(String stationName) {
        this.startStationName = stationName;
    }

    public String getEndStationName() { return endStationName; }

    public void setEndStationName(String endStationName) { this.endStationName = endStationName; }

    public int getCountFreeSits() {
        return countFreeSits;
    }

    public void setCountFreeSits(int countSits) {
        this.countFreeSits = countSits;
    }

    @Override
    public String toString() {
        return "TrainEntity{" +
                "id=" + id +
                ", Start station is " + startStationName +
                ", End station is "+endStationName+
                ", countFreeSits= " + countFreeSits + " }";
    }

}
