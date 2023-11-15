package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TravelServiceTest {

    @Test
    void add() {
        TravelService travelService = new TravelService();
        travelService.add( new CityInfo("Москва", new GeoPosition("55(45'132'')","37(36'936'')")));
        assertEquals( travelService.citiesNames().get(0),"Москва"); ;
    }

    @Test
    void remove() {
        TravelService travelService = new TravelService();
        travelService.add( new CityInfo("Санкт-Петербург", new GeoPosition("59(57'00'')","30(19'00'')")));
        travelService.add( new CityInfo("Москва", new GeoPosition("55(45'132'')","37(36'936'')")));
        travelService.remove("Санкт-Петербург");
        assertEquals( travelService.citiesNames().get(0),"Москва");
    }

    @Test
    void getDistance() {
        TravelService travelService = new TravelService();
        travelService.add( new CityInfo("Санкт-Петербург", new GeoPosition("59(57'00'')","30(19'00'')")));
        travelService.add( new CityInfo("Москва", new GeoPosition("55(45'132'')","37(36'936'')")));
        assertEquals( travelService.getDistance("Санкт-Петербург","Москва" ),643 );

    }

    @Test
    void getCitiesNear() {
        TravelService travelService = new TravelService();
        travelService.add( new CityInfo("Москва", new GeoPosition("55(45'132'')","37(36'936'')")));
        travelService.add( new CityInfo("Санкт-Петербург", new GeoPosition("59(57'00'')","30(19'00'')")));
        travelService.add( new CityInfo("Новгород", new GeoPosition("58(31'16'')","31(16'15'')")));
        assertEquals( travelService.getCitiesNear("Новгород",400).get(0),"Санкт-Петербург");

    }
}