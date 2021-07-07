package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Course;
import it.academy.project.projectonspring.entity.Regime;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RegimeService regimeService;

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
    public Course updateCourseById(Course course, Long id) {
        Course newCourse = getCourseById(id);
        try {
            if (newCourse == null) throw new ObjectsNotFoundException();

            newCourse.setName(course.getName());
            newCourse.setTimeEnd(course.getTimeEnd());
            newCourse.setTimeStart(course.getTimeStart());
            newCourse.setWeek(course.getWeek());

            return saveCourse(newCourse);

        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found course by id - " + id);
        }
    }

    @Override
    public Course saveCourse(Course course) {
        List<Regime> regimes = regimeService.getAllRegime();
        try{
            for (Regime regime:regimes) {
                if (regime.getTimeStart().toString().equals(course.getTimeStart().toString()) || regime.getTimeEnd().toString().equals(course.getTimeEnd().toString())){
                    throw new ObjectsNotFoundException();
                }

            }
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("the time of the regime and the course conflict with each other ");
        }
        return courseRepository.save(course);
    }
    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new ObjectsNotFoundException("not found course by id - " + id));
    }
}

