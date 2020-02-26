package com.softsquared.template.src.task.models;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.util.ArrayList;

public class SocarzoneResponse {
    @SerializedName("result") public SocarzoneResult socarzoneResult;
    public SocarzoneResult getSocarzoneResult() { return socarzoneResult; }

    public class SocarzoneResult{
        @SerializedName("useTime") public Time useTime;
        @SerializedName("socarzone") public ArrayList<SocarzoneInfo> socarzone;

        public Time getUseTime() { return useTime; }
        public ArrayList<SocarzoneInfo> getSocarzone() {
            Log.d("api catch","The response is : "+socarzone);
            return socarzone; }
    }
}
