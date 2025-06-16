package com.kumaran.city_event_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kumaran.city_event_management.model.Event;
import com.kumaran.city_event_management.repository.EventRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EventService {
	
	@Autowired
	private EventRepository er;
	
	public Event getEventById(Long id) {
		return er.findById(id).get();
	}
	
	public void addEvent(Event event) {
		er.save(event);
	}
	
	public List<Event> getAllEvents() {
		return er.findAll();
	}
	
}
