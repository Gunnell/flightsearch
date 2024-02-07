package com.amadeus.flightsearch.repository;

import com.amadeus.flightsearch.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
