package com.trains.util.MyExeptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ExeptionControllerAdvice {
    @ExceptionHandler(MyException.class)
    public ModelAndView handleException(MyException ex) {
        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", ex.getErrMsg());
        model.setViewName("passenger-view/buy-ticket");
        return model;
    }

    @ExceptionHandler(MyExeptionForPassenger.class)
    public ModelAndView passengerException(MyExeptionForPassenger ex) {
        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", ex.getErrMsg());
        model.setViewName("auth-view/registration");
        return model;
    }


    @ExceptionHandler(MyExeptionForTrain.class)
    public ModelAndView trainException(MyExeptionForTrain ex) {
        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", ex.getErrMsg());
        model.setViewName("train-view/add-train");
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView AllExeptions (Exception ex){
        ModelAndView model = new ModelAndView();
        model.setViewName("main/exeption-page");
        return model;
    }

    @ExceptionHandler(MyExeptionForTicket.class)
    public ModelAndView ticketExeption(MyExeptionForTicket ex){
        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", ex.getErrMsg());
        model.setViewName("train-view/trains");
        return model;
    }

    @ExceptionHandler(MyExeptionForStation.class)
    public ModelAndView stationExeption(MyExeptionForStation ex){
        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", ex.getErrMsg());
        model.setViewName("station-view/add-station");
        return model;
    }
}
