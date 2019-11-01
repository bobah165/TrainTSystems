package com.trains.controller;

import com.trains.model.dto.PassengerDTO;
import com.trains.service.PassengerService;
import com.trains.util.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/")
public class LoginController {
    private PassengerService passengerService;
    private UserValidator userValidator;
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

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
        logger.info("Read view main/main-page.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/registration")
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/auth-view/registration");
        logger.info("Read view /auth-view/registration.jsp");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView registration (@ModelAttribute PassengerDTO passengerDTO) {
        ModelAndView modelAndView = new ModelAndView();
        passengerDTO.setUser("passenger");
        passengerService.add(passengerDTO);
        logger.info("Add object Passenger from frontend");
        modelAndView.setViewName("redirect:/login");
        return modelAndView;

    }

    @GetMapping(value = "/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/auth-view/new-login");
        logger.info("Read view /auth-view/new-login.jsp");
        return modelAndView;
    }

}
