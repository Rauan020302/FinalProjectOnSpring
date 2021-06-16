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
    public CourseGroup getById(Long id) throws ObjectsNotFoundException {
        return courseGroupRepository.findById(id)
                .orElseThrow(ObjectsNotFoundException::new);
    }

    @Override
    public CourseGroup deleteById(Long id) throws ObjectsNotFoundException {
        CourseGroup courseGroup = getById(id);
        if (courseGroup != null){
            courseGroupRepository.delete(courseGroup);
            return courseGroup;
        }
        throw new ObjectsNotFoundException();
    }

    @Override
    public CourseGroup save(CourseGroupModel courseGroupModel) throws ObjectsNotFoundException {
        Group group = groupService.getGroupById(courseGroupModel.getGroupId());
        Course course = courseService.getCourseById(courseGroupModel.getCourseId());
        if (group == null || course == null)throw new ObjectsNotFoundException();

        CourseGroup courseGroup = CourseGroup.builder()
                .group(group)
                .course(course).build();
        return save(courseGroup);
    }

    @Override
    public CourseGroup save(CourseGroup courseGroup) {
        return courseGroupRepository.save(courseGroup);
    }

    @Override
    public CourseGroup updateById(CourseGroupModel courseGroupModel, Long id) throws ObjectsNotFoundException {
        Course course = courseService.getCourseById(courseGroupModel.getCourseId());
        Group group = groupService.getGroupById(courseGroupModel.getGroupId());
        CourseGroup courseGroup = getById(id);
        if (group == null || course == null || courseGroup == null)throw new ObjectsNotFoundException();

        courseGroup.setCourse(course);
        courseGroup.setGroup(group);
        return save(courseGroup);
    }
}
