package com.example.carmanager.models;

import java.util.ArrayList;

public class Car {
    public static ArrayList<Car> listOfCars = new ArrayList<>();

    private Integer carId;
    private String brand;
    private String model;
    private Integer productionDate;
    private Double tankVolume;
    private Double engineCapacity;
    private int enginePower;
    private Double weight;
    private String vin;
    private String bodyType;
    private String colour;
    private String shifterType;
    private String description;
    private String fuelType;
    private byte[] picture;
    private String registry;
    private String carNickname;


    public Car(Integer carId, String brand, String model, Integer productionDate, Double tankVolume, Double engineCapacity, int enginePower, Double weight, String vin, String bodyType, String colour, String shifterType, String description, String fuelType, byte[] picture, String registry, String carNickname) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.productionDate = productionDate;
        this.tankVolume = tankVolume;
        this.engineCapacity = engineCapacity;
        this.enginePower = enginePower;
        this.weight = weight;
        this.vin = vin;
        this.bodyType = bodyType;
        this.colour = colour;
        this.shifterType = shifterType;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getShifterType() {
        return shifterType;
    }

    public void setShifterType(String shifterType) {
        this.shifterType = shifterType;
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

