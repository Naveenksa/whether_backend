package com.nvn.weather_app.service;

import com.nvn.weather_app.controller.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${openweathermap.api.key}")
    private String apiKey;

    public Weather getWeather(String city)
    {

        String url = UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .toUriString();

        Map<String, Object> weatherInfo = restTemplate.getForObject(url, Map.class);

           System.out.println(weatherInfo);

        Map<String, Object> mainInfo = (Map<String, Object>) weatherInfo.get("main");
        Map<String, Object> windInfo = (Map<String, Object>) weatherInfo.get("wind");

        String name = (String) weatherInfo.get("name");
        double temp = (double) mainInfo.get("temp");
        int humidity = (int) mainInfo.get("humidity");
        double windSpeed=(double) windInfo.get("speed");

        Weather weather = new Weather();
          weather.setName(name);
          weather.setHumidity(humidity + " %");
          weather.setTemp(temp + " °c");
          weather.setWindSpeed(windSpeed+" Km/hr");

          return weather;
    }
}
