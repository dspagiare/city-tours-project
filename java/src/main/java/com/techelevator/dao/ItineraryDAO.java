package com.techelevator.dao;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.techelevator.model.Itineraries_Landmarks;
import com.techelevator.model.Itinerary;
import com.techelevator.model.Itineraries_Landmarks;

@Configuration
public interface ItineraryDAO {

	Itinerary getItineraryById(Integer id, String userName);

	void deleteItinerary(Integer id, String userName);

	


	Itinerary createItinerary(Itinerary newItinerary, String userName);

	List<Itinerary> getAllItineraries(String userName);

	void deleteLandmarkFromItinerary(Integer id,Integer landId, String userName);

	

	void addLandmarkToItinerary(String name,  List<Integer> landId, String userName);

	void updateItinerary(Integer id, Itinerary itineraryBody, String userName);

	
	

}
