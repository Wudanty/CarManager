package com.example.carmanager.models;

import java.util.Date;


public class Maintenance {
    private Integer maintenanceId;
    private Integer carId;
    private String maintenanceDate;
    private String maintenanceTarget;
    private String nextMaintenanceDate;
    private String nextMileage;
    private Double price;
    private String place;

    public Integer getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Integer maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(String maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getMaintenanceTarget() {
        return maintenanceTarget;
    }

    public void setMaintenanceTarget(String maintenanceTarget) {
        this.maintenanceTarget = maintenanceTarget;
    }

    public String getNextMaintenanceDate() {
        return nextMaintenanceDate;
    }

    public void setNextMaintenanceDate(String nextMaintenanceDate) {
        this.nextMaintenanceDate = nextMaintenanceDate;
    }

    public String getNextMileage() {
        return nextMileage;
    }

    public void setNextMileage(String nextMileage) {
        this.nextMileage = nextMileage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}


