BEGIN TRANSACTION;

DROP TABLE IF EXISTS itineraries_landmarks;
DROP TABLE IF EXISTS users_itineraries;
DROP TABLE IF EXISTS itineraries;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS landmarks;

DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_itinerary_id;
DROP SEQUENCE IF EXISTS seq_landmark_id;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE SEQUENCE seq_itinerary_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE itineraries (
        itinerary_id int DEFAULT nextval('seq_itinerary_id'::regclass) NOT NULL,
        user_id int NOT NULL,
        itinerary_name varchar(200) NOT NULL,
        itinerary_date DATE,
        staring_point varchar(200),
        created_date DATE DEFAULT CURRENT_DATE,
        CONSTRAINT pk_itinerary_id PRIMARY KEY (itinerary_id),
        CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE SEQUENCE seq_landmark_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE landmarks (
        landmark_id int DEFAULT nextval('seq_landmark_id'::regclass) NOT NULL,
        landmark_name varchar(200) NOT NULL,
        description text NOT NULL,
        venue_type varchar(50) NOT NULL,
        address text NOT NULL,
        land_lat float(50) NOT NULL,
        land_lon float(50) NOT NULL,
        available_from_day char(10) NOT NULL,
        available_to_day char(10) NOT NULL,
        available_from_hour time NOT NULL,
        available_to_hour time NOT NULL,
        landmark_img_url varchar(2000),
        num_thumbs_up int,
        num_thumbs_down int,
        created_date DATE DEFAULT CURRENT_DATE,
        
        CONSTRAINT pk_landmark_id PRIMARY KEY (landmark_id),
        
        CONSTRAINT ck_available_from_day CHECK (available_from_day IN ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')),
        CONSTRAINT ck_available_to_day CHECK (available_to_day IN ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'))

);


CREATE TABLE itineraries_landmarks (
        itinerary_id int,
        landmark_id int,
        
        CONSTRAINT pk_itinerary_id_landmark_id_ PRIMARY KEY (itinerary_id, landmark_id),
        --CONSTRAINT fk_itinerary_id FOREIGN KEY (itinerary_id) REFERENCES itineraries(itinerary_id),
        CONSTRAINT fk_landmark_id FOREIGN KEY (landmark_id) REFERENCES landmarks(landmark_id)
);

--Dummy data
INSERT INTO itineraries (user_id, itinerary_name, itinerary_date, staring_point)
VALUES ('1', 'First Itenarary Test', '2020-12-15', 'TBD');

INSERT INTO landmarks (landmark_name, description, venue_type, address, land_lat, land_lon, available_from_day, available_to_day, 
available_from_hour, available_to_hour, landmark_img_url, num_thumbs_up, num_thumbs_down)
VALUES ('Cathedral of Learning', 'The Cathedral of Learning is a building that serves as the centerpiece of the University of Pittsburghs main campus in the Oakland neighborhood ', 'National Historic Landmark', '4200 Fifth Ave, Pittsburgh, PA 15260', '40.4443', '-79.9532', 'Monday', 'Friday', '9:00', '5:00', 'https://media1.trover.com/T/5cecc05d03893a7f670017b6/fixedw.jpg', '5', '10'),
       ('Canton Avenue', 'Canton Avenue, in Pittsburgh, Pennsylvanias Beechview neighborhood, is the steepest officially recorded public street in the United States. Canton Avenue is 630 ft long and is claimed to include a 37 percent grade 21 ft feet long', 'City Feature', 'Canton Ave, Pittsburgh, PA 15216','40.409732', '-80.0322831', 'Monday', 'Friday', '9:00', '5:00', 'https://upload.wikimedia.org/wikipedia/commons/3/34/CantonAve_Top.jpg', '10', '0'),
       ('Frick Park', 'Frick Park is the largest municipal park in Pittsburgh, Pennsylvania, covering 644 acres. It is one of Pittsburghs four historic large parks. The park began when Henry Clay Frick, upon his death in 1919, bequeathed 151 acres south of Clayton, his Point Breeze mansion.', 'Park', '1981 Beechwood Blvd, Pittsburgh, PA 15217', '40.4361', '-79.9089', 'Monday', 'Friday', '9:00', '5:00', 'https://www.discovertheburgh.com/wp-content/uploads/2019/04/DSC00182-600x338.jpg', '5', '0'),
       ('Carnegie Museum of Natural History', 'Carnegie Museums of Pittsburgh are four museums that are operated by the Carnegie Institute headquartered in the Carnegie Institute complex in the Oakland neighborhood of Pittsburgh', 'Museum', '4400 Forbes Ave, Pittsburgh, PA 15213', '40.44397' ,'-79.95014', 'Monday', 'Friday', '9:00', '5:00', 'https://www.visitpittsburgh.com/imager/s3_amazonaws_com/visit-pittsburgh/CMS/CMNH-Dinosaur-Header_49338b2ce992ef730f3e2445db4f50c0.jpg', '5', '0'),
       ('Carrie Furnace', 'Carrie Furnace is a former blast furnace located along the Monongahela River in the Pittsburgh area industrial town of Swissvale, Pennsylvania, and it had formed a part of the Homestead Steel Works. The Carrie Furnaces were built in 1884 and they operated until 1982', 'National Historic Landmark', '801 Carrie Furnace Blvd, Rankin, PA 15104','40.4139', '-79.8896', 'Monday', 'Friday', '9:00', '5:00', 'https://uncoveringpa.com/wp-content/uploads/2015/10/Carrie-Furnace-IMG_58532_HDR.jpg', '5', '0'),
       ('Phipps Conservatory and Botanical Gardens', 'A green oasis in the middle of Pittsburgh’s vibrant Oakland neighborhood, Phipps has provided a world-class garden experience to its visitors since 1893. Visit to discover breathtaking seasonal flower shows and special exhibits', 'National Historic Landmark', '1 Schenley Drive, Pittsburgh, PA 15213-3830', '40.438948','-79.947705', 'Monday', 'Friday', '9:00', '5:00', 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Phipps_Conservatory_winter_2015_welcome_center.jpg/1024px-Phipps_Conservatory_winter_2015_welcome_center.jpg', '5', '0'),
       ('Mount Washington', 'Charming Mount Washington is known for its panoramic city views, from spots including Grandview Avenue’s Restaurant Row, home to upscale seafood and Italian restaurants.', 'City Feature', '160 Grandview Ave, Pittsburgh, PA 15211','40.4277','-80.0116', 'Monday', 'Friday', '9:00', '5:00', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/2008-05-24_Pittsburgh_040_Duquesne_Incline_%282669552496%29.jpg/1024px-2008-05-24_Pittsburgh_040_Duquesne_Incline_%282669552496%29.jpg', '5', '0'),
       ('Carnegie Science Center', 'On the north bank of the Allegheny River is the huge Carnegie Science Center, which offers more than 250 hands-on exhibits. While visitors are exploring, they will learn about the ways that science and technology influence every aspect of our lives, from energy use, food production, and industrial processing to space exploration', 'Museum', 'One Allegheny Avenue, Pittsburgh, Pennsylvania','40.445614','-80.018181', 'Monday', 'Friday', '9:00', '5:00', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Carnegie_Science_Center.jpg/1024px-Carnegie_Science_Center.jpg', '5', '0'),
       ('Duquesne Incline', 'Opened in 1877, this landmark incline railway offers sweeping views of the city & a history museum.', 'National Historic Landmark', '1197 W Carson St, Pittsburgh, PA 15219','40.4399','-80.0176', 'Monday', 'Friday', '9:00', '5:00', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Downtown_Pittsburgh_from_Duquesne_Incline_in_the_morning.jpg/1024px-Downtown_Pittsburgh_from_Duquesne_Incline_in_the_morning.jpg', '5', '0'),
       ('Schenley Park', 'Schenley Park is a large municipal park located in Pittsburgh, Pennsylvania, between the neighborhoods of Oakland, Greenfield, and Squirrel Hill. It is also listed on the National Register of Historic Places as a historic district. In 2011, the park was named one of Americas Coolest City Parks ', 'Park', '1 Overlook Dr, Pittsburgh, PA 15217','40.4348','-79.9425', 'Monday', 'Friday', '9:00', '5:00', 'https://upload.wikimedia.org/wikipedia/commons/c/c8/SchenleyPark1.JPG', '5', '0'),
       ('Kennywood', 'Kennywood is an amusement park located in West Mifflin, Pennsylvania just southeast of Pittsburgh. The park first opened on May 30, 1899, as a trolley park attraction at the end of the Mellon familys Monongahela Street Railway.', 'Amusement Park', '4800 Kennywood Blvd, West Mifflin, PA 15122','40.3876','-79.8640', 'Monday', 'Friday', '9:00', '5:00', 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Kennywood_Thunderbolt_DSCN2744.JPG/1024px-Kennywood_Thunderbolt_DSCN2744.JPG', '5', '0'),
       ('The Andy Warhol Museum', 'The Andy Warhol Museum is located on the North Shore of Pittsburgh, Pennsylvania, in the United States. It is the largest museum in North America dedicated to a single artist. The museum holds an extensive permanent collection of art and archives from the Pittsburgh-born pop art icon Andy Warhol.', 'Museum', '117 Sandusky St, Pittsburgh, PA 15212','40.4484','-80.0025', 'Monday', 'Friday', '9:00', '5:00', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/The_Andy_Warhol_Museum.jpg/1024px-The_Andy_Warhol_Museum.jpg', '5', '0'),
       ('Bicycle Heaven', 'Bicycle Heaven is the largest transportation museum in the world dedicated to bicycles. The museum was founded by Craig Morrow and comprises his personal collection gathered over decades. ', 'Museum', '1800 Preble & Columbus Ave, Pittsburgh, PA 15233','40.45635','-80.03220', 'Monday', 'Friday', '9:00', '5:00', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/Pee-wee_Herman%27s_Bicycle.jpg/1024px-Pee-wee_Herman%27s_Bicycle.jpg', '5', '0'),
       ('Strip District', 'What was once a primarily industrial neighborhood is now packed with restaurants, international markets, museums and shops. The Strip District, which runs alongside the Allegheny River in Pittsburgh, measures only half a square mile, but it has plenty of attractions and eateries to fill up your day (and your stomach).', 'Historic Neighborhood', '2101 Smallman St, Pittsburgh, PA 15222','40.4534','-79.9807', 'Monday', 'Friday', '9:00', '5:00', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c0/Strip_District%2C_Pittsburgh.jpg/1280px-Strip_District%2C_Pittsburgh.jpg', '5', '0'),
       ('Three Rivers Heritage Trail', 'An asphalt link between some of Pittsburghs most notable spots, the Three Rivers Heritage Trail lines the banks of the Allegheny, Monongahela and Ohio waterways. The 24-mile-long path connects the downtown area to the surrounding neighborhoods and makes it easy for people to travel across the city without getting in a car or on a bus.', 'Park', 'Three Rivers Heritage Trail, Pittsburgh, PA 15203','40.4312633','-79.9754564', 'Monday', 'Friday', '9:00', '5:00', 'https://www.railstotrails.org/media/472819/the-trail-borders-pittsburghs-point-state-park-photo-by-ehren-zaun-reduced.jpg', '5', '0');

INSERT INTO itineraries_landmarks (itinerary_id, landmark_id)
VALUES ('1', '1');
        
COMMIT TRANSACTION;
