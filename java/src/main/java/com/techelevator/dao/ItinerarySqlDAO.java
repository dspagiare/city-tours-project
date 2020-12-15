package com.techelevator.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Itineraries_Landmarks;
import com.techelevator.model.Itinerary;
import com.techelevator.dao.ItineraryDAO;
//import com.techelevator.model.Itinerary_Landmarks;

@Component

public class ItinerarySqlDAO implements ItineraryDAO {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public ItinerarySqlDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {

		this.jdbcTemplate = jdbcTemplate;

		this.dataSource = dataSource;
	}

	@Override
	public Itinerary getItineraryById(Integer id, String userName) {
		Itinerary myItinerary = null;
		List<Itinerary> itineraryList = getAllItineraries(userName);

		for (Itinerary value : itineraryList) {
			Integer thisID = value.getItinerary_id();

			if (id == thisID) {
				String sql = "SELECT * FROM itineraries WHERE itinerary_id = ?";

				SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
				while (results.next()) {
					myItinerary = mapRowToItinerary(results);
				}
			}
		}
		return myItinerary;
	}

	@Override
	public List<Itinerary> getAllItineraries(String userName) {
		List<Itinerary> itineraryList = new ArrayList<>();
		String sql = "SELECT * FROM itineraries "+
				"INNER JOIN users ON users.user_id = itineraries.user_id WHERE users.username = ? ";

		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userName);
		while (result.next()) {
			Itinerary myItinerary = mapRowToItinerary(result);
			itineraryList.add(myItinerary);
		}
		return itineraryList;
	}

	@Override
	public void updateItinerary(Integer id, Itinerary itineraryBody, String userName) {
		List<Itinerary> itineraryList = getAllItineraries(userName);

		for (Itinerary value : itineraryList) {
			Integer thisID = value.getItinerary_id();

			if (itineraryBody.getItinerary_id() == thisID) {
				String sql = "UPDATE itineraries SET itinerary_name=?, itinerary_date = ? WHERE itinerary_id=?";

				jdbcTemplate.update(sql, itineraryBody.getName(), itineraryBody.getItinerary_date(),
						id);
			}
		}
	}

	@Override
	public Itinerary createItinerary(Itinerary newItinerary, String userName) {
		String sql = "INSERT INTO itineraries (user_id, itinerary_id, itinerary_name, itinerary_date) VALUES((SELECT user_id FROM users WHERE username= ?),?, ?, ?)";

		newItinerary.setItinerary_id(getNextItineraryId());

		jdbcTemplate.update(sql, userName, newItinerary.getItinerary_id(), newItinerary.getName(),
				newItinerary.getItinerary_date());

		return newItinerary;
	}

	@Override
	public void deleteItinerary(Integer id, String userName) {
		List<Itinerary> itineraryList = getAllItineraries(userName);

		for (Itinerary value : itineraryList) {
			Integer thisID = value.getItinerary_id();
			if (id == thisID) {
				String sql = "DELETE FROM  itineraries  WHERE itinerary_id=?";
				jdbcTemplate.update(sql, id);
			}
		}

	}


	public void deleteLandmarkFromItinerary(Integer id, Integer landId, String userName) {

//		List<Itinerary> itineraryList = getAllItineraries(userName);
//
//		for (Itinerary value : itineraryList) {
//			Integer thisID = value.getItinerary_id();
//			if (id == thisID) {
				String sql = "DELETE FROM itineraries_landmarks WHERE itinerary_id=? AND landmark_id=?";

				jdbcTemplate.update(sql, id, landId);
//			}
//		}

	}
	
	@Override
	public void addLandmarkToItinerary(Integer id, List<Integer> landId, String userName) {

//		List<Itinerary> itineraryList = getAllItineraries(userName);
//
//		for (Itinerary value : itineraryList) {
//			Integer thisID = value.getItinerary_id();
//			if (id == thisID) {
				
		for(Integer valueInteger : landId) {
			
			String sql = "INSERT INTO itineraries_landmarks (itinerary_id, landmark_id) VALUES ( ?,?)";
			
			jdbcTemplate.update(sql, id, valueInteger );

		}
				
				



	}
	


	private Itinerary mapRowToItinerary(SqlRowSet results) {
		Itinerary myItinerary = new Itinerary();
		myItinerary.setUser_id(results.getInt("user_id"));
		myItinerary.setItinerary_id(results.getInt("itinerary_id"));
		myItinerary.setName(results.getString("itinerary_name"));
		myItinerary.setItinerary_date(results.getDate("itinerary_date").toLocalDate());
		return myItinerary;
	}

	private Integer getNextItineraryId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_itinerary_id')");
		if (nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new itinerary");
		}
	}

}
