package ru.job4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Car;
import ru.job4j.model.Driver;
import ru.job4j.model.Engine;

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

            Car car1 = Car.of("Lada", "Vesta", engine1);
            Car car2 = Car.of("Lada", "Priora", engine2);
            Car car3 = Car.of("Lada", "Xray", engine3);

            car1.getDrivers().add(first);
            car2.getDrivers().add(second);
            car3.getDrivers().add(firth);

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
