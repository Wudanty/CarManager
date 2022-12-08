package com.example.carmanager.models;

import java.util.ArrayList;

public class Checkup {
    public static ArrayList<Checkup> listOfCheckup = new ArrayList<>();

    private Integer checkupId;
    private Integer carId;
    private Integer mileageId;
    private String date;
    private String expirationDate;
    private String checkupLocation;
    private Double price;
    private Integer isPassed;
    private String description;

    public Checkup(Integer checkupId, Integer carId, Integer mileageId, String date, String expirationDate, String checkupLocation, Double price, Integer isPassed, String description) {
        this.checkupId = checkupId;
        this.carId = carId;
        this.mileageId = mileageId;
        this.date = date;
        this.expirationDate = expirationDate;
        this.checkupLocation = checkupLocation;
        this.price = price;
        this.isPassed = isPassed;
        this.description = description;
    }

    public Checkup(Integer carId, Integer mileageId, String date, String expirationDate, String checkupLocation, Double price, Integer isPassed, String description) {
        this.carId = carId;
        this.mileageId = mileageId;
        this.date = date;
        this.expirationDate = expirationDate;
        this.checkupLocation = checkupLocation;
        this.price = price;
        this.isPassed = isPassed;
        this.description = description;
    }

    public Integer getCheckupId() {
        return checkupId;
    }

    public void setCheckupId(Integer checkupId) {
        this.checkupId = checkupId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getMileageId() {
        return mileageId;
    }

    public void setMileageId(Integer mileageId) {
        this.mileageId = mileageId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCheckupLocation() {
        return checkupLocation;
    }

    public void setCheckupLocation(String checkupLocation) {
        this.checkupLocation = checkupLocation;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPassed() {
        return isPassed;
    }

    public void setPassed(Integer passed) {
        isPassed = passed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
