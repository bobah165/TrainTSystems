package com.trains.service;

import com.trains.dao.TrainWayDAO;
import com.trains.model.dto.TrainWayDTO;
import com.trains.model.entity.TrainWay;
import com.trains.util.mapperForDTO.TrainWayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainWayService {
    private TrainWayDAO trainWayDAO;
    private TrainWayMapper trainWayMapper;

    @Autowired
    public void setTrainWayDTO(TrainWayDAO trainWayDAO) {
        this.trainWayDAO = trainWayDAO;
    }

    @Autowired
    public void setTrainWayMapper(TrainWayMapper trainWayMapper) {
        this.trainWayMapper = trainWayMapper;
    }

    public List<TrainWayDTO> getAllWays() {
        List<TrainWay> trainWays = trainWayDAO.getAllWays();
        List<TrainWayDTO> trainWayDTOS = new ArrayList<>();
        for (TrainWay trainWay: trainWays) {
            trainWayDTOS.add(trainWayMapper.mapEntityToDto(trainWay));
        }

        return trainWayDTOS;
    }

    public void add(TrainWayDTO trainWayDTO) {
        TrainWay trainWay = trainWayMapper.mapDtoToEntity(trainWayDTO);
        trainWayDAO.add(trainWay); }

    public void delete(TrainWayDTO trainWayDTO) {
        TrainWay trainWay = trainWayMapper.mapDtoToEntity(trainWayDTO);
        trainWayDAO.delete(trainWay); }

    public void edit(TrainWayDTO trainWayDTO) {
        TrainWay trainWay = trainWayMapper.mapDtoToEntity(trainWayDTO);
        trainWayDAO.edit(trainWay); }

    public TrainWayDTO getById(int id) {
        TrainWay trainWay = trainWayDAO.getById(id);
        return trainWayMapper.mapEntityToDto(trainWay);
    }

    public void delByID (int id) {trainWayDAO.delByID(id); }
}
