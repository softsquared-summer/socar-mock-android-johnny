package com.softsquared.template.src.task;

import android.util.Log;

import com.softsquared.template.src.task.interfaces.BasicTaskActivityView;
import com.softsquared.template.src.task.interfaces.TaskRetrofitInterface;
import com.softsquared.template.src.task.models.SocarzoneResponse;

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

                if (socarzoneResponse == null) {
                    mBasicTaskActivityView.validateFailure(null);
                    return;
                }
                //Log.d("jooan debugging","response is : "+socarzoneResponse.getSocarzoneResult().getSocarzone());
                mBasicTaskActivityView.validateSuccess(socarzoneResponse.getSocarzoneResult().getSocarzone());
            }
            @Override
            public void onFailure(Call<SocarzoneResponse> call, Throwable t) {
                mBasicTaskActivityView.validateFailure(null);
            }
        });
    }
}
