package com.kumaran.city_event_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kumaran.city_event_management.exception.NotEnoughSeatsException;
import com.kumaran.city_event_management.model.Event;
import com.kumaran.city_event_management.model.Registration;
import com.kumaran.city_event_management.model.User;
import com.kumaran.city_event_management.service.EventService;
import com.kumaran.city_event_management.service.RegistrationService;
import com.kumaran.city_event_management.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService us;
	
	@Autowired
	EventService es;
	
	@Autowired
	RegistrationService rs;
	
	@GetMapping
	public String showHomePage(Model model) {
		List<User> users = us.getAllUsers();
		List<Event> events = es.getAllEvents();
		List<Registration> registrations = rs.getAllRegistrations();
		model.addAttribute("users", users);
		model.addAttribute("events", events);
		model.addAttribute("registrations", registrations);
		return "home";
	}
	
	@GetMapping("/addUserForm")
	public String showAddUserForm(Model model) {
		model.addAttribute("user", new User());
		return "addUserForm";
	}
	
	@PostMapping("/processAddUserForm")
	public String proccessUserForm(@ModelAttribute("user") User user) {
		us.addUser(user);
		return "redirect:/";
	}
	
	@GetMapping("/deleteUserForm")
	public String showDeleteUserForm() {
		return "deleteUserForm";
	}
	
	@PostMapping("/processDeleteUserForm")
	public String processDeleteUserForm(@RequestParam("id") Long id) {
		User user = us.getUserById(id);
		us.deleteUser(user);
		return "redirect:/";
	}
	
	@GetMapping("/updateUserForm")
	public String showUpdateUserForm(Model model) {
		model.addAttribute("newUser", new User());
		return "updateUserForm";
	}
	
	@PostMapping("/processUpdateUserForm")
	public String processUpdateUserForm(@ModelAttribute("newUser") User newUser, @RequestParam("id") Long id) {
		User oldUser = us.getUserById(id);
		us.updateUser(oldUser, newUser);
		return "redirect:/";
	}
	
	@GetMapping("/addEventForm")
	public String showAddEventForm(Model model) {
		model.addAttribute("event", new Event());
		return "addEventForm";
	}
	
	@PostMapping("/processAddEventForm")
	public String processAddEventForm(@ModelAttribute("event") Event event) {
		es.addEvent(event);
		return "redirect:/";
	}
	
	@GetMapping("/addRegistrationForm")
	public String showAddRegistrationForm() {
		return "addRegistrationForm";
	}
	
	@PostMapping("/processAddRegistrationForm")
	public String processAddRegistrationForm(@RequestParam("userId") Long uid, @RequestParam("eventId") Long eid) throws NotEnoughSeatsException {
		User u = us.getUserById(uid);
		Event e = es.getEventById(eid);
		try {
			rs.addRegistration(u, e);
			return "redirect:/";
		}
		catch(NotEnoughSeatsException ex) {
			return "noSeats";
		}
	}
	
	@GetMapping("/deleteRegistrationForm")
	public String showDeleteRegistrationForm() {
		return "deleteRegistrationForm";
	}
	
	@PostMapping("/processDeleteRegistrationForm")
	public String processDeleteRegistrationForm(@RequestParam("id") Long id) {
		Registration r = rs.getById(id);
		rs.deleteRegistration(r);
		return "redirect:/";
	}
	
	@GetMapping("/user-by-role")
	public String showUserByRole() {
		return "getUserByRoleForm";
	}
	
	@PostMapping("/processUserByRole")
	public String processUserByRole(@RequestParam("role") String role, Model model) {
		List<User> users = us.getUsersByRole(role);
		model.addAttribute("users", users);
		return "showUsersByRole";
	}
	
}
