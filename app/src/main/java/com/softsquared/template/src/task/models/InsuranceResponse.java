package com.softsquared.template.src.task.models;

import com.google.gson.annotations.SerializedName;

public class InsuranceResponse {
    @SerializedName("result")
    InsuranceResult result;
    @SerializedName("isSuccess")
    public boolean isSuccess;
    @SerializedName("code")
    public int code;
    @SerializedName("message")
    public String message;

    public class InsuranceResult{
        @SerializedName("specialCost")
        String specialCost;
        @SerializedName("standardCost")
        String standardCost;
        @SerializedName("lightCost")
        String lightCost;

        public String getSpecialCost() {
            return specialCost;
        }

        public String getStandardCost() {
            return standardCost;
        }

        public String getLightCost() {
            return lightCost;
        }
    }

    public InsuranceResult getResult() {
        return result;
    }
}
