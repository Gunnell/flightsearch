package com.amadeus.flightsearch.repository;
import com.amadeus.flightsearch.model.Flight;
import com.amadeus.flightsearch.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findTwoWayFlights(
        Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime);

    List<Flight> findFlights(
            Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateTime); 

    List<Flight> findByDepAirportArrAirport(
            Airport departureAirport, Airport arrivalAirport);//might be deleted

}