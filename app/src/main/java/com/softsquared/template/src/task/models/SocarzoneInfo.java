package com.softsquared.template.src.task.models;

import com.google.gson.annotations.SerializedName;

public class SocarzoneInfo {
    @SerializedName("socarzoneNo") int socarzoneNo;
    @SerializedName("carCount") int carCount;
    @SerializedName("latitude") float latitude;
    @SerializedName("longitude") float longitude;

    public int getSocarzoneNo() { return socarzoneNo; }
    public int getCarCount() { return carCount; }
    public float getLatitude() { return latitude; }
    public float getLongitude() { return longitude; }
}
