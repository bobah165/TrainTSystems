package com.trains.service;

import com.trains.dao.TrainDAO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.entity.Train;
import com.trains.util.TrainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainService {
    private TrainMapper trainMapper;
    private TrainDAO trainDAO;

    @Autowired
    public void setTrainDAO (TrainDAO trainDAO) {
        this.trainDAO = trainDAO;
    }

    @Autowired
    public void setTrainMapper(TrainMapper trainMapper) {
        this.trainMapper = trainMapper;
    }

    public List<TrainDTO> allTrains() {
        List<Train> trains = trainDAO.allTrain();
        List<TrainDTO> trainDTOS = new ArrayList<>();
        for (Train train: trains) {
            trainDTOS.add(trainMapper.mapEntityToDto(train));
        }
        return trainDTOS;
    }

    public void add(TrainDTO trainDTO) {
        Train train = trainMapper.mapDtoToEntity(trainDTO);
        trainDAO.add(train);
    }

    public void delete(TrainDTO trainDTO) {
        Train train = trainMapper.mapDtoToEntity(trainDTO);
        trainDAO.delete(train);
    }

    public void edit(TrainDTO trainDTO) {
        Train train = trainMapper.mapDtoToEntity(trainDTO);
        trainDAO.edit(train);
    }

    public TrainDTO getById(int idTrain) {
        Train train = trainDAO.getById(idTrain);
        TrainDTO trainDTO = trainMapper.mapEntityToDto(train);
        return trainDTO;
    }

    public void delByID (int id) { trainDAO.delByID(id); }

}

