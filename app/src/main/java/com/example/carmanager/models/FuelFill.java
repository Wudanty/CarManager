package com.example.carmanager.models;

import java.util.ArrayList;

public class FuelFill {
    public static ArrayList<FuelFill> listOfFuelFill = new ArrayList<>();

    private Integer fillId;
    private Integer carId;
    private String fillDate;
    private Double pricePerLiter;
    private Double price;
    private String stationName;
    private Double literAmount;
    private String fuelType;

    public FuelFill(Integer fillId, Integer carId, String fillDate, Double pricePerLiter, Double price, String stationName, Double literAmount, String fuelType) {
        this.fillId = fillId;
        this.carId = carId;
        this.fillDate = fillDate;
        this.pricePerLiter = pricePerLiter;
        this.price = price;
        this.stationName = stationName;
        this.literAmount = literAmount;
        this.fuelType = fuelType;
    }

    public FuelFill(Integer carId, String fillDate, Double pricePerLiter, Double price, String stationName, Double literAmount, String fuelType) {
        this.carId = carId;
        this.fillDate = fillDate;
        this.pricePerLiter = pricePerLiter;
        this.price = price;
        this.stationName = stationName;
        this.literAmount = literAmount;
        this.fuelType = fuelType;
    }

    public Integer getFillId() {
        return fillId;
    }

    public void setFillId(Integer fillId) {
        this.fillId = fillId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getFillDate() {
        return fillDate;
    }

    public void setFillDate(String fillDate) {
        this.fillDate = fillDate;
    }

    public Double getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(Double pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Double getLiterAmount() {
        return literAmount;
    }

    public void setLiterAmount(Double literAmount) {
        this.literAmount = literAmount;
    }

    public String  getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
