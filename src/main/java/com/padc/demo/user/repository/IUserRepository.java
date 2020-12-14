package com.padc.demo.user.repository;

import com.padc.demo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * called by UserService. Creates the connection to the database
 * findByUsename is used by Spring Security, when JpaUserDetailsService
 * loads user from database by username
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
