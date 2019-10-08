package com.trains.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "trains")
public class Train {

    @Id
    @Column (name = "idTrain")
    private int idTrain;
    @Column (name = "station")
    private String stationName;
    @Column(name = "countFreeSits")
    private int countFreeSits;


    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getCountFreeSits() {
        return countFreeSits;
    }

    public void setCountFreeSits(int countSits) {
        this.countFreeSits = countSits;
    }

    @Override
    public String toString() {
        return "train number "+getIdTrain()+"  "+
                "station "+getStationName()+"  "+
                "count of sits "+getCountFreeSits();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return getIdTrain() == train.getIdTrain() &&
                getCountFreeSits() == train.getCountFreeSits() &&
                Objects.equals(getStationName(), train.getStationName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdTrain(), getStationName(), getCountFreeSits());
    }
}
