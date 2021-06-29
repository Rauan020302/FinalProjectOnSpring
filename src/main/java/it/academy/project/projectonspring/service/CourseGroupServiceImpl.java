package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Course;
import it.academy.project.projectonspring.entity.CourseGroup;
import it.academy.project.projectonspring.entity.Group;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.CourseGroupModel;
import it.academy.project.projectonspring.repository.CourseGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseGroupServiceImpl implements CourseGroupService {
    @Autowired
    private CourseGroupRepository courseGroupRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private GroupService groupService;

    @Override
    public CourseGroup deleteById(Long id){
        CourseGroup courseGroup = getById(id);
        if (courseGroup != null){
            courseGroupRepository.delete(courseGroup);
            return courseGroup;
        }
        return null;
    }

    @Override
    public CourseGroup save(CourseGroupModel courseGroupModel){
        Group group = groupService.getGroupById(courseGroupModel.getGroupId());
        Course course = courseService.getCourseById(courseGroupModel.getCourseId());
        try {
            if (group == null || course == null) throw new ObjectsNotFoundException();

            CourseGroup courseGroup = CourseGroup.builder()
                    .group(group)
                    .course(course).build();
            return save(courseGroup);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found group or course");
        }
    }

    @Override
    public CourseGroup updateById(CourseGroupModel courseGroupModel, Long id){
        Course course = courseService.getCourseById(courseGroupModel.getCourseId());
        Group group = groupService.getGroupById(courseGroupModel.getGroupId());
        try {
            if (group == null || course == null) throw new ObjectsNotFoundException();

            CourseGroup courseGroup = getById(id);
            courseGroup.setCourse(course);
            courseGroup.setGroup(group);
            return save(courseGroup);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found courseGroup by id - " + id);
        }
    }
    @Override
    public CourseGroup save(CourseGroup courseGroup) {
        return courseGroupRepository.save(courseGroup);
    }

    @Override
    public List<CourseGroup> findAllByGroup_Id(Long id) {
        return courseGroupRepository.findAllByGroup_Id(id);
    }

    @Override
    public List<CourseGroup> findAllByCourse_Id(Long id) {
        return courseGroupRepository.findAllByCourse_Id(id);
    }

    @Override
    public List<CourseGroup> getAll() {
        return courseGroupRepository.findAll();
    }

    @Override
    public CourseGroup getById(Long id){
        return courseGroupRepository.findById(id)
                .orElseThrow(() -> new ObjectsNotFoundException("not found courseGroup by id - " + id));
    }
}
