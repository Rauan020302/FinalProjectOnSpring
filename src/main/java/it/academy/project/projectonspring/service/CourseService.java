package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getALlCourse();
    Course getCourseById(Long id);
    Course deleteCourseById(Long id);
    Course updateCourseById(Course course, Long id);
    Course saveCourse(Course course);
}
