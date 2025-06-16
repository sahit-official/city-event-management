package com.kumaran.city_event_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kumaran.city_event_management.model.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

}
