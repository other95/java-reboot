package ru.sberbank.edu;

public interface CommonWeatherProvider {

    WeatherInfo get(String city);
}
