package com.amadeus.flightsearch.service;

import com.amadeus.flightsearch.model.Flight;
import com.amadeus.flightsearch.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight details) {
        Flight flight = flightRepository.findById(id).orElse(null);
        if (flight != null) {
            
            flight.setDepartureAirport(details.getDepartureAirport());
            flight.setArrivalAirport(details.getArrivalAirport());
            flight.setDepartureDateTime(details.getDepartureDateTime());
            flight.setReturnDateTime(details.getReturnDateTime());
            flight.setPrice(details.getPrice());

            return flightRepository.save(flight);
        }
        return null;
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}