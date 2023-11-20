package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoPositionTest {

    @Test
    void getLatitude() {
        GeoPosition geoPosition = new GeoPosition("30(59'60'')","50");
        assertEquals(Math.round(geoPosition.getLatitude()*1000.0)/1000.0,0.541);
    }

    @Test
    void getLongitude() {
        GeoPosition geoPosition = new GeoPosition("30(59'60'')","50");
        assertEquals(Math.round(geoPosition.getLongitude()*1000.0)/1000.0,0.873);
    }
}