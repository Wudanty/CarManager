package com.example.carmanager.models;

public class Mileage {
    private Integer mileageId;
    private Integer carId;
    private Integer actionId; //What is this?
    private String mileageCheckDate;
    private Integer mileageValue;
    private Integer actionType;

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

    public Integer getMileageValue() {
        return mileageValue;
    }

    public void setMileageValue(Integer mileageValue) {
        this.mileageValue = mileageValue;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }
}
