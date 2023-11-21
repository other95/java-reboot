package ru.sberbank.edu;


import org.h2.tools.Server;
import ru.sberbank.edu.dbconnection.H2DbEmbedded;
import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;
import ru.sberbank.edu.repository.CarRepository;
import ru.sberbank.edu.service.CarService;
import ru.sberbank.edu.service.CarServiceImpl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class CarBootstrap {
    public static void main(String[] args) throws Exception {
        Server server = Server.createTcpServer(args).start();
        H2DbEmbedded.initDb();

        try(H2DbEmbedded h2DbEmbedded = new H2DbEmbedded()) {
            CarRepository carRepository = new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
            CarService carService = new CarServiceImpl(carRepository);

            carService.addCar("777", "Lada");

            // Test check start
            String readAllCarsSql = "SELECT * FROM car";
            Statement statement = H2DbEmbedded.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(readAllCarsSql);

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String model = resultSet.getString(2);
                System.out.println("id=" + id + "; model=" + model);
            }
            // Test end

            //Начало теста 2

            carRepository.deleteAll();
            Set<Car> carsToAdd = new HashSet<>();
            carsToAdd.add(new Car("111", "Хундай"));
            carsToAdd.add(new Car("222", "Ренаулт"));
            carsToAdd.add(new Car("333", "Пугеот"));
            carsToAdd.add(new Car("112", "Хундай"));
            carsToAdd.add(new Car("156", "Cherry"));
            carRepository.createAll(carsToAdd);
            Set<Car> cars = carRepository.findAll();
            System.out.println("Список автомобилей");
            for (Car car:cars) {
                System.out.println(car);
            }
            System.out.println();

            System.out.println("Список автомобилей модели Хундай");
            cars = carRepository.findByModel("Хундай");
            for (Car car:cars) {
                System.out.println(car);
            }

            carService.deleteCar("156");
            System.out.println("Список автомобилей после удаления ид 156");
            cars = carRepository.findAll();
            for (Car car:cars) {
                System.out.println(car);
            }
            //Конец теста 2
        }
        server.stop();

    }
}
