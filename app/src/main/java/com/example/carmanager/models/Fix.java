package com.example.carmanager.models;

import java.util.ArrayList;

public class Fix {
    public static ArrayList<Fix> listOfFix = new ArrayList<>();

    private Integer fixId;
    private Integer carId;
    private String dateOfFix;
    private String fixDescription;
    private String warnings;
    private Double price;
    private String placeOfFix;

    public Fix(Integer fixId, Integer carId, String dateOfFix, String fixDescription, String warnings, Double price, String placeOfFix) {
        this.fixId = fixId;
        this.carId = carId;
        this.dateOfFix = dateOfFix;
        this.fixDescription = fixDescription;
        this.warnings = warnings;
        this.price = price;
        this.placeOfFix = placeOfFix;
    }

    public Integer getFixId() {
        return fixId;
    }

    public void setFixId(Integer fixId) {
        this.fixId = fixId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getDateOfFix() {
        return dateOfFix;
    }

    public void setDateOfFix(String dateOfFix) {
        this.dateOfFix = dateOfFix;
    }

    public String getFixDescription() {
        return fixDescription;
    }

    public void setFixDescription(String fixDescription) {
        this.fixDescription = fixDescription;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPlaceOfFix() {
        return placeOfFix;
    }

    public void setPlaceOfFix(String placeOfFix) {
        this.placeOfFix = placeOfFix;
    }
}
