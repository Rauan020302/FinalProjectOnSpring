package it.academy.project.projectonspring.repository;

import it.academy.project.projectonspring.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar,Long> {
    List<Calendar> findAllByGroup_Id(Long id);
    List<Calendar> findAllByChild_Id(Long id);
}
