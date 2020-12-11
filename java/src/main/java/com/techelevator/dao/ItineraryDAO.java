package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Itinerary;
//import com.techelevator.model.Itinerary_Landmarks;

public interface ItineraryDAO {

	Itinerary getItineraryById(Integer id, String userName);

	void deleteItinerary(Integer id);

	void updateItinerary(Itinerary itineraryBody, String userName);


	Itinerary createItinerary(Itinerary newItinerary, String userName);

	List<Itinerary> getAllItineraries(String userName);

	

	//void addLandmarkToItinerary(Itinerary_Landmarks body);
	

}
