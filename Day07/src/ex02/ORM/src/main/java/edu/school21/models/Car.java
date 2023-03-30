package edu.school21.models;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;

@OrmEntity(table = "Simple_cars")
public class Car {
    @OrmColumnId
    private Long id;
    @OrmColumn(name = "model", length = 50)
    private String model;
    @OrmColumn(name = "price")
    private Double price;
    private int speed;
    @OrmColumn(name = "is_sold")
    private Boolean isSold;

    @Override
    public String toString() {
        return "Car {" +
                "id= " + id +
                ", model = '" + model + '\'' +
                ", price = " + price +
                ", speed = " + speed +
                ", isSold = " + isSold +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Boolean getReserved() {
        return isSold;
    }

    public void setReserved(Boolean isSold) {
        this.isSold = isSold;
    }

    public Car(Long id, String model, Double price, int speed, Boolean isSold) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.speed = speed;
        this.isSold = isSold;
    }

    public Car() {
    }
}
