package com.softsquared.template.src.task.models;

import com.google.gson.annotations.SerializedName;

public class SocarzoneInfo {
    @SerializedName("socarzoneNo")
    int socarzoneNo;
    @SerializedName("carCount")
    int carCount;
    @SerializedName("latitude")
    double latitude;
    @SerializedName("longitude")
    double longitude;

    public int getSocarzoneNo() {
        return socarzoneNo;
    }

    public int getCarCount() {
        return carCount;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}