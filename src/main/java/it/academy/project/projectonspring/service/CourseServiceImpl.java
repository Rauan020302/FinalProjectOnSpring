package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Course;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getALlCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new ObjectsNotFoundException("not found course by id - " + id));
    }

    @Override
    public Course deleteCourseById(Long id){
        Course course = getCourseById(id);
        if (course != null){
            courseRepository.delete(course);
            return course;
        }
        return null;
    }

    @Override
    public Course updateCourseById(Course course, Long id){
        Course newCourse = getCourseById(id);
        newCourse.setName(course.getName());
        newCourse.setTimeEnd(course.getTimeEnd());
        newCourse.setTimeStart(course.getTimeStart());
        return saveCourse(newCourse);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
}

