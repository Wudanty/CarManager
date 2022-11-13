package com.example.carmanager.models;

import java.sql.Blob;

import lombok.Data;

@Data
public class Car {
    private Integer carId;
    private String brand;
    private String model;
    private Integer productionDate;
    private Double tankVolume;
    private String vin;
    private String description;
    private String fuelType;
    private Blob picture;
    private String registry;
    private String carNickname;
}
