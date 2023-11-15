package ru.sberbank.edu;

import java.util.regex.*;

/**
 * Geo position.
 */
public class GeoPosition {

    /**
     * Широта в радианах.
     */
    private double latitude;

    /**
     * Долгота в радианах.
     */
    private double longitude;

    /**
     * Ctor.
     *
     * @param latitudeGradus  - широта в градусах
     * @param longitudeGradus - долгота в гградусах
     *                        Возможные значения: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus) throws NumberFormatException {
        latitude = getDegrees(latitudeGradus);
        longitude = getDegrees(longitudeGradus);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String toString() {
        return "[ Широта: "+latitude+", Долгота: "+longitude+" ]";
    }

    /***
     * Получить координату в радианах
     * @param inDegrees - координата в градусах
     * @return -координата в радианах
     */
    private Double getDegrees(String inDegrees) {
        String regex_format = "\\d{1,2}(\\(\\d{1,2}\'\\d{1,3}\'\'\\))?";
        String regex_number = "\\d{1,3}";

        Pattern r = Pattern.compile(regex_number);
        if ( !Pattern.matches(regex_format,inDegrees) ) {
            throw new NumberFormatException("Неверный формат координат!");
        }

        Matcher matcher = r.matcher(inDegrees);

        Double[] degreeList = new Double[]{0.0, 0.0, 0.0};
        int i = 0;
        while (matcher.find()) {
            degreeList[i] = Double.parseDouble(inDegrees.substring(matcher.start(), matcher.end()));
            i++;
        }
        return ((degreeList[0] + (degreeList[1] + degreeList[2]/60)/60)*Math.PI/180);
    }

}