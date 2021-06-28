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
    public String saveVisit(@RequestBody List<VisitModel> visitModels){
        return visitService.saveVisit(visitModels);
    }
    @GetMapping("/{id}")
    public Visit getVisitById(@PathVariable Long id) {
        return visitService.getVisitById(id);
    }
    @GetMapping
    public List<Visit> getAllVisit(){
        return visitService.getAllVisits();
    }
//    @GetMapping("/child/{id}/month/{month}")
//    public List<Visit> findAllByChild_IdAndDate_DayOfMonth(@PathVariable Long id,@PathVariable int month){
//        return visitService.findAllByChild_IdAndDate_DayOfMonth(id,month);
//    }
//    @GetMapping("/group/{id}")
//    public List<Visit> getAllCalendarByGroupId(@PathVariable Long id){
//        return visitService.findAllByGroup_Id(id);
//    }
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
