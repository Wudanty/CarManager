package com.example.carmanager.models;

import java.util.ArrayList;
import java.util.Date;


public class Maintenance {
    public static ArrayList<Maintenance> listOfMaintance = new ArrayList<>();

    private Integer maintenanceId;
    private Integer carId;
    private String maintenanceDate;
    private String maintenanceTarget;
    private String nextMaintenanceDate;
    private Double nextMileage;
    private Double price;
    private String description;
    private String place;

    public Maintenance(Integer maintenanceId, Integer carId, String maintenanceDate, String maintenanceTarget, String nextMaintenanceDate, Double nextMileage, Double price, String description, String place) {
        this.maintenanceId = maintenanceId;
        this.carId = carId;
        this.maintenanceDate = maintenanceDate;
        this.maintenanceTarget = maintenanceTarget;
        this.nextMaintenanceDate = nextMaintenanceDate;
        this.nextMileage = nextMileage;
        this.price = price;
        this.description = description;
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public Double getNextMileage() {
        return nextMileage;
    }

    public void setNextMileage(Double nextMileage) {
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


