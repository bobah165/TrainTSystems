package com.trains.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/main")
public class MainPageController {

    private static Logger logger = Logger.getLogger(MainPageController.class);
          //  getLogger(MainPageController.class);

    @GetMapping(value = "/pass")
    public ModelAndView getPassengerPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/main/passenger");
        logger.debug("Read view /main/passenger.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/empl")
    public ModelAndView getEmployeePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/main/employee");
        logger.debug("Read view /main/employee.jsp");
        return modelAndView;
    }
}
