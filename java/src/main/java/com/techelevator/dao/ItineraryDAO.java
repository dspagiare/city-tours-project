package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Itineraries_Landmarks;
import com.techelevator.model.Itinerary;
import com.techelevator.model.Itineraries_Landmarks;

public interface ItineraryDAO {

	Itinerary getItineraryById(Integer id, String userName);

	void deleteItinerary(Integer id, String userName);

	void updateItinerary(Itinerary itineraryBody, String userName);


	Itinerary createItinerary(Itinerary newItinerary, String userName);

	List<Itinerary> getAllItineraries(String userName);

	void deleteLandmarkFromItinerary(Integer id,Integer landId, String userName);

	void addLandmarkToItinerary(Integer id, Integer landId, String userName);

	
	

}
