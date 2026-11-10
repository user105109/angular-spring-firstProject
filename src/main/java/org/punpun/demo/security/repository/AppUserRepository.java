package org.punpun.demo.security.repository;

import org.punpun.demo.security.entities.AppUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
    @EntityGraph(attributePaths = {"roles"})
    Optional<AppUser> findWithRolesByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
