package com.softsquared.template.src.main.interfaces;

import com.softsquared.template.src.main.models.DefaultResponse;
import com.softsquared.template.src.main.models.SignUpInfo;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainRetrofitInterface {
//    @GET("/test")
    @GET("/jwt")
    Call<DefaultResponse> getTest();

    @GET("/test/{number}")
    Call<DefaultResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

    @POST("/test")
    Call<DefaultResponse> postTest(@Body RequestBody params);

    //쏘카회원가입
    @POST("/user")
    Call<DefaultResponse> signUp(@Body SignUpInfo signUpInfo);
}
