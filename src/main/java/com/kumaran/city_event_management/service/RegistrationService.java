package com.kumaran.city_event_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kumaran.city_event_management.exception.NotEnoughSeatsException;
import com.kumaran.city_event_management.model.Event;
import com.kumaran.city_event_management.model.Registration;
import com.kumaran.city_event_management.model.User;
import com.kumaran.city_event_management.repository.EventRepository;
import com.kumaran.city_event_management.repository.RegistrationRepository;
import com.kumaran.city_event_management.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository rr;
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private EventRepository er;
	
	public List<Registration> getAllRegistrations() {
		return rr.findAll();
	}
	
	public Registration getById(Long id) {
		return rr.findById(id).get();
	}
	
	public void addRegistration(User user, Event event) throws NotEnoughSeatsException {
		Registration registration = new Registration(user, event);
		if(event.getAvailableSeats() > 0) {
			event.getRegistrations().add(registration);
			event.setAvailableSeats(event.getAvailableSeats() - 1);
			user.getRegistrations().add(registration);
			registration.setEvent(event);
			registration.setUser(user);
			rr.save(registration);
			ur.save(user);
			er.save(event);
		}
		else {
			throw new NotEnoughSeatsException("All seats for this event are already booked");
		}
	}
	
	public void deleteRegistration(Registration r) {
		Event event = r.getEvent();
		User user = r.getUser();
		event.getRegistrations().remove(r);
		event.setAvailableSeats(event.getAvailableSeats() + 1);
		user.getRegistrations().remove(r);
		rr.delete(r);
		ur.save(user);
		er.save(event);
	}
	
}
