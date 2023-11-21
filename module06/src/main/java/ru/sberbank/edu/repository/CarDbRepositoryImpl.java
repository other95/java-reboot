package ru.sberbank.edu.repository;


import ru.sberbank.edu.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Хпвнилище машин (БД)
 */
public class CarDbRepositoryImpl implements CarRepository {
    private final Connection connection;
    private static final String CREATE_CAR_SQL = "INSERT INTO car (id, model) VALUES (?,?)";
    private static final String UPDATE_CAR_SQL = "UPDATE car SET model = ? WHERE id = ?";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";
    private static final String DELETE_CAR_SQL = "DELETE FROM car WHERE id = ?";
    private static final String DELETE_ALL_CAR_SQL = "DELETE FROM car";
    private static final String SELECT_CAR_BY_MODEL = "SELECT * FROM car WHERE model = ?";
    private static final String SELECT_ALL_CAR = "SELECT * FROM car";

    private final PreparedStatement createPreStmt;
    private final PreparedStatement updatePreStmt;
    private final PreparedStatement findByIdPreStmt;
    private final PreparedStatement deletePreStmt;
    private final PreparedStatement deleteAllPreStmt;
    private final PreparedStatement findByModelPreStmt;
    private final PreparedStatement getAllCarPreStmt;
    public CarDbRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.createPreStmt = connection.prepareStatement(CREATE_CAR_SQL);
        this.updatePreStmt = connection.prepareStatement(UPDATE_CAR_SQL);
        this.findByIdPreStmt = connection.prepareStatement(SELECT_CAR_BY_ID);
        this.deletePreStmt = connection.prepareStatement(DELETE_CAR_SQL);
        this.deleteAllPreStmt = connection.prepareStatement(DELETE_ALL_CAR_SQL);
        this.findByModelPreStmt = connection.prepareStatement(SELECT_CAR_BY_MODEL);
        this.getAllCarPreStmt = connection.prepareStatement(SELECT_ALL_CAR);

    }

    /***
     * Создать или изменить машину
     * @param car - машина
     * @return - измененная машина
     * @throws SQLException
     */
    @Override
    public Car createOrUpdate(Car car) throws SQLException {
        Optional<Car> optCar = findById(car.getId());
        if (optCar.isEmpty()) {
            createPreStmt.setString(1, car.getId());
            createPreStmt.setString(2, car.getModel());
            createPreStmt.executeUpdate();
        } else {
            updatePreStmt.setString(1, car.getModel());
            updatePreStmt.setString(2, car.getId());
            updatePreStmt.executeUpdate();
        }
        return car;
    }

    /***
     * найти машину
     * @param id идентификатор
     * @return - машина
     * @throws SQLException
     */
    @Override
    public Optional<Car> findById(String id) throws SQLException {
        // validation
        int rowsCount = countRowsById(id);
        if (rowsCount > 1) {
            throw new RuntimeException("Car with id = " + id + " was found many times (" + rowsCount + ").");
        } else if (rowsCount == 0) {
            return Optional.empty();
        }

        findByIdPreStmt.setString(1, id);
        ResultSet resultSet = findByIdPreStmt.executeQuery();

        resultSet.next();
        Car car = new Car(resultSet.getString(1), resultSet.getString(2));
        return Optional.of(car);
    }

    /***
     * Удалить машину
     * @param id - идентификатор
     * @return - успешность операции
     */
    @Override
    public Boolean deleteById(String id)  {
        try {
            deletePreStmt.setString(1, id);
            deletePreStmt.executeUpdate();
        }
        catch (SQLException ex) {
            return false;
        }
        return true;
    }

    /***
     * Количество машин с заданным идентификатором
     * @param id идентификатор
     * @return количество
     * @throws SQLException
     */
    private int countRowsById(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM car where id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int rowCount = 0;
        while (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }

    /***
     * Найти по модели
     * @param model - модель
     * @return - Набор машин
     */
    @Override
    public Set<Car> findByModel(String model) {
        Set<Car> set = new HashSet<>();
        try {
            findByModelPreStmt.setString(1, model);
            ResultSet resultSet = findByModelPreStmt.executeQuery();

            while (resultSet.next()) {
                Car car = new Car(resultSet.getString(1), resultSet.getString(2));
                set.add(car);
            }
        }
        catch (SQLException e) {
            return null;
        }
        return set;
    }

    /***
     * Добавить все машины
     * @param tCollection
     * @return - Набор машин
     */
    @Override
    public Set<Car> createAll(Collection<Car> tCollection) {
        try {
            for (Car car : tCollection) {
                createOrUpdate(car);
            }
        }
        catch (SQLException e) {
            return  null;
        }
        Set<Car> set = findAll();
        return set;
    }

    /***
     * Найти все машины
      * @return - Набор машин
     */
    @Override
    public Set<Car> findAll() {
        Set<Car> set = new HashSet<>();
        try {
            ResultSet resultSet = getAllCarPreStmt.executeQuery();

            while (resultSet.next()) {
                Car car = new Car(resultSet.getString(1), resultSet.getString(2));
                set.add(car);
            }
        }
        catch (SQLException e) {
            return null;
        }
        return set;
    }

    /***
     * Удалить все машины
     * @return - успешность операции
     */
    @Override
    public Boolean deleteAll() {
        try {
            deleteAllPreStmt.executeUpdate();
        }
        catch (SQLException ex) {
            return false;
        }
        return true;
    }

}
