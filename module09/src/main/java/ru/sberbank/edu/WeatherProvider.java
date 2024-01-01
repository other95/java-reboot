package ru.sberbank.edu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Weather provider
 */
public class WeatherProvider implements CommonWeatherProvider{

     private RestTemplate restTemplate;
     private String appKey;

    public WeatherProvider(String appKey) {
        this.appKey = appKey;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    @Override
    public WeatherInfo get(String city) {

        String url = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID=56d2b70eff5993be5ff292616108c615";

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            ObjectMapper oMapper = new ObjectMapper();
            OpenWeatherResponse openWeatherResponse = oMapper.readValue(response.getBody().replace("feels_like","feelsLike"), OpenWeatherResponse.class);

            return (new WeatherInfo(openWeatherResponse));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
