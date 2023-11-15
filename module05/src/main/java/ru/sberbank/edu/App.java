package ru.sberbank.edu;

import java.util.List;

/**
 * Модуль 05 Задача 01
 * Реализация Сервиса путешествий по городам
 */
public class App 
{
    public static void main( String[] args )
    {
       TravelService travelService = new TravelService();
       travelService.add( new CityInfo("Москва", new GeoPosition("55(45'132'')","37(36'936'')")));
       travelService.add( new CityInfo("Санкт-Петербург", new GeoPosition("59(57'00'')","30(19'00'')")));
       travelService.add( new CityInfo("Казань", new GeoPosition("55(47'00'')","49(6'00'')")));
       travelService.add( new CityInfo("Астрахань", new GeoPosition("46(21'00'')","48(02'00'')")));
       travelService.add( new CityInfo("Новгород", new GeoPosition("58(31'16'')","31(16'15'')")));
       travelService.add( new CityInfo("Рязань", new GeoPosition("54(38'37'')","39(41'30'')")));
       travelService.add( new CityInfo("Суздаль", new GeoPosition("56(25'21'')","40(26'48'')")));
       travelService.add( new CityInfo("Владимир", new GeoPosition("56(08'11'')","40(23'47'')")));
       System.out.println("Список городов :" + travelService.citiesNames());
       System.out.println("Расстояние между Петербургом и Москвой :" +travelService.getDistance("Санкт-Петербург","Москва" ));

       List<String> list = travelService.getCitiesNear("Новгород", 520 );

    }
}
