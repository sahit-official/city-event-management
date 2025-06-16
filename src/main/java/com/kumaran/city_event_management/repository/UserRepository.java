package com.kumaran.city_event_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kumaran.city_event_management.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByRole(String role);

}
