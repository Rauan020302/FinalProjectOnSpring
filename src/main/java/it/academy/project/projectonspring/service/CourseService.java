package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Course;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;

import java.util.List;

public interface CourseService {
    List<Course> getALlCourse();
    Course getCourseById(Long id);
    Course deleteCourseById(Long id) throws ObjectsNotFoundException;
    Course updateCourseById(Course course, Long id) throws ObjectsNotFoundException;
    Course saveCourse(Course course);
}
