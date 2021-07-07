package it.academy.project.projectonspring.controller;

import it.academy.project.projectonspring.entity.Visit;
import it.academy.project.projectonspring.model.MonthModel;
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
    public List<VisitModel> saveVisit(@RequestBody List<VisitModel> visitModels){
        return visitService.saveVisit(visitModels);
    }

    @PostMapping("/month")
    public List<Visit> getAllVisitByGroupIdAndMonth(@RequestBody MonthModel monthModel){
        return visitService.findAllByGroup_IdAndMonth(monthModel);
    }
    @PostMapping("/month/true")
    public List<Visit> getAllVisitByGroupIdAndMonthVisitTrue(@RequestBody MonthModel monthModel){
        return visitService.findAllByGroup_IdAndMonthVisitTrue(monthModel);
    }
    @PostMapping("/month/false")
    public List<Visit> getAllVisitByGroupIdAndMonthVisitFalse(@RequestBody MonthModel monthModel){
        return visitService.findAllByGroup_IdAndMonthVisitFalse(monthModel);
    }
    @GetMapping("/{id}")
    public Visit getVisitById(@PathVariable Long id) {
        return visitService.getVisitById(id);
    }
    @GetMapping
    public List<Visit> getAllVisit(){
        return visitService.getAllVisits();
    }

    @GetMapping("/group/{id}")
    public List<Visit> getAllCalendarByGroupId(@PathVariable Long id){
        return visitService.findAllByGroup_Id(id);
    }
    @GetMapping("/child/{id}")
    public List<Visit> getAllVisitByChildId(@PathVariable Long id){
        return visitService.findAllByChild_Id(id);
    }
    @PutMapping("/{id}")
    public Visit updateVisit(@PathVariable Long id, @RequestBody VisitModel visitModel){
        return visitService.updateVisit(visitModel,id);
    }
    @DeleteMapping("/{id}")
    public Visit deleteVisit(@PathVariable Long id){
        return visitService.deleteVisit(id);
    }
}
