package com.example.carmanager.models;

import lombok.Data;

@Data
public class CarTodo {
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
}
