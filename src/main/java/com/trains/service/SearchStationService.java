package com.trains.service;

import com.trains.dao.*;
import com.trains.model.dto.PassengerDTO;
import com.trains.model.dto.SearchStationDTO;
import com.trains.model.dto.TrainFromStationAToB;
import com.trains.model.entity.*;
import com.trains.util.mapperForDTO.SearchStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class SearchStationService {
    private SearchStationDAO searchStationDAO;
    private TrainWayDAO trainWayDAO;
    private SearchStationMapper searchStationMapper;
    private FreeSeatsDAO freeSeatsDAO;
    private TrainDAO trainDAO;


    @Autowired
    public void setFreeSeatsDAO(FreeSeatsDAO freeSeatsDAO) {
        this.freeSeatsDAO = freeSeatsDAO;
    }

    @Autowired
    public void setTrainWayDAO(TrainWayDAO trainWayDAO) {
        this.trainWayDAO = trainWayDAO;
    }

    @Autowired
    public void setTrainDAO(TrainDAO trainDAO) {
        this.trainDAO = trainDAO;
    }

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

    public List<SearchStationDTO> getAllStations() {
        List<SearchStations> searchStations = searchStationDAO.allTrains();
        List<SearchStationDTO> searchStationDTOS = new ArrayList<>();
        for (SearchStations searchStation: searchStations) {
            searchStationDTOS.add(searchStationMapper.mapEntityToDto(searchStation));
        }
        return searchStationDTOS;
    }

    public void delByID (int id) {searchStationDAO.delByID(id);}



    public List<TrainWay> getTrainWaysFromStationAToB(String stationNameA, String stationNameB) {
        // находим инофрмаци о станциях в БД
        Station stationA = searchStationDAO.getStationByName(stationNameA);
        Station stationB = searchStationDAO.getStationByName(stationNameB);

        List<TrainWay> trainWaysBetweenAandB = new ArrayList<>();

        List<TrainWay> getTrainWaysA = trainWayDAO.getWaysByStationId(stationA.getId());
        List<TrainWay> getTrainWaysB = trainWayDAO.getWaysByStationId(stationB.getId());

        // находим маршруты поездов проходящих и через А и через В
        for (TrainWay trainWayA: getTrainWaysA ) {
            for (TrainWay trainWayB: getTrainWaysB) {
                if (trainWayA.getNumberWay() == trainWayB.getNumberWay()) {
                    List<TrainWay> oneWay = trainWayDAO.getWaysByNumberWay(trainWayA.getNumberWay());

                    List<TrainWay> sortedTrainWay= oneWay.stream()
                            .sorted(Comparator.comparing(TrainWay::getDaysInWay)
                                    .thenComparing(TrainWay::getDepartureTime))
                            .collect(Collectors.toList());

                    if (sortedTrainWay.indexOf(trainWayA)<sortedTrainWay.indexOf(trainWayB)) {
                        trainWaysBetweenAandB.add(trainWayA);
                    }
                }
            }
        }
        return trainWaysBetweenAandB;
    }




    public List<TrainFromStationAToB> getTrainsWithCorrectDate(List<Train> trainsFromAtoB, List<TrainFromStationAToB> trainFromStationAToBS,String stationNameA, String stationNameB,LocalDate departureDate) {

        int days = 0; // количество дней в пути
        // получение даты у поездов которые несколько дней в пути
        for (Train train:trainsFromAtoB) {
            TrainWay trainWayByStationAndWay = trainWayDAO.getTrainWayByStationAndWay(stationNameA,train.getTrainWay().getNumberWay());
            days = trainWayByStationAndWay.getDaysInWay();

            if (train.getDepartureDate().plusDays(days-1).isEqual(departureDate)) {
                TrainFromStationAToB trainFromStationAToB = new TrainFromStationAToB();
                trainFromStationAToB.setTrainID(train.getId());
                trainFromStationAToB.setCountFreeSits(train.getCountSits());
                trainFromStationAToB.setArrivalStation(stationNameB);
                trainFromStationAToB.setDeprtureStation(stationNameA);

                Time depTime = trainWayDAO.getTrainWayByStationAndWay(stationNameA,train.getTrainWay().getNumberWay()).getDepartureTime();
                Time arrivTime = trainWayDAO.getTrainWayByStationAndWay(stationNameB,train.getTrainWay().getNumberWay()).getArrivalTime();

                trainFromStationAToB.setDepartureTime((depTime.toLocalTime()));
                trainFromStationAToB.setArrivalTime(arrivTime.toLocalTime());

                trainFromStationAToBS.add(trainFromStationAToB);
            }
        }
        return trainFromStationAToBS;
    }



    public List<TrainFromStationAToB> getTrainsFromStations (String stationNameA, String stationNameB, LocalTime startTime, LocalTime endTime, LocalDate departureDate) {

        List<TrainFromStationAToB> trainFromStationAToBS = new ArrayList<>();
        // ищем маршруты проходящие станции А и В
        List<TrainWay> trainWaysBetweenAandB = getTrainWaysFromStationAToB(stationNameA,stationNameB);

        //ищем поезда проезжающие по этим маршрутам
        List<Train> trainsFromAtoB = new ArrayList<>();
            for (TrainWay trainWay: trainWaysBetweenAandB) {
                    trainsFromAtoB.addAll(trainDAO.getTrainByNumberWay(trainWay.getNumberWay()));
                }
        //корректировка списка поездов с учетом тех поездов, которые находятся в пути несколько дней
        trainFromStationAToBS = getTrainsWithCorrectDate(trainsFromAtoB,trainFromStationAToBS,stationNameA,stationNameB,departureDate);

        //ищем поезда в деапазоне времени
        List<TrainFromStationAToB> trainFromStationAToBS1 = new ArrayList<>();
        trainFromStationAToBS1.addAll(trainFromStationAToBS);
        for (TrainFromStationAToB trainFromStationAToB: trainFromStationAToBS) {
            if(!(trainFromStationAToB.getDepartureTime().isAfter(startTime)&&trainFromStationAToB.getDepartureTime().isBefore(endTime))){
                trainFromStationAToBS1.remove(trainFromStationAToB);
            }
        }
        // задаем количество свободных мест
        for(TrainFromStationAToB trainFromStationAToB: trainFromStationAToBS1) {
            FreeSeats freeSeat = freeSeatsDAO.getByStationAndTrainID(trainFromStationAToB.getDeprtureStation(),trainFromStationAToB.getTrainID());
            if(!freeSeat.equals(new FreeSeats())) {
                trainFromStationAToB.setCountFreeSits(freeSeat.getFreeSeats());
            }
        }

        return trainFromStationAToBS1;
    }




    public void addInformationAboutSearch (String stationA, String stationB, LocalDate departureDate, String startTime, String endTime) {

        int idCurrentPassenger = ((PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        SearchStations searchStation = new SearchStations();
        searchStation.setId(idCurrentPassenger);
        searchStation.setDepartureDate(departureDate);
        searchStation.setStartTime(LocalTime.parse(startTime));
        searchStation.setDepartureStation(stationA);
        searchStation.setArrivalStation(stationB);
        searchStation.setEndTime(LocalTime.parse(endTime));

        searchStationDAO.add(searchStation);

    }


    public void addTrainBySchedule (LocalDate departureDate) {

        List<Train> trainList = new ArrayList<>();
        boolean everydayFlag = true;
        boolean oddFlag = true;
        boolean evenFlag = true;

        Set<Train> trains =new HashSet<>(trainDAO.allTrain());

        for (Train train : trains) {
            // поезд с ежедневным расписанием
            if (train.getSchedule().equals("everyday")&&everydayFlag) {
                everydayFlag = false;
                Train newTrain = new Train();
                newTrain.setDepartureDate(departureDate);
                newTrain.setSchedule(train.getSchedule());
                newTrain.setCountSits(train.getCountSits());
                newTrain.setTrainNumber(train.getTrainNumber());
                newTrain.setTrainWay(train.getTrainWay());
                trainList.add(newTrain);
                continue;
            }
            // поезд двигающийся по четным дням
            if (departureDate.getDayOfMonth() % 2 == 0 && train.getSchedule().equals("even") && evenFlag) {
                evenFlag = false;
                Train newTrain = new Train();
                newTrain.setDepartureDate(departureDate);
                newTrain.setSchedule(train.getSchedule());
                newTrain.setCountSits(train.getCountSits());
                newTrain.setTrainNumber(train.getTrainNumber());
                newTrain.setTrainWay(train.getTrainWay());
                trainList.add(newTrain);
                continue;
            }

            // поезд двигающийся по нечетным дням
            if (departureDate.getDayOfMonth() % 2 != 0 && train.getSchedule().equals("odd")&&oddFlag) {
                oddFlag = false;
                Train newTrain = new Train();
                newTrain.setDepartureDate(departureDate);
                newTrain.setSchedule(train.getSchedule());
                newTrain.setCountSits(train.getCountSits());
                newTrain.setTrainNumber(train.getTrainNumber());
                newTrain.setTrainWay(train.getTrainWay());
                trainList.add(newTrain);
                continue;
            }
        }

        List<Train> trainIndate = trainDAO.getTrainsByDepartureDate(departureDate);

        if (trainIndate.size()==trainList.size()) {
            return;
        }else {
            for (Train train: trainList) {
                if (!trainIndate.contains(train)) {
                    trainDAO.add(train);
                }
            }
        }
    }
}
