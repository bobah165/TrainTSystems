package com.trains.util.validator;

import com.trains.model.dto.TrainDTO;
import com.trains.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class TrainDTOValidator implements Validator {
    private TrainService trainService;

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TrainDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TrainDTO trainDTO = (TrainDTO) o;
        List<TrainDTO> trainDTOS = trainService.allTrains();
        for (TrainDTO train: trainDTOS) {
            if (train.getTrainNumber()==trainDTO.getTrainNumber()&&
            train.getDepartureDate().equals(trainDTO.getDepartureDate())) {
                errors.rejectValue(
                        "trainNumber", "", "There is this train"
                );
            }
        }

    }
}
