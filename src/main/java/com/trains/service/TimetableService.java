package com.trains.service;

import com.trains.dao.TimetableDAO;
import com.trains.model.dto.TimetableDTO;
import com.trains.model.entity.Station;
import com.trains.model.entity.Timetable;
import com.trains.model.entity.Train;
import com.trains.util.mapperForDTO.TimetableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TimetableService {
    private TimetableMapper timetableMapper;
    private TimetableDAO timetableDAO;

    @Autowired
    public void setTimetableDAO(TimetableDAO timetableDAO) {
        this.timetableDAO = timetableDAO;
    }

    @Autowired
    public void setTimetableMapper(TimetableMapper timetableMapper) {
        this.timetableMapper = timetableMapper;
    }

    public List<TimetableDTO> allTimetable() {
        List<Timetable> timetables = timetableDAO.allTimetable();
        List<TimetableDTO> timetableDTOS = new ArrayList<>();
        for (Timetable timetable: timetables) {
            timetableDTOS.add(timetableMapper.mapEntityToDto(timetable));
        }
        return timetableDTOS;
    }

    public void add(TimetableDTO timetableDTO) {
        Timetable timetable = timetableMapper.mapDtoToEntity(timetableDTO);
        timetableDAO.add(timetable); }

    public void delete(TimetableDTO timetableDTO) {
        Timetable timetable = timetableMapper.mapDtoToEntity(timetableDTO);
        timetableDAO.delete(timetable); }

    public void edit(TimetableDTO timetableDTO) {
        Timetable timetable = timetableMapper.mapDtoToEntity(timetableDTO);
        timetableDAO.edit(timetable); }

    public TimetableDTO getById(int id) {
        Timetable timetable = timetableDAO.getById(id);
        TimetableDTO timetableDTO = timetableMapper.mapEntityToDto(timetable);
        return timetableDTO; }

    public void delByID (int id) { timetableDAO.delByID(id); }

    public TimetableDTO getTimetableByTrainAndStation (Train train, Station station) {
        Timetable timetable = timetableDAO.getTimetableByTrainAndStation(train,station);
        TimetableDTO timetableDTO = timetableMapper.mapEntityToDto(timetable);
        return timetableDTO;
    }

}
