package com.trains.controller;

import com.trains.model.dto.TimetableDTO;
import com.trains.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/timetable")
public class TimetableController {
    private TimetableService timetableService;

    @Autowired
    public void setTimetableService(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @GetMapping(value = "/")
    public ModelAndView allTimetable() {
        List<TimetableDTO> timetables = timetableService.allTimetable();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("timetable-view/timetables");
        modelAndView.addObject("timetablesList",timetables);
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView getEditPage (@PathVariable("id") int id) {
        TimetableDTO timetable = timetableService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("timetable-view/edit-timetable");
        modelAndView.addObject("timetable",timetable);
        return modelAndView;
    }


    @PostMapping(value = "/edit")
    public ModelAndView update(@ModelAttribute("timetable") TimetableDTO timetable) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/timetable/");
        timetableService.edit(timetable);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView getAddPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("timetable-view/add-timetable");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView create(@ModelAttribute("timetable") TimetableDTO timetable) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/timetable/");
        timetableService.add(timetable);
        return modelAndView;
    }

    @GetMapping (value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/timetable/");
        timetableService.delByID(id);
        return modelAndView;
    }

}
