package it.academy.project.projectonspring.controller;

import it.academy.project.projectonspring.entity.CourseGroup;
import it.academy.project.projectonspring.model.CourseGroupModel;
import it.academy.project.projectonspring.service.CourseGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course_group")
public class CourseGroupController {
    @Autowired
    private CourseGroupService courseGroupService;

    @PostMapping
    public CourseGroup save(@RequestBody CourseGroupModel courseGroupModel){
        return courseGroupService.save(courseGroupModel);
    }

    @GetMapping("/course/{id}")
    public List<CourseGroup> getCourseGroupByCourseId(@PathVariable Long id){
        return courseGroupService.findAllByCourse_Id(id);
    }

    @GetMapping("/group/{id}")
    public List<CourseGroup> getCourseGroupByGroupId(@PathVariable Long id){
        return courseGroupService.findAllByGroup_Id(id);
    }

    @GetMapping
    public List<CourseGroup> getAll(){
        return courseGroupService.getAll();
    }

    @GetMapping("/{id}")
    public CourseGroup getById(@PathVariable Long id){
        return courseGroupService.getById(id);
    }

    @DeleteMapping("/{id}")
    public CourseGroup deleteById(@PathVariable Long id){
        return courseGroupService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CourseGroup updateById(@RequestBody CourseGroupModel courseGroupModel,
                                  @PathVariable Long id){
        return courseGroupService.updateById(courseGroupModel,id);
    }
}

