package ru.sberbank.edu;

/**
 * Погода openweathermap.com
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        WeatherProvider weatherProvider = new WeatherProvider();
        WeatherCache weatherCache = new WeatherCache(weatherProvider);
        System.out.println(weatherCache.getWeatherInfo("London,uk"));
        System.out.println(weatherCache.getWeatherInfo("Moscow,ru"));
        System.out.println(weatherCache.getWeatherInfo("London,uk"));
        System.out.println(weatherCache.getWeatherInfo("qwerty"));

    }
}
