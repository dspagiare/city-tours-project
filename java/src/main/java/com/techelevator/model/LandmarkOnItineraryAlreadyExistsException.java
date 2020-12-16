package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "Landmark On Itinerary Already Exists.")
public class LandmarkOnItineraryAlreadyExistsException extends RuntimeException {

}
