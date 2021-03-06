package it.academy.project.projectonspring.controller;

import it.academy.project.projectonspring.entity.Course;
import it.academy.project.projectonspring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public Course saveCourse(@RequestBody Course course){
        return courseService.saveCourse(course);
    }
    @GetMapping
    public List<Course> getAllCourse(){
        return courseService.getAllCourse();
    }
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }
    @PutMapping("/{id}")
    public Course updateCourseById(@RequestBody Course course,@PathVariable Long id){
        return courseService.updateCourseById(course,id);
    }
    @DeleteMapping("/{id}")
    public Course deleteCourseById(@PathVariable Long id){
        return courseService.deleteCourseById(id);
    }
}
