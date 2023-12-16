package ru.sberbank.edu;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Weather info.
 */
public class WeatherInfo {

    private final int FIVE_MINUTES = 300000;

    private String city;

    /**
     * Short weather description
     * Like 'sunny', 'clouds', 'raining', etc
     */
    private String shortDescription;

    /**
     * Weather description.
     * Like 'broken clouds', 'heavy raining', etc
     */
    private String description;

    /**
     * Temperature.
     */
    private double temperature;

    /**
     * Temperature that fells like.
     */
    private double feelsLikeTemperature;

    /**
     * Wind speed.
     */
    private double windSpeed;

    /**
     * Pressure.
     */
    private double pressure;

    /**
     * Expiry time of weather info.
     * If current time is above expiry time then current weather info is not actual!
     */
    private LocalDateTime expiryTime;

    /***
     * конструктор с параметром - класс структура ответа от api.openweathermap.org
     * @param openWeatherResponse - структура ответа
     */
    public WeatherInfo(OpenWeatherResponse openWeatherResponse) {
        this.city = openWeatherResponse.getName();
        this.shortDescription = openWeatherResponse.getWeather()[0].getMain();
        this.description = openWeatherResponse.getWeather()[0].getDescription();
        this.temperature = openWeatherResponse.getMain().getTemp();
        this.feelsLikeTemperature = openWeatherResponse.getMain().getFeelsLike();
        this.windSpeed = openWeatherResponse.getWind().getSpeed();
        this.pressure = openWeatherResponse.getMain().getPressure();
        this.expiryTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(openWeatherResponse.getDt()*1000+FIVE_MINUTES),
                ZoneId.systemDefault());

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public void setFeelsLikeTemperature(double feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    @Override
    public String toString() {
        return "WeatherInfo\n{" +
                " city='" + city + '\'' +
                ", \n shortDescription='" + shortDescription + '\'' +
                ", \n description='" + description + '\'' +
                ", \n temperature=" + temperature +
                ", \n feelsLikeTemperature=" + feelsLikeTemperature +
                ", \n windSpeed=" + windSpeed +
                ", \n pressure=" + pressure +
                ", \n expiryTime=" + expiryTime +
                " } \n";
    }
}

