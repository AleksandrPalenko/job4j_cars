package ru.job4j.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private String brand;

    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "driver_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "car_id", nullable = false, updatable = false)})
    private Set<Driver> drivers = new HashSet<>();

    public Car() {
    }

    public static Car of(String brand, String model, Engine engine) {
        Car car = new Car();
        car.model = brand;
        car.brand = model;
        car.engine = engine;
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", name='" + model + '\''
                + '}';
    }
}