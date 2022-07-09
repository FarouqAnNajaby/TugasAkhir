package com.example.babycare.ui.forgetpassword;

import android.content.Context;
import android.util.Log;

import com.example.babycare.api.ApiClient;
import com.example.babycare.model.UserResponse;
import com.shashank.sony.fancytoastlib.FancyToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordPresenter {
    private static final String TAG = "ForgetPasswordPresenter";

    Context context;
    View view;

    public ForgetPasswordPresenter(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public interface View {
        void resetPassword(Response<UserResponse> response);
    }

    public void onReset(String email, String password){

        Call<UserResponse> resetPass = ApiClient.getApiService().toResetPass(email, password);

        resetPass.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                try {
                    view.resetPassword(response);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                FancyToast.makeText(context, "Gagal Menghubungi Server | "
                                +t.getMessage(), FancyToast.LENGTH_LONG, FancyToast.WARNING,
                        false).show();
                Log.d("error", "Register : "+t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
