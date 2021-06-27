package it.academy.project.projectonspring.repository;

import it.academy.project.projectonspring.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> {
    //List<Visit> findAllByGroup_Id(Long id);
    List<Visit> findAllByChild_Id(Long id);
    List<Visit> findAllByChild_IdAndDate_DayOfMonth(Long child_id, int date_dayOfMonth);
}
