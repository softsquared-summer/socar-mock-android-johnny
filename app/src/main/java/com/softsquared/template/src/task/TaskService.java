package com.softsquared.template.src.task;

import android.util.Log;

import com.softsquared.template.src.task.interfaces.BasicTaskActivityView;
import com.softsquared.template.src.task.interfaces.TaskRetrofitInterface;
import com.softsquared.template.src.task.models.InsuranceResponse;
import com.softsquared.template.src.task.models.SocarResponse;
import com.softsquared.template.src.task.models.SocarzoneResponse;

import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.getRetrofit;

public class TaskService {
    final BasicTaskActivityView mBasicTaskActivityView;

    TaskService(final BasicTaskActivityView basicTaskActivityView){
        this.mBasicTaskActivityView = basicTaskActivityView;
    }

    void getSocarzone(){
        final TaskRetrofitInterface taskRetrofitInterface = getRetrofit().create(TaskRetrofitInterface.class);

        taskRetrofitInterface.getSocarzoneInfo().enqueue(new Callback<SocarzoneResponse>() {
            @Override
            public void onResponse(Call<SocarzoneResponse> call, Response<SocarzoneResponse> response) {
                final SocarzoneResponse socarzoneResponse = response.body();
                if (socarzoneResponse.getSocarzoneResult() == null) {
                    mBasicTaskActivityView.validateSocarzoneFailure(null);
                    return;
                }
                mBasicTaskActivityView.validateSocarzoneSuccess(socarzoneResponse.getSocarzoneResult().getSocarzone());
            }
            @Override
            public void onFailure(Call<SocarzoneResponse> call, Throwable t) {
                mBasicTaskActivityView.validateSocarzoneFailure(null);
            }
        });
    }

    void getSocarList(int socarzoneNo, Timestamp startTime, Timestamp endTime){
        final TaskRetrofitInterface taskRetrofitInterface = getRetrofit().create(TaskRetrofitInterface.class);

        taskRetrofitInterface.getSocarList(socarzoneNo,startTime,endTime).enqueue(new Callback<SocarResponse>() {
            @Override
            public void onResponse(Call<SocarResponse> call, Response<SocarResponse> response) {
                final SocarResponse socarResponse = response.body();
                if(socarResponse.getSocarResult() == null){
                    mBasicTaskActivityView.validateSocarListFailure(null);
                }
                Log.d("jooan",socarResponse.toString());
                mBasicTaskActivityView.validateSocarListSuccess(socarResponse.getSocarResult().getSocarList(),socarResponse.getSocarResult().getSocarzoneAddress());
            }
            @Override
            public void onFailure(Call<SocarResponse> call, Throwable t) {
                mBasicTaskActivityView.validateSocarListFailure(null);
            }
        });
    }

    void getInsurance(int carNo, Timestamp startTime, Timestamp endTime){
        final TaskRetrofitInterface taskRetrofitInterface = getRetrofit().create(TaskRetrofitInterface.class);

        taskRetrofitInterface.getInsurance(carNo,startTime,endTime).enqueue(new Callback<InsuranceResponse>() {
            @Override
            public void onResponse(Call<InsuranceResponse> call, Response<InsuranceResponse> response) {
                final InsuranceResponse insuranceResponse = response.body();
                if(insuranceResponse.getResult()==null){
                    mBasicTaskActivityView.validateInsuranceFailure(null);
                }
                mBasicTaskActivityView.validateInsuranceSuccess(insuranceResponse);
            }
            @Override
            public void onFailure(Call<InsuranceResponse> call, Throwable t) {
                mBasicTaskActivityView.validateInsuranceFailure(null);
            }
        });
    }
}
