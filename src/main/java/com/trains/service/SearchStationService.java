package com.trains.service;

import com.trains.dao.SearchStationDAO;
import com.trains.model.dto.SearchStationDTO;
import com.trains.model.entity.SearchStations;
import com.trains.util.mapperForDTO.SearchStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class SearchStationService {
    private SearchStationDAO searchStationDAO;
    private SearchStationMapper searchStationMapper;

    @Autowired
    public void setSearchStationDAO(SearchStationDAO searchStationDAO) {
        this.searchStationDAO = searchStationDAO;
    }

    @Autowired
    public void setSearchStationMapper(SearchStationMapper searchStationMapper) {
        this.searchStationMapper = searchStationMapper;
    }

    public void add(SearchStationDTO searchStationDTO) {
        SearchStations searchStations = searchStationMapper.mapDtoToEntity(searchStationDTO);
        searchStationDAO.add(searchStations);
    }

    public void delete(SearchStationDTO searchStationDTO) {
        SearchStations searchStations = searchStationMapper.mapDtoToEntity(searchStationDTO);
        searchStationDAO.delete(searchStations);
    }

    public void edit(SearchStationDTO searchStationDTO) {
        SearchStations searchStations = searchStationMapper.mapDtoToEntity(searchStationDTO);
        searchStationDAO.edit(searchStations);
    }

    public SearchStationDTO getById(int id) {
        SearchStations searchStations = searchStationDAO.getById(id);
        SearchStationDTO searchStationDTO = searchStationMapper.mapEntityToDto(searchStations);
        return searchStationDTO;
    }

    public List<SearchStationDTO> allTrains() {
        List<SearchStations> searchStations = searchStationDAO.allTrains();
        List<SearchStationDTO> searchStationDTOS = new ArrayList<>();
        for (SearchStations searchStation: searchStations) {
            searchStationDTOS.add(searchStationMapper.mapEntityToDto(searchStation));
        }
        return searchStationDTOS;
    }

    public void delByID (int id) {searchStationDAO.delByID(id);}
}
