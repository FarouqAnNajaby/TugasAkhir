package com.example.babycare.ui.main.history;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.babycare.api.ApiClient;
import com.example.babycare.model.ResponseDiagnosa;
import com.example.babycare.model.ResponseGetBaby;
import com.example.babycare.model.ResponseLogin;
import com.example.babycare.model.UserResponse;
import com.shashank.sony.fancytoastlib.FancyToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPresenter {

    private static final String TAG = "HistoryFragment";

    Context context;
    View view;

    public HistoryPresenter(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public interface View {
        void getListHistory(Response<ResponseDiagnosa> response);
        void getHistory(Response<ResponseDiagnosa> response);
    }

    public void getHistory(){

        Call<ResponseDiagnosa> login = ApiClient.getApiServiceLaravel().toProcessDiagnosa();

        login.enqueue(new Callback<ResponseDiagnosa>() {
            @Override
            public void onResponse(Call<ResponseDiagnosa> call, Response<ResponseDiagnosa> response) {
                try {
                    view.getHistory(response);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseDiagnosa> call, Throwable t) {
                FancyToast.makeText(context, "Gagal Menghubungi Server | "
                                +t.getMessage(), FancyToast.LENGTH_LONG, FancyToast.WARNING,
                        false).show();
                Log.d("error", "Register : "+t.getMessage());
                t.printStackTrace();
            }
        });
    }

}
