package ru.job4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.*;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            List<Driver> listOfDrivers = new ArrayList<>();
            Driver first = Driver.of("Alex");
            Driver second = Driver.of("Petr");
            Driver firth = Driver.of("Anna");
            listOfDrivers.add(first);
            listOfDrivers.add(second);
            listOfDrivers.add(firth);
            listOfDrivers.forEach(session::save);

            List<Engine> engineList = new ArrayList<>();
            Engine engine1 = Engine.of("1.9 L");
            Engine engine2 = Engine.of("1.8 L");
            Engine engine3 = Engine.of("2.0 L");
            engineList.add(engine1);
            engineList.add(engine2);
            engineList.add(engine3);
            engineList.forEach(session::save);

            List<Brand> brandList = new ArrayList<>();
            Brand brand1 = Brand.of("Audi");
            brandList.add(brand1);
            brandList.forEach(session::save);

            List<TypeCar> typeCarList = new ArrayList<>();
            TypeCar typeCar1 = TypeCar.of("sedan", 4);
            TypeCar typeCar2 = TypeCar.of("pickup", 2);
            TypeCar typeCar3 = TypeCar.of("coupe", 4);
            typeCarList.add(typeCar1);
            typeCarList.add(typeCar2);
            typeCarList.add(typeCar3);
            typeCarList.forEach(session::save);

            Car car1 = Car.of(brand1, typeCar1, engine1, "description", null);
            Car car2 = Car.of(brand1, typeCar2, engine2, "description", null);
            Car car3 = Car.of(brand1, typeCar3, engine3, "description", null);

            car1.getDrivers().add(first);
            car2.getDrivers().add(second);
            car3.getDrivers().add(firth);

            List<User> userList = new ArrayList<>();
            User user1 = User.of("user1", "user1@mail.ru", "user1");
            User user2 = User.of("user2", "user2@mail.ru", "user2");
            User user3 = User.of("user3", "user3@mail.ru", "user3");
            userList.add(user1);
            userList.add(user2);
            userList.add(user3);
            userList.forEach(session::save);

            List<Advertisement> advertisementList = new ArrayList<>();
            Advertisement advertisement1 = Advertisement.of(false, user1, car1);
            Advertisement advertisement2 = Advertisement.of(false, user2, car2);
            Advertisement advertisement3 = Advertisement.of(false, user3, car3);
            advertisementList.add(advertisement1);
            advertisementList.add(advertisement2);
            advertisementList.add(advertisement3);
            advertisementList.forEach(session::save);

            session.persist(car1);
            session.persist(car2);
            session.persist(car3);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
