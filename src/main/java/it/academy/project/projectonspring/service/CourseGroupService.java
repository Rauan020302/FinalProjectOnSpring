package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.CourseGroup;
import it.academy.project.projectonspring.model.CourseGroupModel;

import java.util.List;

public interface CourseGroupService {
    List<CourseGroup> getAll();
    CourseGroup getById(Long id);
    CourseGroup deleteById(Long id);
    CourseGroup save(CourseGroupModel courseGroupModel);
    CourseGroup save(CourseGroup courseGroup);
    CourseGroup updateById(CourseGroupModel courseGroupModel,Long id);
    List<CourseGroup> findAllByGroup_Id(Long id);
    List<CourseGroup> findAllByCourse_Id(Long id);
}
