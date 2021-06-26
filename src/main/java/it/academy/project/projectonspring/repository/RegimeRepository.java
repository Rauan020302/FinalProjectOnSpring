package it.academy.project.projectonspring.repository;

import it.academy.project.projectonspring.entity.Regime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegimeRepository extends JpaRepository<Regime,Long> {
}
