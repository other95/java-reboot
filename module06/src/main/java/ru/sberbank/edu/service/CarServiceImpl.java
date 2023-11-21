package ru.sberbank.edu.service;


import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarRepository;

import java.sql.SQLException;
import java.util.Optional;

/***
 * АвтоСервис
 */
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /***
     * Добавить машину
     * @param id идентификатор
     * @param model модель
     * @throws SQLException
     */
    @Override
    public void addCar(String id, String model) throws SQLException {
        carRepository.createOrUpdate(new Car(id, model));
    }

    /***
     * Улаить машину
     * @param id идентификатор
     */
    @Override
    public void deleteCar(String id) {
        carRepository.deleteById(id);
    }

    /***
     * Редатировать модель машины
     * @param id идентификатор
     * @param newModel ноавя модель
     * @throws SQLException
     */
    @Override
    public void editModel(String id, String newModel) throws SQLException {
        Optional<Car> optCar = carRepository.findById(id);
        Car car = optCar.orElseThrow();
        updateCarModel(car, newModel);
    }

    /***
     * Обновить модель
     * @param car машина
     * @param newModel новая модель
     */
    private void updateCarModel(Car car, String newModel) {
        car.setModel(newModel);
        try {
            carRepository.createOrUpdate(car);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
