package com.example.carmanager.models;

import java.util.ArrayList;

public class Notification {
    public static ArrayList<Notification> listOfNotification = new ArrayList<>();
    private Integer notificationId;
    private Integer carId;
    private String date;
    private String description;
    private Integer importance;
    private Integer notificationType;
    private Integer kilometre;
    private String name;
    private String powtarzanie;

    public Notification(Integer notificationId, Integer carId, String date, String description, Integer importance, Integer notificationType, Integer kilometre, String name, String powtarzanie) {
        this.notificationId = notificationId;
        this.carId = carId;
        this.date = date;
        this.description = description;
        this.importance = importance;
        this.notificationType = notificationType;
        this.kilometre = kilometre;
        this.name = name;
        this.powtarzanie = powtarzanie;
    }

    public Notification(Integer carId, String date, String description, Integer importance, Integer notificationType, Integer kilometre, String name, String powtarzanie) {
        this.carId = carId;
        this.date = date;
        this.description = description;
        this.importance = importance;
        this.notificationType = notificationType;
        this.kilometre = kilometre;
        this.name = name;
        this.powtarzanie = powtarzanie;
    }

    public String getPowtarzanie() {return powtarzanie;}

    public void setPowtarzanie(String powtarzanie) {this.powtarzanie = powtarzanie;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKilometre() {
        return kilometre;
    }

    public void setKilometre(Integer kilometre) {
        this.kilometre = kilometre;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public Integer getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Integer notificationType) {
        this.notificationType = notificationType;
    }
}
