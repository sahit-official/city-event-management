package com.kumaran.city_event_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kumaran.city_event_management.exception.NotEnoughSeatsException;

@SpringBootApplication
public class CityEventManagementApplication {

	public static void main(String[] args) throws NotEnoughSeatsException {
		SpringApplication.run(CityEventManagementApplication.class, args);
	}

}
