package com.example.babycare.ui.main.profil.edit.presenter;

import android.content.Context;
import android.util.Log;

import com.example.babycare.api.ApiClient;
import com.example.babycare.model.ResponseLogin;
import com.example.babycare.model.ResponseUpdateProfile;
import com.shashank.sony.fancytoastlib.FancyToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilPresenter {

    private static final String TAG = "EditProfilPresenter";

    Context context;
    View view;

    public EditProfilPresenter(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public interface View {
        void updateUser(Response<ResponseUpdateProfile> response);
    }

    public void onUpdate(String name, String email, String password){

        Call<ResponseUpdateProfile> login = ApiClient.getApiService().toUpdateUser(name,email, password);

        login.enqueue(new Callback<ResponseUpdateProfile>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
                try {
                    view.updateUser(response);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateProfile> call, Throwable t) {
                FancyToast.makeText(context, "Gagal Menghubungi Server | "
                                +t.getMessage(), FancyToast.LENGTH_LONG, FancyToast.WARNING,
                        false).show();
                Log.d("error", "Register : "+t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
