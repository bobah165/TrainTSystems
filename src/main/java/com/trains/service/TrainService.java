package com.trains.service;

import com.trains.dao.TrainDAOImpl;
import com.trains.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainService {

    private TrainDAOImpl trainDAO;

    @Autowired
    public void setTrainDAO (TrainDAOImpl trainDAO) {
        this.trainDAO = trainDAO;
    }


    @Transactional
    public List<Train> allTrains() {
        return trainDAO.allTrain();
    }

    @Transactional
    public void add(Train train) {
        trainDAO.add(train);
    }

    @Transactional
    public void delete(Train train) {
        trainDAO.delete(train);
    }

    @Transactional
    public void edit(Train train) {
        trainDAO.edit(train);
    }

    @Transactional
    public Train getById(int idTrain) {
        return trainDAO.getById(idTrain);
    }
}

