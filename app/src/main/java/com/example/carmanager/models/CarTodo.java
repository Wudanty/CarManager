package com.example.carmanager.models;


import java.util.ArrayList;

public class CarTodo {
    public static ArrayList<CarTodo> listOfCarTodo = new ArrayList<>();

    private Integer carTodoId;
    private Integer carId;
    private String name;
    private String description;

    public CarTodo(Integer carTodoId, Integer carId, String name, String description) {
        this.carTodoId = carTodoId;
        this.carId = carId;
        this.name = name;
        this.description = description;
    }

    public CarTodo(Integer carId, String name, String description) {
        this.carId = carId;
        this.name = name;
        this.description = description;
    }

    public Integer getCarTodoId() {
        return carTodoId;
    }

    public void setCarTodoId(Integer carTodoId) {
        this.carTodoId = carTodoId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
