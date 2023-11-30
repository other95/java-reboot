package ru.sberbank.edu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.http.WebSocket;

public class WeatherProvider {

    //    private RestTemplate restTemplate = null;

    /**
     * Скачать актуальную информацию о погоде и интернета.
     * Необходимо вызвать метод GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * Необходимо использовать Spring Rest Template для вызова запросов
     *
     * @param city - город
     * @return информация о погоде или null
     */
    public WeatherInfo get(String city) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID=56d2b70eff5993be5ff292616108c615";

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            ObjectMapper oMapper = new ObjectMapper();
            OpenWeatherResponse openWeatherResponse = oMapper.readValue(response.getBody(), OpenWeatherResponse.class);

            WeatherInfo weatherInfo = new WeatherInfo(openWeatherResponse);
            return weatherInfo;
        }
        catch (Exception ex) {
         return  null;
        }


    }
}
