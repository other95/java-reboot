package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Travel Service.
 */
public class TravelService {

    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();

    static final int EARTH_RADIUS = 6372795;

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        if ( cities.stream()
                .anyMatch(cityInfoStream -> cityInfo.getName().equals(cityInfoStream.getName()))) {
            System.out.println("Город "+cityInfo.getName()+" уже существует в списке городов");
            return;
        }

        cities.add(cityInfo);
    }

    /**
     * Удалить город из списка.
     *
     * @param cityName - Название города
     * @throws IllegalArgumentException Если город нге существует
     */
    public void remove(String cityName) throws IllegalArgumentException {

        cities.removeIf( cityInfo -> cityInfo.getName().equals(cityName) );

    }

    /**
     * Получить список названий городов.
     */
    public List<String> citiesNames() {
        List<String> list = new ArrayList<>();
        cities.forEach( ( CityInfo cityInfo) -> list.add(cityInfo.getName()));
        return list;
    }

    /**
     * Получить расстояние между двумя городами в километрах.
     *
     * @param srcCityName  - город откуда
     * @param destCityName - город куда
     * @throws IllegalArgumentException если какого нибудь из городов не существует.
     */
    public int getDistance(String srcCityName, String destCityName) throws IllegalArgumentException{

        CityInfo srcCityInfo = cities.stream()
                .filter(cityInfo -> srcCityName.equals(cityInfo.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        CityInfo destCityInfo = cities.stream()
                .filter(cityInfo -> destCityName.equals(cityInfo.getName()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        // косинусы и синусы широт и разницы долгот
        double cl1 = Math.cos(srcCityInfo.getPosition().getLatitude());
        double sl1 = Math.sin(srcCityInfo.getPosition().getLatitude());

        double cl2 = Math.cos(destCityInfo.getPosition().getLatitude());
        double sl2 = Math.sin(destCityInfo.getPosition().getLatitude());
        double delta = srcCityInfo.getPosition().getLongitude() - destCityInfo.getPosition().getLongitude() ;
        double cdelta = Math.cos(delta);
        double sdelta = Math.sin(delta);

        // вычисления длины большого круга
        double y = Math.sqrt( Math.pow(cl2 * sdelta,2) + Math.pow(cl1 * sl2 - sl1 * cl2 * cdelta,2));
        double x = sl1 * sl2 + cl1 * cl2 * cdelta;

        double ad = Math.atan2(y, x);
        double dist = ad * EARTH_RADIUS/1000.0;

        return (int)Math.ceil(dist);
    }

    /**
     * Получить все города рядом с выбранным городе в указанном радиусе
     *
     * @param cityName - город
     * @param radius   - радиус поиска в километрах
     * @throws IllegalArgumentException если города не существует.
     */
    public List<String> getCitiesNear(String cityName, int radius) throws IllegalArgumentException {

        List<String> list = new ArrayList<>();
        cities.forEach( ( CityInfo cityInfo) -> {
            if (!Objects.equals(cityName, cityInfo.getName()) &&
                    getDistance(cityName, cityInfo.getName() ) <= radius ) {
                list.add(cityInfo.getName());
            }
        } );
        return list;

    }
}
