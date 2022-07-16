package com.example.babycare.ui.main.home.hasil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.babycare.R;
import com.example.babycare.api.ApiClient;
import com.example.babycare.databinding.ActivityHasilActvityBinding;
import com.example.babycare.model.DataDiagnosa;
import com.example.babycare.model.ResponseDiagnosa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilActvity extends AppCompatActivity {

    ActivityHasilActvityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHasilActvityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onProcessDiagnosa();

    }

    public void onProcessDiagnosa(){

        Call<ResponseDiagnosa> login = ApiClient.getApiServiceLaravel().toProcessDiagnosa();

        login.enqueue(new Callback<ResponseDiagnosa>() {
            @Override
            public void onResponse(Call<ResponseDiagnosa> call, Response<ResponseDiagnosa> response) {
                Log.i("error", "onResponse: "+response.message());
            }

            @Override
            public void onFailure(Call<ResponseDiagnosa> call, Throwable t) {
                Log.i("error", "onFailure: "+t.getMessage());
                t.printStackTrace();
            }
        });
    }
}