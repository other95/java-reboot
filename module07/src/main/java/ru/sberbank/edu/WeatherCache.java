package ru.sberbank.edu;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class WeatherCache {

    private final Map<String, WeatherInfo> cache = new HashMap<>();
    private final WeatherProvider weatherProvider;

    /**
     * Конструктор.
     *
     * @param weatherProvider - weather provider
     */
    public WeatherCache(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    /**
     * Get ACTUAL weather info for current city or null if current city not found.
     * Если кэш не содержит информацию о погоде ИЛИ содержит НЕ АКТУАЛЬНУЮ информацию то мы должны скачать информацию
     * Если ты скачал информацию о погоде то должен установить время expiry now() плюс 5 минут.
     * Если не удается скачать информацию о погоде то удалить информацию о погоде для текущего города из кэша.
     *
     * @param city - город
     * @return актуальная информация о погоде
     */
    public synchronized WeatherInfo getWeatherInfo(String city) {

            WeatherInfo weatherInfo;
            weatherInfo = cache.get(city);

            if (weatherInfo == null) {
                weatherInfo = weatherProvider.get(city);
                if (weatherInfo != null) {
                    cache.put(city, weatherInfo);
                }
            } else if (weatherInfo.getExpiryTime().isBefore(LocalDateTime.now())) {
                removeWeatherInfo(city);
                weatherInfo = weatherProvider.get(city);
                if (weatherInfo != null) {
                    cache.put(city, weatherInfo);
                }
            }
            return weatherInfo;
    }

    /**
     * Удалить информацию о погоде из кеэша
     **/
    public void removeWeatherInfo(String city) {
        cache.remove(city);
    }
}
