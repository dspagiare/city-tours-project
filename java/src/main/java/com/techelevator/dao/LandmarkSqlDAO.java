package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Landmark;
import com.techelevator.model.User;

@Service
public class LandmarkSqlDAO implements LandmarkDAO {
	
	private JdbcTemplate jdbcTemplate;

    public LandmarkSqlDAO(JdbcTemplate jdbcTemplate) {
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

		return null;
	}
	
	private Landmark mapRowToLandmark(SqlRowSet rs) {
        Landmark landmark = new Landmark();
        landmark.setId(rs.getLong("landmark_id"));
        landmark.setName(rs.getString("landmark_name"));
        landmark.setDescription(rs.getString("description"));
        user.setAuthorities(rs.getString("role"));
        user.setActivated(true);
        return user;
    }

}
