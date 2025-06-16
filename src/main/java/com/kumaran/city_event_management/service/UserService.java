package com.kumaran.city_event_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kumaran.city_event_management.model.Event;
import com.kumaran.city_event_management.model.Registration;
import com.kumaran.city_event_management.model.User;
import com.kumaran.city_event_management.repository.RegistrationRepository;
import com.kumaran.city_event_management.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private RegistrationRepository rr;
	
	public User getUserById(Long id) {
		return ur.findById(id).get();
	}
	
	public void addUser(User user) {
		ur.save(user);
	}
	
	public void deleteUser(User user) {
		for(Registration r : user.getRegistrations()) {
			Event event = r.getEvent();
			event.setAvailableSeats(event.getAvailableSeats() + 1);
			event.getRegistrations().remove(r);
			rr.delete(r);
		}
		ur.delete(user);
	}
	
	public void updateUser(User userOld, User userNew) {
		if(userNew.getUserName() != null && !userNew.getUserName().isEmpty()) userOld.setUserName(userNew.getUserName());
		if(userNew.getEmail() != null && !userNew.getEmail().isEmpty()) userOld.setEmail(userNew.getEmail());
		if(userNew.getPassword() != null && !userNew.getPassword().isEmpty()) userOld.setPassword(userNew.getPassword());
		if(userNew.getRole() != null && !userNew.getRole().isEmpty()) userOld.setRole(userNew.getRole());
		
		ur.save(userOld);
	}
	
	public List<User> getUsersByRole(String role) {
		return ur.findAllByRole(role);
	}
	
	public List<User> getAllUsers() {
		return ur.findAll();
	}
	
}
