package com.softsquared.template.src.task.models;

import com.google.gson.annotations.SerializedName;

public class SocarInfo {
    @SerializedName("carNo")
    int carNum;
    @SerializedName("model")
    String model;
    @SerializedName("cost")
    String cost;
    @SerializedName("profileUrl")
    String profileUrl;
    @SerializedName("available")
    String available;

    public int getCarNum() {
        return carNum;
    }

    public String getModel() {
        return model;
    }

    public String getCost() {
        return cost;
    }

    public String getAvailable() {
        return available;
    }

    public String getProfileUrl() {
        return profileUrl;
    }
}
