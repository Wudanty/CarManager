package com.example.carmanager.models;

import lombok.Data;

@Data
public class CarTodo {
    private Integer carTodoId;
    private Integer carId;
    private String name;
    private String description;
}
