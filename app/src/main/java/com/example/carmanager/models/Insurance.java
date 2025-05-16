package com.example.carmanager.models;

import java.util.ArrayList;

public class Insurance {
    public static ArrayList<Insurance> listOfInsurance = new ArrayList<>();

    private Integer insuranceId;
    private Integer carId;
    private String startDate;
    private String expirationDate;
    private String provider;
    private Double price;
    private String insuranceType;
    private String insuranceNumber;

    public Insurance(Integer insuranceId, Integer carId, String startDate, String expirationDate, String provider, Double price, String insuranceType, String insuranceNumber) {
        this.insuranceId = insuranceId;
        this.carId = carId;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.provider = provider;
        this.price = price;
        this.insuranceType = insuranceType;
        this.insuranceNumber = insuranceNumber;
    }

    public Insurance(Integer carId, String startDate, String expirationDate, String provider, Double price, String insuranceType, String insuranceNumber) {
        this.carId = carId;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.provider = provider;
        this.price = price;
        this.insuranceType = insuranceType;
        this.insuranceNumber = insuranceNumber;
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }
}
