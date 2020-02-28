package com.softsquared.template.src.task.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SocarResponse {
    @SerializedName("result")
    SocarResult result;
    @SerializedName("isSuccess")
    public boolean isSuccess;
    @SerializedName("code")
    public int code;
    @SerializedName("message")
    public String message;

    public SocarResult getSocarResult() {
        return result;
    }

    public class SocarResult{
        @SerializedName("socarzoneAddress")
        String socarzoneAddress;
        @SerializedName("useTime")
        String useTime;
        @SerializedName("carList")
        ArrayList<SocarInfo> socarList;

        public String getSocarzoneAddress() {
            return socarzoneAddress;
        }

        public String getUseTime() {
            return useTime;
        }

        public ArrayList<SocarInfo> getSocarList() {
            return socarList;
        }
    }
}
