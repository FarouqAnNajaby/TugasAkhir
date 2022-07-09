package com.example.babycare.ui.login;

import android.content.Context;
import android.util.Log;

import com.example.babycare.api.ApiClient;
import com.example.babycare.model.ResponseLogin;
import com.shashank.sony.fancytoastlib.FancyToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    private static final String TAG = "LoginPresenter";

    Context context;
    View view;

    public LoginPresenter(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public interface View {
        void login(Response<ResponseLogin> response);
    }

    public void onlogin(String email, String password){

        Call<ResponseLogin> login = ApiClient.getApiService().toLogin(email, password);

        login.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                try {
                    view.login(response);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                FancyToast.makeText(context, "Gagal Menghubungi Server | "
                                +t.getMessage(), FancyToast.LENGTH_LONG, FancyToast.WARNING,
                        false).show();
                Log.d("error", "Register : "+t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
