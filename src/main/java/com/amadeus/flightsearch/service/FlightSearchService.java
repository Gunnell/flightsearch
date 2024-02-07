package com.amadeus.flightsearch.service;

import com.amadeus.flightsearch.model.Airport;
import com.amadeus.flightsearch.model.Flight;
import com.amadeus.flightsearch.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightSearchService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> searchFlights(Airport departureAirport, Airport arrivalAirport,
                                      LocalDateTime departureDateTime, LocalDateTime returnDateTime) {
        if (returnDateTime == null) {
            return flightRepository.findFlights(
                    departureAirport, arrivalAirport, departureDateTime);
        } else {
            List<Flight> oneWayFlights = flightRepository.findFlights(
                    departureAirport, arrivalAirport, departureDateTime);
            List<Flight> returnFlights = flightRepository.findFlights(
                    arrivalAirport, departureAirport, returnDateTime);

            oneWayFlights.addAll(returnFlights);
            return oneWayFlights;
        }
    }
}
