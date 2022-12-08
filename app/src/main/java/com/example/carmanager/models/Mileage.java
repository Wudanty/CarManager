package com.example.carmanager.models;

import java.util.ArrayList;

public class Mileage {
    public static ArrayList<Mileage> listOfMIleage = new ArrayList<>();

    private Integer mileageId;
    private Integer carId;
    private Integer actionId; //What is this?
    private String mileageCheckDate;
    private Double mileageValue;
    private Integer actionType;

    public Mileage(Integer mileageId, Integer carId, Integer actionId, String mileageCheckDate, Double mileageValue, Integer actionType) {
        this.mileageId = mileageId;
        this.carId = carId;
        this.actionId = actionId;
        this.mileageCheckDate = mileageCheckDate;
        this.mileageValue = mileageValue;
        this.actionType = actionType;
    }


    public Mileage(Integer carId, Integer actionId, String mileageCheckDate, Double mileageValue, Integer actionType) {
        this.carId = carId;
        this.actionId = actionId;
        this.mileageCheckDate = mileageCheckDate;
        this.mileageValue = mileageValue;
        this.actionType = actionType;
    }

    public Mileage(Integer carId, String mileageCheckDate, Double mileageValue) {

        this.carId = carId;
        this.mileageCheckDate = mileageCheckDate;
        this.mileageValue = mileageValue;
    }

    public Integer getMileageId() {
        return mileageId;
    }

    public void setMileageId(Integer mileageId) {
        this.mileageId = mileageId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getMileageCheckDate() {
        return mileageCheckDate;
    }

    public void setMileageCheckDate(String mileageCheckDate) {
        this.mileageCheckDate = mileageCheckDate;
    }

    public Double getMileageValue() {
        return mileageValue;
    }

    public void setMileageValue(Double mileageValue) {
        this.mileageValue = mileageValue;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }
}
