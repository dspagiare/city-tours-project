package com.techelevator.model;

public class Landmark {
	
	private Long id;
	private String name;
	private String description;
	private String venueType;
	private String address;
	private String availableFromDay;
	private String availableToDay;
	private String availableFromHour;
	private String availableToHour;
	private String imgUrl;
	private Long numThumbsUp;
	private Long numThumbsdown;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVenueType() {
		return venueType;
	}
	public void setVenueType(String venueType) {
		this.venueType = venueType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAvailableFromDay() {
		return availableFromDay;
	}
	public void setAvailableFromDay(String availableFromDay) {
		this.availableFromDay = availableFromDay;
	}
	public String getAvailableToDay() {
		return availableToDay;
	}
	public void setAvailableToDay(String availableToDay) {
		this.availableToDay = availableToDay;
	}
	public String getAvailableFromHour() {
		return availableFromHour;
	}
	public void setAvailableFromHour(String availableFromHour) {
		this.availableFromHour = availableFromHour;
	}
	public String getAvailableToHour() {
		return availableToHour;
	}
	public void setAvailableToHour(String availableToHour) {
		this.availableToHour = availableToHour;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Long getNumThumbsUp() {
		return numThumbsUp;
	}
	public void setNumThumbsUp(Long numThumbsUp) {
		this.numThumbsUp = numThumbsUp;
	}
	public Long getNumThumbsdown() {
		return numThumbsdown;
	}
	public void setNumThumbsdown(Long numThumbsdown) {
		this.numThumbsdown = numThumbsdown;
	}
	
	

}
