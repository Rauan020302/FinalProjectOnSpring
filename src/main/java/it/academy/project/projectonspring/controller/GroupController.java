package it.academy.project.projectonspring.controller;

import it.academy.project.projectonspring.entity.Group;
import it.academy.project.projectonspring.model.GroupModel;
import it.academy.project.projectonspring.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping
    public Group saveGroup(@RequestBody GroupModel groupModel){
        return groupService.saveGroup(groupModel);
    }

    @GetMapping("/get/{id}")
    public List<Group> getGroupByKGId(@PathVariable Long id){
        return groupService.findAllByKinderGarden_Id(id);
    }

    @GetMapping
    public List<Group> getAllGroups(){
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable Long id){
        return groupService.getGroupById(id);
    }

    @DeleteMapping("/{id}")
    public Group deleteGroupById(@PathVariable Long id){
        return groupService.deleteGroupById(id);
    }

    @PutMapping("/{id}")
    public Group updateGroupById(@RequestBody GroupModel groupModel,@PathVariable Long id){
        return groupService.updateGroupById(groupModel,id);
    }
}

