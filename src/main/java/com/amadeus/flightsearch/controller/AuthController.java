package com.amadeus.flightsearch.controller;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/showUserString")
    public String showUserString() {
        return "test auth service";
    }
}
