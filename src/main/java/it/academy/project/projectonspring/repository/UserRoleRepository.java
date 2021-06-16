package it.academy.project.projectonspring.repository;

import it.academy.project.projectonspring.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    UserRole findByUser_Id(Long id);
    List<UserRole> findAllByUser_Id(Long id);
}
