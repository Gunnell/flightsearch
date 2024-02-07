package com.amadeus.flightsearch.controller;

import com.amadeus.flightsearch.model.Flight;
import com.amadeus.flightsearch.scheduler.ScheduledJob; //might be changed
import com.amadeus.flightsearch.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private  ScheduledJob scheduledJob;
    @Autowired
    private FlightService flightService;

    //manuel trigger for cron job
    @GetMapping("/trigger")
    public String triggerJobManually() {
        scheduledJob.fetchAndSaveFlightData();
        return "Job triggered manually";
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Optional<Flight> getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);
    }

    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable Long id, @RequestBody Flight flightDetails) {
        return flightService.updateFlight(id, flightDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}