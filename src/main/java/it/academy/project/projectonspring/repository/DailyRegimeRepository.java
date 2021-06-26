package it.academy.project.projectonspring.repository;

import it.academy.project.projectonspring.entity.DailyRegime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyRegimeRepository extends JpaRepository<DailyRegime, Long> {
    List<DailyRegime> findAllByGroup_Id(Long id);
    List<DailyRegime> findAllByRegime_Id(Long id);
    List<DailyRegime> findAllByGroup_IdOrderByRegimeAsc(Long group_id);
}
