package ru.sberbank.edu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/***
 * структура ответа от http://api.openweathermap.org
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class OpenWeatherResponse {

    private Main main;
    private Wind wind;
    private String name;
    private Weather[] weather;
    private long dt;
    /**
     * Обязательно должен быть безаргументный конструктор
     */
    public OpenWeatherResponse() {
    }

    public OpenWeatherResponse(Main main) {
        this.main = main;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }
}