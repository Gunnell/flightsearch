CREATE TABLE flight (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        departure_airport_id BIGINT,
                        arrival_airport_id BIGINT,
                        departure_datetime DATETIME,
                        return_datetime DATETIME,
                        price DOUBLE,
                        FOREIGN KEY (departure_airport_id) REFERENCES airport (id),
                        FOREIGN KEY (arrival_airport_id) REFERENCES airport (id)
);
CREATE TABLE airport (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         city VARCHAR(255)
);