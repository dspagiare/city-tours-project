package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Landmark;

public interface LandmarksDAO {
	
	List<Landmark> findAll();

	List<Landmark> getLandmarksForItinerary(Integer itinerary_id);
	
	void updateLandmarkRating(Long id, Landmark landmark);
	
}
