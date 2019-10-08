package com.trains.service;

import com.trains.dao.TrainDAO;
import com.trains.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {
    private TrainDAO trainDAO;

    @Autowired
    public void setTrainDAO (TrainDAO trainDAO) {
        this.trainDAO = trainDAO;
    }

    @Override
    @Transactional
    public List<Train> allTrains() {
        return trainDAO.allTrains();
    }

    @Override
    @Transactional
    public void add(Train train) {
        trainDAO.add(train);
    }

    @Override
    @Transactional
    public void delete(Train train) {
        trainDAO.delete(train);
    }

    @Override
    @Transactional
    public void edit(Train train) {
        trainDAO.edit(train);
    }

    @Override
    @Transactional
    public Train getById(int idTrain) {
        return trainDAO.getById(idTrain);
    }
}
