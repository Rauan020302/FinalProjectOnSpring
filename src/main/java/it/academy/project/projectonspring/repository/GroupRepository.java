package it.academy.project.projectonspring.repository;

import it.academy.project.projectonspring.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    List<Group> findAllByKinderGarden_Id(Long id);
}
