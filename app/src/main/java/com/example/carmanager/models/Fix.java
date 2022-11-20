package com.example.carmanager.models;

public class Fix {
    private Integer fixId;
    private Integer carId;
    private String dateOfFix;
    private String fixDescription;
    private String warnings;
    private Double price;
    private String placeOfFix;

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
