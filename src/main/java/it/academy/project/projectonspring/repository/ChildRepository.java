package it.academy.project.projectonspring.repository;

import it.academy.project.projectonspring.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child,Long> {
    List<Child> findAllByGroup_Id(Long id);
    List<Child> findAllByBirthDayAfter(LocalDate date);
    List<Child> findAllByGroupKinderGarden_Id(Long id);
}
