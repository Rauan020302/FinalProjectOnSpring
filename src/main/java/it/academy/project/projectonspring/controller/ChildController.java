package it.academy.project.projectonspring.controller;

import it.academy.project.projectonspring.entity.Child;
import it.academy.project.projectonspring.model.ChildWithoutVisitModel;
import it.academy.project.projectonspring.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/child")
public class ChildController {
    @Autowired
    private ChildService childService;

    @PostMapping
    public Child saveChild(@RequestBody it.academy.project.projectonspring.model.ChildModel childModel){
        return childService.saveChild(childModel);
    }
    @GetMapping("/date")
    public List<Child> findAllByBirthDayAfter(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date ){
        return childService.findAllByBirthDayAfter(date);
    }

    @GetMapping
    public List<ChildWithoutVisitModel> getChild(){
        return childService.getChild();
    }
    @GetMapping("/group/{id}")
    public List<Child> getChildByGroupId(@PathVariable Long id){
        return childService.findAllByGroup_Id(id);
    }
    @GetMapping("/{id}")
    public ChildWithoutVisitModel getChildById(@PathVariable Long id){
            return childService.getChildById(id);
    }
    @GetMapping("/garden/{id}")
    public List<ChildWithoutVisitModel> findAllByGroupKinderGarden_Id(@PathVariable Long id){
        return childService.findAllByGroupKinderGarden_Id(id);
    }
    @DeleteMapping("/{id}")
    public Child deleteChildById(@PathVariable Long id){
        return childService.deleteChildById(id);
    }
    @PutMapping("/{id}")
    public Child updateChildById(@RequestBody it.academy.project.projectonspring.model.ChildModel childModel,
                                 @PathVariable Long id){
        return childService.updateChild(childModel,id);
    }


}

