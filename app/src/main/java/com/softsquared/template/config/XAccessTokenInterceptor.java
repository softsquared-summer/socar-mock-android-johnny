package com.softsquared.template.config;

import androidx.annotation.NonNull;

import com.softsquared.template.src.ApplicationClass;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.softsquared.template.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.template.src.ApplicationClass.sSharedPreferences;

public class XAccessTokenInterceptor implements Interceptor {

    @Override
    @NonNull
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();

        final String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRlIjoiMjAyMC0wMi0yNiAxMzozNzo0NSIsImlkIjoia2toMDE5NjZAbmF2ZXIuY29tIiwicHciOiIkMnkkMTAkME9BTnpSZ01SV25nbDZ5MFN2ZktTLk9UVFFvWFZNR3lraDhYUUVreUswUFlFN1hENVJLdC4ifQ.bELbQdu-OPqy-78EZEfHDxqHncN-bL2fp1Ke5yueMhU");
        if (jwtToken != null) {
            builder.addHeader("x-access-token", jwtToken)
                    .build();
        }
        return chain.proceed(builder.build());
    }
}