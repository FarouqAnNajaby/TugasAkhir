package com.example.babycare.ui.main.home.baby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.babycare.R;
import com.example.babycare.api.ApiClient;
import com.example.babycare.databinding.ActivityBabyBinding;
import com.example.babycare.helper.DbHelper;
import com.example.babycare.model.Baby;
import com.example.babycare.model.ResponseGetBaby;
import com.example.babycare.ui.main.home.adapter.BabyAdapter;
import com.example.babycare.ui.main.home.baby.presenter.BabyPresenter;
import com.example.babycare.ui.main.home.hasil.HasilActvity;
import com.example.babycare.utils.Constanta;
import com.example.babycare.utils.Loading;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BabyActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityBabyBinding binding;
    SweetAlertDialog dialog;
    BabyAdapter adapter;
    DbHelper helper;
    List<Baby> babyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBabyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.list_baby));

        dialog = new SweetAlertDialog(BabyActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#4283F5"));
        dialog.setTitleText("Loading");
        dialog.setCancelable(false);

        helper = new DbHelper(this);

        binding.fab.setOnClickListener(this);
        showInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab){
            gotoAdd();
        }
    }

    private void gotoAdd(){
        Intent intent = new Intent(this, AddBabyActivity.class);
        startActivity(intent);
    }

    private void getListBaby() {

        dialog.show();
        binding.rvListBaby.setVisibility(View.GONE);
        binding.layoutEmpty.setVisibility(View.GONE);

        Call<ResponseGetBaby> getChildren = ApiClient.getApiService().toGetBaby(helper.getString(Constanta.PREF_ID));

        getChildren.enqueue(new Callback<ResponseGetBaby>() {
            @Override
            public void onResponse(Call<ResponseGetBaby> call, Response<ResponseGetBaby> response) {
                try {
                    dialog.dismiss();
                    if (response.body().getKode() == 1){
                        if (response.body().getData() != null){
                            binding.rvListBaby.setVisibility(View.VISIBLE);
                            babyList = response.body().getData();
                            Log.i("cek", String.valueOf(babyList.size()));
                            adapter = new BabyAdapter(BabyActivity.this, babyList);
                            binding.rvListBaby.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                        else {
                            dialog.dismiss();
                            FancyToast.makeText(BabyActivity.this,"Gagal mengambil data!",
                                    FancyToast.LENGTH_LONG,FancyToast.ERROR,false);
                        }
                    }else {
                        dialog.dismiss();
                        binding.layoutEmpty.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetBaby> call, Throwable t) {
                dialog.dismiss();
                FancyToast.makeText(BabyActivity.this, "Gagal Menghubungi Server | "
                                +t.getMessage(), FancyToast.LENGTH_LONG, FancyToast.WARNING,
                        false).show();
                Log.d("error", "AddBaby : "+t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void showInfo(){
        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText("Info");
        dialog.setContentText("Harap pilih balita yang akan anda diagnosa");
        dialog.setCancelable(false);
        dialog.setConfirmClickListener(v->{
            dialog.dismiss();
            getListBaby();
        });
        dialog.show();
    }

}