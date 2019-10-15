package com.trains.service;

import com.trains.dao.TrainDAO;
import com.trains.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrainService {

    private TrainDAO trainDAO;

    @Autowired
    public void setTrainDAO (TrainDAO trainDAO) {
        this.trainDAO = trainDAO;
    }

    public List<Train> allTrains() {
        return trainDAO.allTrain();
    }

    public void add(Train train) {
        trainDAO.add(train);
    }

    public void delete(Train train) {
        trainDAO.delete(train);
    }

    public void edit(Train train) {
        trainDAO.edit(train);
    }

    public Train getById(int idTrain) {
        return trainDAO.getById(idTrain);
    }

}

