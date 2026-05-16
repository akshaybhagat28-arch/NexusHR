package com.nexusHr.authService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexusHr.authService.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
