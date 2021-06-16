package it.academy.project.projectonspring.repository;

import it.academy.project.projectonspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String login);
    List<User> findAllByStatus(Long status);
}
