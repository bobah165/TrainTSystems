package com.trains.model;

import com.trains.service.TrainService;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TrainStationId implements Serializable {

    @Column(name = "id_train")
    private int idTrain;
    @Column (name = "id_station")
    private int idStation;

    public TrainStationId() {}

    public TrainStationId(int idTrain, int idStation) {
        this.idTrain = idTrain;
        this.idStation = idStation;
    }

    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    public int getIdStation() {
        return idStation;
    }

    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TrainStationId that = (TrainStationId) object;
        return idTrain == that.idTrain &&
                idStation == that.idStation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTrain, idStation);
    }
}
