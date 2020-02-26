package com.softsquared.template.src.task.interfaces;

import com.softsquared.template.src.task.models.SocarzoneResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TaskRetrofitInterface {
    @GET("/socarzones")
    Call<SocarzoneResponse> getSocarzoneInfo();
}
