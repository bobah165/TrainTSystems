package com.trains.service;

import com.trains.dao.TrainFromStationDAO;
import com.trains.model.dto.TrainFromStationDTO;
import com.trains.model.entity.TrainFromStation;
import com.trains.util.mapperForDTO.TrainFromStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainFromStationService {
    private TrainFromStationDAO trainFromStationDAO;
    private TrainFromStationMapper trainFromStationMapper;

    @Autowired
    public void setTrainFromStationMapper(TrainFromStationMapper trainFromStationMapper) {
        this.trainFromStationMapper = trainFromStationMapper;
    }

    @Autowired
    public void setTrainFromStationDAO(TrainFromStationDAO trainFromStationDAO) {
        this.trainFromStationDAO = trainFromStationDAO;
    }

    public List<TrainFromStationDTO> allTrains() {
        List<TrainFromStation> trains = trainFromStationDAO.allTrain();
        List<TrainFromStationDTO> trainDTOS = new ArrayList<>();
        for (TrainFromStation train: trains) {
            trainDTOS.add(trainFromStationMapper.mapEntityToDto(train));
        }
        return trainDTOS;
    }

    public void add(TrainFromStationDTO trainFromStationDTO) {
        TrainFromStation train = trainFromStationMapper.mapDtoToEntity(trainFromStationDTO);
        trainFromStationDAO.add(train); }

    public void delete(TrainFromStationDTO trainFromStationDTO) {
        TrainFromStation train = trainFromStationMapper.mapDtoToEntity(trainFromStationDTO);
        trainFromStationDAO.delete(train); }

    public void edit(TrainFromStationDTO trainFromStationDTO) {
        TrainFromStation train = trainFromStationMapper.mapDtoToEntity(trainFromStationDTO);
        trainFromStationDAO.edit(train);
    }


    public TrainFromStationDTO getById(int id) {
        TrainFromStation train = trainFromStationDAO.getById(id);
        TrainFromStationDTO trainDTO = trainFromStationMapper.mapEntityToDto(train);
        return trainDTO; }
}
