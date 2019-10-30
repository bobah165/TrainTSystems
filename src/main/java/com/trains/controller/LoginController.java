package com.trains.controller;

import com.trains.model.dto.PassengerDTO;
import com.trains.model.entity.User;
import com.trains.service.PassengerService;
import com.trains.service.UserService;
import com.trains.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/")
public class LoginController {
    private PassengerService passengerService;
    private UserValidator userValidator;

    @Autowired
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }


    @GetMapping(value = "/")
    public ModelAndView getStartPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main/main-page");
        return modelAndView;
    }

    @GetMapping(value = "/registration")
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
     //   modelAndView.addObject("user",new PassengerDTO());
        modelAndView.setViewName("/auth-view/registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView registration (@ModelAttribute @Valid PassengerDTO passengerDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
//        userValidator.validate(passengerDTO, result);
//        if(result.hasErrors()) {
//            modelAndView.setViewName("/auth-view/registration");
//            return modelAndView;
//        }
        passengerService.add(passengerDTO);
        modelAndView.setViewName("redirect:/login");
        return modelAndView;

    }

    @GetMapping(value = "/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/auth-view/new-login");
        return modelAndView;
    }

}
