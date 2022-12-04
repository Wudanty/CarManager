package com.example.carmanager.models;

import java.sql.Blob;
import java.util.ArrayList;

public class Car {
    public static ArrayList<Car> listOfCars = new ArrayList<>();

    private Integer carId;
    private String brand;
    private String model;
    private Integer productionDate;
    private Double tankVolume;
    private String vin;
    private String description;
    private String fuelType;
    private byte[] picture;
    private String registry;
    private String carNickname;


    public Car(Integer carId, String brand, String model, Integer productionDate, Double tankVolume, String vin, String description, String fuelType, byte[] picture, String registry, String carNickname) {

        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.productionDate = productionDate;
        this.tankVolume = tankVolume;
        this.vin = vin;
        this.description = description;
        this.fuelType = fuelType;
        this.picture = picture;
        this.registry = registry;
        this.carNickname = carNickname;
    }

    public Car(String brand, String model, Integer productionDate, Double tankVolume, String vin, String description, String fuelType, byte[] picture, String registry, String carNickname) {
        this.brand = brand;
        this.model = model;
        this.productionDate = productionDate;
        this.tankVolume = tankVolume;
        this.vin = vin;
        this.description = description;
        this.fuelType = fuelType;
        this.picture = picture;
        this.registry = registry;
        this.carNickname = carNickname;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Integer productionDate) {
        this.productionDate = productionDate;
    }

    public Double getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(Double tankVolume) {
        this.tankVolume = tankVolume;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public String getCarNickname() {
        return carNickname;
    }

    public void setCarNickname(String carNickname) {
        this.carNickname = carNickname;
    }
}

