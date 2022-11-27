package com.example.carmanager.models;

import java.util.ArrayList;

public class Action {
    public static ArrayList<Action> listOfActions = new ArrayList<>();

    private Integer actionId;
    private String name;

    public Action(Integer actionId, String name) {
        this.actionId = actionId;
        this.name = name;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
