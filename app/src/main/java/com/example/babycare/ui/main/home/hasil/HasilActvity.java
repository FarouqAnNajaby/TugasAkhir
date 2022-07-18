package com.example.babycare.ui.main.home.hasil;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.babycare.api.ApiClient;
import com.example.babycare.databinding.ActivityHasilActvityBinding;
import com.example.babycare.model.DataDiagnosa;
import com.example.babycare.model.ResponseDiagnosa;
import com.example.babycare.ui.main.history.adapter.GejalaAdapter;
import com.example.babycare.ui.main.history.adapter.SolusiAdapter;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilActvity extends AppCompatActivity{

    ActivityHasilActvityBinding binding;
    SweetAlertDialog dialog;
    GejalaAdapter adapter;
    SolusiAdapter solusiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHasilActvityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getDiagnosa();

    }

    private void getDiagnosa() {


        binding.progressbar.setVisibility(View.VISIBLE);
        binding.containerLayout.setVisibility(View.GONE);

        Call<ResponseDiagnosa> getChildren = ApiClient.getApiServiceLaravel().toProcessDiagnosa();

        getChildren.enqueue(new Callback<ResponseDiagnosa>() {
            @Override
            public void onResponse(Call<ResponseDiagnosa> call, Response<ResponseDiagnosa> response) {
                try {
                    binding.progressbar.setVisibility(View.GONE);
                    if (response.body().getStatusCode() == 1){
                        if (response.body().getData() != null){
                            binding.containerLayout.setVisibility(View.VISIBLE);
                            binding.tvNameBaby.setText(response.body().getData().get(0).getIdBaby());
                            binding.tvPenyakit.setText(response.body().getData().get(0).getPenyakit());
                            ArrayList<String> listGejala = new ArrayList<>();
                            for (String s : response.body().getData().get(0).getGejala()){
                                listGejala.add(s);
                            }
                            ArrayList<String> listSolusi = new ArrayList<>();
                            for (String s : response.body().getData().get(0).getSolusi()){
                                listSolusi.add(s);
                            }
                            adapter = new GejalaAdapter(HasilActvity.this, listGejala);
                            binding.rvGejala.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                            solusiAdapter = new SolusiAdapter(HasilActvity.this, listSolusi);
                            binding.rvSolusi.setAdapter(solusiAdapter);
                            solusiAdapter.notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(HasilActvity.this, "gagal", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(HasilActvity.this, "gagal", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseDiagnosa> call, Throwable t) {
                binding.progressbar.setVisibility(View.GONE);
                FancyToast.makeText(HasilActvity.this, "Gagal Menghubungi Server | "
                                +t.getMessage(), FancyToast.LENGTH_LONG, FancyToast.WARNING,
                        false).show();
                Log.d("error", "Hasil Diagnosa : "+t.getMessage());
                t.printStackTrace();
            }
        });
    }
}