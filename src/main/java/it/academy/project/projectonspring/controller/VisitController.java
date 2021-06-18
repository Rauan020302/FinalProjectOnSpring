package it.academy.project.projectonspring.controller;

import it.academy.project.projectonspring.entity.Visit;
import it.academy.project.projectonspring.model.VisitModel;
import it.academy.project.projectonspring.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitController {
    @Autowired
    private VisitService visitService;

    @PostMapping
    public Visit saveCalendar(@RequestBody VisitModel visitModel){
        return visitService.saveVisit(visitModel);
    }
    @GetMapping("/{id}")
    public Visit getCalendarById(@PathVariable Long id) {
        return visitService.getVisitById(id);
    }
    @GetMapping
    public List<Visit> getAllCalendar(){
            return visitService.getAllVisits();
    }
    @GetMapping("/group/{id}")
    public List<Visit> getAllCalendarByGroupId(@PathVariable Long id){
        return visitService.findAllByGroup_Id(id);
    }
    @GetMapping("/child/{id}")
    public List<Visit> getAllCalendarByChildId(@PathVariable Long id){
        return visitService.findAllByChild_Id(id);
    }
    @PutMapping("/{id}")
    public Visit updateCalendar(@PathVariable Long id, @RequestBody VisitModel visitModel){
        return visitService.updateVisit(visitModel,id);
    }
    @DeleteMapping("/{id}")
    public Visit deleteCalendar(@PathVariable Long id){
        return visitService.deleteVisit(id);
    }
}
