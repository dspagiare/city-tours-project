package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Landmark;

public class LandmarkSqlDaoIntegrationTest extends DAOIntegrationTest {

    private LandmarksSqlDAO landmarksSqlDAO;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        landmarksSqlDAO = new LandmarksSqlDAO(jdbcTemplate);
    }
    
    @Test
    public void findAll() {
    	
    	List<Landmark> landmarks = new ArrayList<>();
    	
    	landmarks = landmarksSqlDAO.findAll();
    	
    	Assert.assertNotNull(landmarks);
    	
    	/*Working off of baseline dummy data in landmarks table. If time allows, we can
    	 * expand on this test case by creating test data with this test case.
    	 */
    	Assert.assertEquals(1, landmarks.size());
    	
    }

	
	
}
