package it.academy.project.projectonspring.controller;

import it.academy.project.projectonspring.entity.Child;
import it.academy.project.projectonspring.model.ChildModel;
import it.academy.project.projectonspring.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/child")
public class ChildController {
    @Autowired
    private ChildService childService;

    @PostMapping
    public Child saveChild(@RequestBody ChildModel childModel){
        return childService.saveChild(childModel);
    }
    @GetMapping
    public List<Child> getChild(){
        return childService.getChild();
    }
    @GetMapping("/group/{id}")
    public List<Child> getChildByGroupId(@PathVariable Long id){
        return childService.findAllByGroup_Id(id);
    }
    @GetMapping("/{id}")
    public Child getChildById(@PathVariable Long id){
        return childService.getChildById(id);
    }
    @DeleteMapping("/{id}")
    public Child deleteChildById(@PathVariable Long id){
        return childService.deleteChildById(id);
    }
    @PutMapping("/{id}")
    public Child updateChildById(@RequestBody ChildModel childModel,
                                 @PathVariable Long id){
        return childService.updateChild(childModel,id);
    }


}

