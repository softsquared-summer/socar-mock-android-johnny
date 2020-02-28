package com.softsquared.template.src.task.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class SocarzoneResponse {

    @SerializedName("result")
    public SocarzoneResult socarzoneResult;
    @SerializedName("isSuccess")
    public boolean isSuccess;
    @SerializedName("code")
    public int code;
    @SerializedName("message")
    public String message;

    public SocarzoneResult getSocarzoneResult() {
        return socarzoneResult;
    }

    public class SocarzoneResult {
        @SerializedName("useTime")
        public String useTime;
        @SerializedName("socarzone")
        public ArrayList<SocarzoneInfo> socarzone;
        public String getUseTime() {
            return useTime;
        }

        public ArrayList<SocarzoneInfo> getSocarzone() {
            //Log.d("api catch", "The response is : " + socarzone);
            return socarzone;
        }
    }
}