package com.example.babycare.ui.main.home.hasil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.babycare.R;
import com.example.babycare.api.ApiClient;
import com.example.babycare.databinding.ActivityHasilActvityBinding;
import com.example.babycare.model.DataDiagnosa;
import com.example.babycare.model.ResponseDiagnosa;
import com.example.babycare.ui.main.history.adapter.GejalaAdapter;
import com.example.babycare.ui.main.history.adapter.HistoryAdapter;
import com.example.babycare.ui.main.history.adapter.SolusiAdapter;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilActvity extends AppCompatActivity{

    public static final String LABEL = "label";
    public static String DIAGNOSA = "id";
    ActivityHasilActvityBinding binding;
    GejalaAdapter adapter;
    SolusiAdapter solusiAdapter;
    Intent intent;
    List<DataDiagnosa> list;
    String label,id;
    SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHasilActvityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.hasil));

        intent = getIntent();
        label = getIntent().getStringExtra(LABEL);
        id = getIntent().getStringExtra(DIAGNOSA);

        dialog = new SweetAlertDialog(HasilActvity.this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#4283F5"));
        dialog.setTitleText("Loading");
        dialog.setCancelable(false);

        if (!label.equals("history")){
            getDiagnosa();
        }else {
            fetchData();
        }

    }

    private void fetchData(){

        binding.containerLayout.setVisibility(View.GONE);
        dialog.show();

        Map<String, String> data = new HashMap<>();
        data.put("uuid",id);

        Call<ResponseDiagnosa> getChildren = ApiClient.getApiServiceLaravel().toDetilHistory(data);

        getChildren.enqueue(new Callback<ResponseDiagnosa>() {
            @Override
            public void onResponse(Call<ResponseDiagnosa> call, Response<ResponseDiagnosa> response) {
                try {
                    if (response.body().getStatusCode() == 1){
                        dialog.dismiss();
                        if (response.body().getData() != null){
                            binding.containerLayout.setVisibility(View.VISIBLE);
                            binding.tvNameBaby.setText(response.body().getData().get(0).getNameBaby());
                            binding.tvPenyakit.setText(response.body().getData().get(0).getPenyakit());
                            binding.tvAgeBaby.setText(response.body().getData().get(0).getAge());
                            binding.tvDate.setText(response.body().getData().get(0).getDate());
                            binding.tvSexBaby.setText(response.body().getData().get(0).getSexBaby());

                            ArrayList<String> listGejala = new ArrayList<>();
                            for (String z : response.body().getData().get(0).getGejala()){
                                listGejala.add(z);
                            }
                            ArrayList<String> listSolusi = new ArrayList<>();
                            for (String y : response.body().getData().get(0).getSolution()){
                                listSolusi.add(y);
                            }
                            adapter = new GejalaAdapter(HasilActvity.this, listGejala);
                            binding.rvGejala.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                            solusiAdapter = new SolusiAdapter(HasilActvity.this, listSolusi);
                            binding.rvSolusi.setAdapter(solusiAdapter);
                            solusiAdapter.notifyDataSetChanged();
                        }
                        else {
                            dialog.dismiss();
                            FancyToast.makeText(HasilActvity.this,"Gagal mengambil data!",
                                    FancyToast.LENGTH_LONG,FancyToast.ERROR,false);
                        }
                    }else {
                        dialog.dismiss();
                        binding.layoutEmpty.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    FancyToast.makeText(HasilActvity.this,"Gagal mengambil data!",
                            FancyToast.LENGTH_LONG,FancyToast.ERROR,false);
                }
            }

            @Override
            public void onFailure(Call<ResponseDiagnosa> call, Throwable t) {
                FancyToast.makeText(HasilActvity.this, "Gagal Menghubungi Server | "
                                +t.getMessage(), FancyToast.LENGTH_LONG, FancyToast.WARNING,
                        false).show();
                Log.d("error", "AddBaby : "+t.getMessage());
                t.printStackTrace();
            }
        });
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
                    if (response.body() != null){
                        if (response.body().getStatusCode() == 1){
                            if (response.body().getData() != null){
                                binding.containerLayout.setVisibility(View.VISIBLE);
                                binding.tvNameBaby.setText(response.body().getData().get(0).getNameBaby());
                                binding.tvDate.setText(response.body().getDate());
                                binding.tvAgeBaby.setText(response.body().getData().get(0).getAge());
                                binding.tvPenyakit.setText(response.body().getData().get(0).getPenyakit());
                                binding.tvSexBaby.setText(response.body().getData().get(0).getSexBaby());

                                ArrayList<String> listGejala = new ArrayList<>();
                                for (String a : response.body().getData().get(0).getGejala()){
                                    listGejala.add(a);
                                }
                                ArrayList<String> listSolusi = new ArrayList<>();
                                for (String s : response.body().getData().get(0).getSolution()){
                                    listSolusi.add(s);
                                }
                                adapter = new GejalaAdapter(HasilActvity.this, listGejala);
                                binding.rvGejala.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

                                solusiAdapter = new SolusiAdapter(HasilActvity.this, listSolusi);
                                binding.rvSolusi.setAdapter(solusiAdapter);
                                solusiAdapter.notifyDataSetChanged();
                            }
                        }
                    }else {
                        binding.layoutEmpty.setVisibility(View.VISIBLE);
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