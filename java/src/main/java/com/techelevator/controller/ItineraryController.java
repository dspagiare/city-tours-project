package com.techelevator.controller;

import java.math.BigDecimal;
import java.security.Principal;

import java.util.List;


import javax.validation.Valid;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.techelevator.dao.ItineraryDAO;

import com.techelevator.dao.UserDAO;

import com.techelevator.model.Itinerary;

import com.techelevator.model.LoginDTO;

import com.techelevator.model.RegisterUserDTO;

import com.techelevator.model.User;

import com.techelevator.model.UserAlreadyExistsException;

import com.techelevator.security.jwt.JWTFilter;

import com.techelevator.security.jwt.TokenProvider;



@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RestController
public class ItineraryController {

	
    private ItineraryDAO itineraryDAO;
    
	public ItineraryController(ItineraryDAO itineraryDAO) {
		this.itineraryDAO = itineraryDAO;
	}
	

	  @RequestMapping(value = "/itineraries", method = RequestMethod.GET)
	 public List<Itinerary> allItineraries(Principal principal) {
			return itineraryDAO.getAllItineraries(principal.getName()
					);

		}
	  
	  @RequestMapping(value = "/itineraries/id", method = RequestMethod.GET)
	  public Itinerary itineraryById(@PathVariable Integer  id, Principal principal) {
		  return itineraryDAO.getItineraryById(id,principal.getName() );
	  }
	  
	    
	  @RequestMapping(value = "/itineraries", method = RequestMethod.PUT)
	    public void updateItinerary(@RequestBody Itinerary myItinerary, Principal principal)  {
	    	
	    	itineraryDAO.updateItinerary(myItinerary, principal.getName()); 
	  }
	  @RequestMapping(value = "/itineraries", method = RequestMethod.POST)
	  public Itinerary addItinerary(@RequestBody Itinerary myItinerary,Principal principal ) {
		return  itineraryDAO.createItinerary(myItinerary, principal.getName());
	  }
	  
	  
	  @RequestMapping(value = "/itineraries/id", method = RequestMethod.DELETE)
	  public void deleteItinerary(@PathVariable Integer id, Principal principal ) {
		 itineraryDAO.deleteItinerary(id, principal.getName());
	  }
	  
	  @RequestMapping(value = "/itineraries/id/landmarks/landId", method = RequestMethod.DELETE)
	  public void deleteLandmrk(@PathVariable Integer id,@PathVariable Integer landId, Principal principal ) {
		 itineraryDAO.deleteLandmarkFromItinerary(id,landId, principal.getName());
	  }
	  
	  @RequestMapping(value = "/itineraries/id/landmarks/landId", method = RequestMethod.POST)
	  public void addLandmark(@PathVariable Integer id, @PathVariable Integer landId,  Principal principal ) {
		 itineraryDAO.addLandmarkToItinerary(id, landId, principal.getName());
	  }
	  
}
