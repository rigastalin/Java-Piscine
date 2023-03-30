package edu.school21.classes;

public class Car {
    private String name;
    private String model;
    private int year;
    private double maxSpeed;

    public Car() {
    }

    public Car(String name, String model, int year, double maxSpeed) {
        this.name = name;
        this.model = model;
        this.year = year;
        this.maxSpeed = maxSpeed;
    }

    public void startEngine() {
        System.out.println("vroom-vroom");
    }

    @Override
    public String toString() {
        return "Car[" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", maxSpeed=" + maxSpeed +
                ']';
    }
}
