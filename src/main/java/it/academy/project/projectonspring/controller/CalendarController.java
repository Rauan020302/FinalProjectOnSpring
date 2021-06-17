package it.academy.project.projectonspring.controller;

import it.academy.project.projectonspring.entity.Calendar;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.CalendarModel;
import it.academy.project.projectonspring.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
    @Autowired
    private CalendarService calendarService;

    @PostMapping
    public Calendar saveCalendar(@RequestBody CalendarModel calendarModel) throws ObjectsNotFoundException {
        return calendarService.saveCalendar(calendarModel);
    }
    @GetMapping("/{id}")
    public Calendar getCalendarById(@PathVariable Long id) throws ObjectsNotFoundException {
        return calendarService.getCalendarById(id);
    }
    @GetMapping
    public List<Calendar> getAllCalendar(){
            return calendarService.getAllCalendar();
    }
    @GetMapping("/group/{id}")
    public List<Calendar> getAllCalendarByGroupId(@PathVariable Long id){
        return calendarService.findAllByGroup_Id(id);
    }
    @GetMapping("/child/{id}")
    public List<Calendar> getAllCalendarByChildId(@PathVariable Long id){
        return calendarService.findAllByChild_Id(id);
    }
    @PutMapping("/{id}")
    public Calendar updateCalendar(@PathVariable Long id,@RequestBody CalendarModel calendarModel) throws ObjectsNotFoundException {
        return calendarService.updateCalendar(calendarModel,id);
    }
    @DeleteMapping("/{id}")
    public Calendar deleteCalendar(@PathVariable Long id) throws ObjectsNotFoundException {
        return calendarService.deleteCalendar(id);
    }
}
