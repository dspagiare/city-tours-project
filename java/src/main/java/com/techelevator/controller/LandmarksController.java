package com.techelevator.controller;
import java.security.Principal;

import java.util.List;

import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techelevator.dao.LandmarksDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Landmark;
import com.techelevator.model.LoginDTO;
import com.techelevator.model.RegisterUserDTO;
import com.techelevator.model.User;
import com.techelevator.model.UserAlreadyExistsException;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;


@RequestMapping("/landmarks")
@RestController
@CrossOrigin

public class LandmarksController {
//	private UserDAO userDAO;
	private LandmarksDAO landmarksDAO;
	
//	public LandmarksController(LandmarksDAO landmarksDAO, UserDAO userDAO) {
//		this.userDAO = userDAO;
//		this.landmarksDAO = landmarksDAO;
//	}
	
	public LandmarksController(LandmarksDAO landmarksDAO) {
		this.landmarksDAO = landmarksDAO;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Landmark> getLandmarks() {
		return landmarksDAO.findAll();
	}
	 /**
     * Finds the user by username and returns the id
     * @param principal the current authenticated user
     * @return Long user_id
     */
//    private Long getCurrentUserId(Principal principal) {
//        return userDAO.findByUsername(principal.getName()).getId();
//    }
}