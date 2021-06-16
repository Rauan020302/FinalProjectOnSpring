package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.CourseGroup;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.CourseGroupModel;

import java.util.List;

public interface CourseGroupService {
    List<CourseGroup> getAll();
    CourseGroup getById(Long id) throws ObjectsNotFoundException;
    CourseGroup deleteById(Long id) throws ObjectsNotFoundException;
    CourseGroup save(CourseGroupModel courseGroupModel) throws ObjectsNotFoundException;
    CourseGroup save(CourseGroup courseGroup);
    CourseGroup updateById(CourseGroupModel courseGroupModel,Long id) throws ObjectsNotFoundException;
    List<CourseGroup> findAllByGroup_Id(Long id);
    List<CourseGroup> findAllByCourse_Id(Long id);
}
