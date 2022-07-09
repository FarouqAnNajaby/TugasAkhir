package com.example.babycare.ui.main.home.baby.presenter;

import android.content.Context;
import android.util.Log;

import com.example.babycare.api.ApiClient;
import com.example.babycare.model.ResponseGetBaby;
import com.example.babycare.model.ResponseLogin;
import com.example.babycare.model.UserResponse;
import com.example.babycare.ui.main.profil.edit.presenter.EditProfilPresenter;
import com.shashank.sony.fancytoastlib.FancyToast;

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
        void getBaby(Response<ResponseGetBaby> response);
    }

    public void onUpdate(String name, String tanggal, String sex, String id){

        Call<UserResponse> login = ApiClient.getApiService().toAddBaby(name,tanggal, sex, id);

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

    public void onGet(String id){

        Call<ResponseGetBaby> getChildren = ApiClient.getApiService().toGetBaby(id);

        getChildren.enqueue(new Callback<ResponseGetBaby>() {
            @Override
            public void onResponse(Call<ResponseGetBaby> call, Response<ResponseGetBaby> response) {
                try {
                    view.getBaby(response);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetBaby> call, Throwable t) {
                FancyToast.makeText(context, "Gagal Menghubungi Server | "
                                +t.getMessage(), FancyToast.LENGTH_LONG, FancyToast.WARNING,
                        false).show();
                Log.d("error", "AddBaby : "+t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
