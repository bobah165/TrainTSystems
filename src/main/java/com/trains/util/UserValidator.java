package com.trains.util;

import com.trains.model.dto.PassengerDTO;
import com.trains.model.entity.User;
import com.trains.service.PassengerService;
import com.trains.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private PassengerService passengerService;

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @Autowired
    private UserService userService;



    @Override
    public boolean supports(Class<?> clazz) {
        return PassengerDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PassengerDTO passengerDTO = (PassengerDTO) target;
        if (passengerService.getPassengerBylogin(passengerDTO.getLogin()) != null) {
            errors.rejectValue(
                    "email", "", "This email is already in use"
            );
        }
    }
}
