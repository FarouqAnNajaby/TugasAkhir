package com.example.babycare.ui.main.home.baby.presenter;

import android.content.Context;
import android.util.Log;

import com.example.babycare.api.ApiClient;
import com.example.babycare.model.Baby;
import com.example.babycare.model.ResponseDiagnosa;
import com.example.babycare.model.ResponseGetBaby;
import com.example.babycare.model.ResponseLogin;
import com.example.babycare.model.UserResponse;
import com.example.babycare.ui.main.profil.edit.presenter.EditProfilPresenter;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BabyPresenter {

    private static final String TAG = "AddBabyPresenter";

    Context context;
    View view;

    public BabyPresenter(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public interface View {
        void addBaby(Response<UserResponse> response);
        void updateBaby(Response<UserResponse> response);
    }

    public void onAddBaby(Baby baby){

        Call<UserResponse> login = ApiClient.getApiServiceLaravel().toAddBaby(baby);

        login.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                try {
                    view.addBaby(response);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                FancyToast.makeText(context, "Gagal Menghubungi Server | "
                                +t.getMessage(), FancyToast.LENGTH_LONG, FancyToast.WARNING,
                        false).show();
                Log.d("error", "AddBaby : "+t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void onUpdateBaby(String id, Baby baby){

        Map<String, String> data = new HashMap<>();
        data.put("id_baby",id);

        Call<UserResponse> login = ApiClient.getApiServiceLaravel().toUpdateBaby(data,baby);

        login.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                try {
                    view.updateBaby(response);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                FancyToast.makeText(context, "Gagal Menghubungi Server | "
                                +t.getMessage(), FancyToast.LENGTH_LONG, FancyToast.WARNING,
                        false).show();
                Log.d("error", "AddBaby : "+t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
