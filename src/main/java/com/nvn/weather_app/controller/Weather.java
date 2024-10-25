package com.nvn.weather_app.controller;


import lombok.Data;

@Data
public class Weather {
    private String name;
    private String humidity;
    private String temp;
    private String windSpeed;

    // Getters and setters for all fields
}

