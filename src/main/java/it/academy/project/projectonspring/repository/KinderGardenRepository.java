package it.academy.project.projectonspring.repository;

import it.academy.project.projectonspring.entity.KinderGarden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KinderGardenRepository extends JpaRepository<KinderGarden,Long> {
}
