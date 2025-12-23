package org.gdg.techsprint.placement.readiness.backend.repository;

import org.gdg.techsprint.placement.readiness.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
