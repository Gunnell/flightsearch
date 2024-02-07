package com.amadeus.flightsearch.scheduler;

import com.amadeus.flightsearch.model.Airport;
import com.amadeus.flightsearch.model.Flight;
import com.amadeus.flightsearch.repository.AirportRepository;
import com.amadeus.flightsearch.repository.FlightRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Log4j2
@Component
public class ScheduledJob {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ScheduledJob(FlightRepository flightRepository, AirportRepository airportRepository, RestTemplate restTemplate) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void fetchAndSaveFlightData() {
        String apiUrl = "http://localhost:8080/mock/flights";

        Flight[] flights = restTemplate.getForObject(apiUrl, Flight[].class);
        if (flights != null) {
            for (Flight flight : flights) {
                Airport departureAirport = flight.getDepartureAirport();
                Airport arrivalAirport = flight.getArrivalAirport();

                airportRepository.save(departureAirport);
                airportRepository.save(arrivalAirport);

                flight.setDepartureAirport(departureAirport);
                flight.setArrivalAirport(arrivalAirport);
            }

            flightRepository.saveAll(Arrays.asList(flights));
            log.info("Flight data fetched and saved.");
        }
        else {
            log.info("Failed to fetch flight data.");
        }
    }
}