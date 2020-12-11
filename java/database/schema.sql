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
        itinerary_name varchar(200) NOT NULL,
        itinerary_date DATE,
        staring_point varchar(200),
        created_date DATE DEFAULT CURRENT_DATE,
        CONSTRAINT pk_itinerary_id PRIMARY KEY (itinerary_id)

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
        available_from_day char(10) NOT NULL,
        available_to_day char(10) NOT NULL,
        available_from_hour time NOT NULL,
        available_to_hour time NOT NULL,
        landmark_img_url varchar(2000),
        num_thumbs_up int,
        num_thumbs_down int,
        created_date DATE DEFAULT CURRENT_DATE,
        
        CONSTRAINT pk_landmark_id PRIMARY KEY (landmark_id),
        
        -- CONSTRAINT ck_venue_type CHECK (venue_type IN ('Park', 'Restaurant', 'Historical')),
        
        CONSTRAINT ck_available_from_day CHECK (available_from_day IN ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')),
        CONSTRAINT ck_available_to_day CHECK (available_to_day IN ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'))

);

CREATE TABLE users_itineraries (
        user_id int,
        itinerary_id int,
        
        CONSTRAINT pk_user_id_itinerary_id PRIMARY KEY (user_id, itinerary_id),
        CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
        CONSTRAINT fk_itinerary_id FOREIGN KEY (itinerary_id) REFERENCES itineraries(itinerary_id)
);

CREATE TABLE itineraries_landmarks (
        itinerary_id int,
        landmark_id int,
        
        CONSTRAINT pk_itinerary_id_landmark_id_ PRIMARY KEY (itinerary_id, landmark_id),
        CONSTRAINT fk_itinerary_id FOREIGN KEY (itinerary_id) REFERENCES itineraries(itinerary_id),
        CONSTRAINT fk_landmark_id FOREIGN KEY (landmark_id) REFERENCES landmarks(landmark_id)
);

--Dummy data
INSERT INTO itineraries (itinerary_name, itinerary_date, staring_point)
VALUES ('First Itenarary Test', '2020-12-15', 'TBD');

INSERT INTO landmarks (landmark_name, description, venue_type, address, available_from_day, available_to_day, 
available_from_hour, available_to_hour, landmark_img_url, num_thumbs_up, num_thumbs_down)
VALUES ('First Landmark Test', 'Test Description', 'Park', '123 Some Ave Pittsburgh PA 15235', 'Monday', 'Friday', '9:00', '5:00', 'TBD', '5', '0');

INSERT INTO users_itineraries (user_id, itinerary_id)
VALUES ('1', '1');

INSERT INTO itineraries_landmarks (itinerary_id, landmark_id)
VALUES ('1', '1');
        
COMMIT TRANSACTION;
