package com.techelevator.model;

import java.time.LocalDate;


public class Itinerary {

	private Integer  itinerary_id;
	private Integer user_id;
	private String name;
	private LocalDate itinerary_date;
	
	
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getItinerary_id() {
		return itinerary_id;
	}
	public void setItinerary_id(Integer  itinerary_id) {
		this.itinerary_id = itinerary_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getItinerary_date() {
		return itinerary_date;
	}
	public void setItinerary_date(LocalDate itinerary_date) {
		this.itinerary_date = itinerary_date;
	}
	
	
	
}
