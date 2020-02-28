package com.softsquared.template.src.task.interfaces;

import com.softsquared.template.src.task.models.InsuranceResponse;
import com.softsquared.template.src.task.models.SocarResponse;
import com.softsquared.template.src.task.models.SocarzoneResponse;

import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TaskRetrofitInterface {
    @GET("/socarzones")
    Call<SocarzoneResponse> getSocarzoneInfo();

    @GET("/socarzone/{socarzoneNo}/cars")
    Call<SocarResponse> getSocarList(
            @Path("socarzoneNo") int socarzoneNo,
            @Query("startTime") final Timestamp startTime,
            @Query("endTime") final Timestamp endTime
    );

    @GET("/car/{carNo}/insurances")
    Call<InsuranceResponse> getInsurance(
            @Path("carNo") int carNo,
            @Query("startTime") final Timestamp startTime,
            @Query("endTime") final Timestamp endTime
    );
}
