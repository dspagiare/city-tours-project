package com.techelevator.dao;

import java.util.List;

<<<<<<< HEAD
import com.techelevator.model.Itineraries_Landmarks;
=======
import org.springframework.context.annotation.Configuration;


>>>>>>> master
import com.techelevator.model.Itinerary;
import com.techelevator.model.Itineraries_Landmarks;

@Configuration
public interface ItineraryDAO {

	Itinerary getItineraryById(Integer id, String userName);

	void deleteItinerary(Integer id, String userName);

	void updateItinerary(Itinerary itineraryBody, String userName);

	Itinerary createItinerary(Itinerary newItinerary, String userName);
	
	List<Itinerary> getAllItineraries(String userName);

	void deleteLandmarkFromItinerary(Integer id,Integer landId, String userName);

	void addLandmarkToItinerary(Integer id, Integer landId, String userName);

	
	

}
