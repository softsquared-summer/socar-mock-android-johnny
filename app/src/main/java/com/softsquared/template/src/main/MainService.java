package com.softsquared.template.src.main;

import com.softsquared.template.src.main.interfaces.MainRetrofitInterface;
import com.softsquared.template.src.main.models.DefaultResponse;
import com.softsquared.template.src.main.interfaces.MainActivityView;
import com.softsquared.template.src.main.models.SignUpInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.getRetrofit;

class MainService {
    private final MainActivityView mMainActivityView;
    SignUpInfo signUpInfo;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    void getTest() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                mMainActivityView.validateSuccess(defaultResponse.getMessage());
            }
            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }

    void sigUp(){
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);

        mainRetrofitInterface.signUp(signUpInfo).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }
}
