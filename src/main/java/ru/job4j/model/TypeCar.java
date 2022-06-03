package ru.job4j.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "typeCar")
public class TypeCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int countOfDoors;

    public TypeCar() {
    }

    public static TypeCar of(String name, int countOfDoors) {
        TypeCar typeCar = new TypeCar();
        typeCar.name = name;
        typeCar.countOfDoors = countOfDoors;
        return typeCar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountOfDoors() {
        return countOfDoors;
    }

    public void setCountOfDoors(int countOfDoors) {
        this.countOfDoors = countOfDoors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypeCar typeCar = (TypeCar) o;
        return id == typeCar.id && Objects.equals(name, typeCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
