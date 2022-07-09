package com.example.babycare.ui.register;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.babycare.api.ApiClient;
import com.example.babycare.model.UserResponse;
import com.shashank.sony.fancytoastlib.FancyToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    private static final String TAG = "RegisterPresenter";

    Context context;
    View view;

    public RegisterPresenter(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    interface View{
        void register(Response<UserResponse> response);
    }

    public void onRegister(String email, String name, String password){
        Call<UserResponse> simpanRegisterData =
                ApiClient.getApiService().toRegist(email, name, password);

        simpanRegisterData.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                try {
                    view.register(response);
                    Log.i(TAG, "cekResponse: "+response.body().getKode());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                FancyToast.makeText(context, "Gagal Menghubungi Server | "
                                +t.getMessage(), FancyToast.LENGTH_LONG, FancyToast.WARNING,
                        false).show();
                Log.d("eror", "Register : "+t.getMessage());
                t.printStackTrace();
            }
        });
    }

}
