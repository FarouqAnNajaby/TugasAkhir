package com.example.babycare.ui.main.home.baby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.babycare.R;
import com.example.babycare.api.ApiClient;
import com.example.babycare.databinding.ActivityBabyBinding;
import com.example.babycare.helper.DbHelper;
import com.example.babycare.model.Baby;
import com.example.babycare.model.ResponseGetBaby;
import com.example.babycare.ui.main.home.adapter.BabyAdapter;
import com.example.babycare.ui.main.home.baby.presenter.BabyPresenter;
import com.example.babycare.utils.Constanta;
import com.example.babycare.utils.Loading;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BabyActivity extends AppCompatActivity implements View.OnClickListener
//        , BabyPresenter.View
{

    ActivityBabyBinding binding;
    BabyPresenter presenter;
    Loading loading;
    BabyAdapter adapter;
    DbHelper helper;
    List<Baby> babyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBabyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        presenter = new BabyPresenter(this,this);
        loading = new Loading(this);
        helper = new DbHelper(this);

        binding.fab.setOnClickListener(this);
//        presenter.onGet(helper.getString(Constanta.PREF_ID));
        showInfo();
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

//    @Override
//    public void addBaby(Response<UserResponse> response) {
//    }

//    @Override
//    public void getBaby(Response<ResponseGetBaby> response) {
//        if (response.body() != null) {
//            switch(response.body().getKode()) {
//                case 0:
//                    loading.dialogError("Oops..","Gagal Mengambil Data");
//                    break;
//                case 1:
//                    Log.i("cek", response.body().getData().toString());
//                    babyList.addAll(response.body().getData());
//                    adapter = new BabyAdapter(this, babyList);
//                    binding.rvListBaby.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
//                    break;
//                default:
//                    loading.dialogError("Login","Terdapat error pada sistem");
//                    Toast.makeText(getBaseContext(), "Terdapat error pada sistem",
//                            Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    private void getListBaby() {

        Call<ResponseGetBaby> getChildren = ApiClient.getApiService().toGetBaby(helper.getString(Constanta.PREF_ID));

        getChildren.enqueue(new Callback<ResponseGetBaby>() {
            @Override
            public void onResponse(Call<ResponseGetBaby> call, Response<ResponseGetBaby> response) {
                try {
                    if (response.body().getKode() == 1){
                        if (response.body().getData() != null){
                            babyList = response.body().getData();
                            Log.i("cek", String.valueOf(babyList.size()));
                            adapter = new BabyAdapter(BabyActivity.this, babyList);
                            binding.rvListBaby.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                        else {
                            loading.dialogError("Oops..",response.body().getPesan());
                        }
                    }else {
                        loading.dialogError("Oops..",response.body().getPesan());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetBaby> call, Throwable t) {
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