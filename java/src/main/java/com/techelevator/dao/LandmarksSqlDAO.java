package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.techelevator.model.Landmark;
import com.techelevator.model.User;

@Component
@Service
public class LandmarksSqlDAO implements LandmarksDAO {
	
	private JdbcTemplate jdbcTemplate;

    public LandmarksSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	@Override
	public List<Landmark> findAll() {
		List<Landmark> landmarks = new ArrayList<>();
		String sql = "SELECT * FROM landmarks";
		
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        
        while(results.next()) {
            Landmark landmark = mapRowToLandmark(results);
            landmarks.add(landmark);
        }

        return landmarks;

	}
	
	@Override
	public List<Landmark> getLandmarksForItinerary(Integer itinerary_id){
		List<Landmark> landmarks = new ArrayList<>();
		String sql = "SELECT l.* FROM itineraries_landmarks il INNER JOIN landmarks l ON l.landmark_id = il.landmark_id WHERE il.itinerary_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itinerary_id);
		
		while(results.next()) {
			Landmark landmark = mapRowToLandmark(results);
			landmarks.add(landmark);
		}
		return landmarks;
	}
	
	@Override
	public Landmark getLandmarkDetailsById(Integer landmark_id){
		Landmark myLandmark = null;
		String sql = "SELECT landmark_name,description,available_from_day, available_to_day,available_from_hour, available_to_hour "+
		"FROM landmarks  WHERE landmark_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, landmark_id);
		
		while(results.next()) {
			myLandmark = mapRowToLandmark(results);
			
		}
		return myLandmark;
	}
	public void updateLandmarkRating(Long id, Landmark landmark) {
		
//		String sql_get = "SELECT * FROM landmarks WHERE landmark_id = ?";
//		
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sql_get, id);
//		
//		if (landmark.getNumThumbsUp() + 1 > results.getLong("num_thumbs_up")) {
//			
//			String sql = "UPDATE landmarks SET num_thumbs_up = ? WHERE landmark_id = ?";
//			
//			jdbcTemplate.update(sql, landmark.getNumThumbsUp()+1, id);
//			
//		} else if (landmark.getNumThumbsDown() + 1 > results.getLong("num_thumbs_down") && results.getLong("num_thumbs_down") > 0) {
//			
//			String sql = "UPDATE landmarks SET num_thumbs_down = ? WHERE landmark_id = ?";
//			
//			jdbcTemplate.update(sql, landmark.getNumThumbsDown()+1, id);
//		}
		
		String sql = "UPDATE landmarks SET num_thumbs_up = ? WHERE landmark_id = ?";
		
		jdbcTemplate.update(sql, landmark.getNumThumbsUp()+1, id);
		
		//Need to take care of thumbs down

		
	}

	
	private Landmark mapRowToLandmark(SqlRowSet rs) {
        Landmark landmark = new Landmark();
        landmark.setId(rs.getLong("landmark_id"));
        landmark.setName(rs.getString("landmark_name"));
        landmark.setDescription(rs.getString("description"));
        landmark.setVenueType(rs.getString("venue_type"));
        landmark.setAddress(rs.getString("address"));
        landmark.setAvailableFromDay(rs.getString("available_from_day"));
        landmark.setAvailableToDay(rs.getString("available_to_day"));
        landmark.setAvailableFromHour(rs.getString("available_from_hour"));
        landmark.setAvailableToHour(rs.getString("available_to_hour"));
        landmark.setImgUrl(rs.getString("landmark_img_url"));
        landmark.setNumThumbsUp(rs.getLong("num_thumbs_up"));
        landmark.setNumThumbsDown(rs.getLong("num_thumbs_down"));
     
        return landmark;
    }

}
