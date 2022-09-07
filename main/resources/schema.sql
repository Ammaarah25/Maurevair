CREATE TABLE IF NOT EXISTS airport
	(
		code VARCHAR(255),
		country 	VARCHAR(255),
		region 		VARCHAR(255),
		city		VARCHAR(255),
		PRIMARY KEY (code)
	);
	
CREATE TABLE IF NOT EXISTS flight
	(
		id VARCHAR(255),
		carrier 	VARCHAR(255),
		fnumber		VARCHAR(255),
		origin		VARCHAR(255),
		destination		VARCHAR(255),
		departure		VARCHAR(255),
		arrival		VARCHAR(255),
		first_class INT(10),
		premium		INT(10),
		economy		INT(10),
		PRIMARY KEY (id)
	);
	
CREATE TABLE IF NOT EXISTS booking
	(
		flight_id   VARCHAR(255),
		first_class INT(10),
		premium		INT(10),
		economy		INT(10),
		PRIMARY KEY (flight_id)
	);