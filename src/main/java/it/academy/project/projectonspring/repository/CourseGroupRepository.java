package it.academy.project.projectonspring.repository;

import it.academy.project.projectonspring.entity.CourseGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseGroupRepository extends JpaRepository<CourseGroup,Long> {
    List<CourseGroup> findAllByGroup_Id(Long id);
    List<CourseGroup> findAllByCourse_Id(Long id);
}
