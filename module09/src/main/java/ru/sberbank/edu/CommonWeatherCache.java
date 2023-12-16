package ru.sberbank.edu;

public interface CommonWeatherCache {
    public WeatherInfo getWeatherInfo(String city);

    void removeWeatherInfo(String city);

}
