package com.example.carmanager.models;

public class Checkup {
    private Integer checkupId;
    private Integer carId;
    private Integer mileageId;
    private String date;
    private String expirationDate;
    private String checkupLocation;
    private Double price;
    private Boolean isPassed;
    private String description;

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

    public Boolean getPassed() {
        return isPassed;
    }

    public void setPassed(Boolean passed) {
        isPassed = passed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
