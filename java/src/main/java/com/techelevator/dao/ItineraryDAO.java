package com.techelevator.dao;

import java.util.List;
<<<<<<< HEAD


import com.techelevator.model.Itineraries_Landmarks;

import org.springframework.context.annotation.Configuration;



=======
import com.techelevator.model.Itineraries_Landmarks;
import org.springframework.context.annotation.Configuration;
>>>>>>> b0c885f7de031f1c1ab170765c53a9c65d776c2a
import com.techelevator.model.Itinerary;


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
