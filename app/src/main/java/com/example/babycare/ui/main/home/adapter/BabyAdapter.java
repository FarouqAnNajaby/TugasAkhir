package com.example.babycare.ui.main.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babycare.api.ApiClient;
import com.example.babycare.databinding.ItemBabyBinding;
import com.example.babycare.model.Baby;
import com.example.babycare.model.ResponseDiagnosa;
import com.example.babycare.ui.main.history.adapter.GejalaAdapter;
import com.example.babycare.ui.main.history.adapter.HistoryAdapter;
import com.example.babycare.ui.main.history.adapter.SolusiAdapter;
import com.example.babycare.ui.main.home.hasil.HasilActvity;
import com.example.babycare.ui.main.home.question.QuestionActivity;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BabyAdapter extends RecyclerView.Adapter<BabyAdapter.MyViewHolder> {

    private Context context;
    private List<Baby> babyList;
    SweetAlertDialog dialog;

    public BabyAdapter(Context context, List<Baby> babyList) {
        this.context = context;
        this.babyList = babyList;
    }

    @NonNull
    @Override
    public BabyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBabyBinding binding = ItemBabyBinding.inflate(LayoutInflater.from(context), parent,
                false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BabyAdapter.MyViewHolder holder, int position) {
        try {
            Baby data = babyList.get(position);
            holder.binding.tvNama.setText(data.getNameBaby());
            holder.binding.tvUmur.setText(data.getAge());
            holder.itemView.setOnClickListener(v->{
                if (data.getAge().equalsIgnoreCase("0 years, 1 months") ||
                        data.getAge().equalsIgnoreCase("0 years, 2 months")){
                    dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
                    dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    dialog.setTitleText("Bahaya");
                    dialog.setContentText("Jika balita dengan umur kurang dari 3 bulan segera rujuk ke dokter atau rumah saki terdekat");
                    dialog.setCancelable(false);
                    dialog.setConfirmClickListener(sweetAlertDialog -> dialog.dismiss());
                    dialog.show();
                }else {
                    Intent intent = new Intent(context, QuestionActivity.class);
                    intent.putExtra(QuestionActivity.ID_BABY, data.getIdBaby());
                    context.startActivity(intent);
                }
            });
            holder.binding.btnDelete.setOnClickListener(v->{
                dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
                dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                dialog.setTitleText("Apakah anda yakin?");
                dialog.setConfirmClickListener(sweetAlertDialog -> delete(data.getIdBaby()));
                dialog.setCancelable(false);
                dialog.show();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return babyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemBabyBinding binding;

        public MyViewHolder(@NonNull ItemBabyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    private void delete(String id){

        Map<String, String> data = new HashMap<>();
        data.put("id_baby",id);

        Call<ResponseDiagnosa> getChildren = ApiClient.getApiServiceLaravel().toDeleteBaby(data);

        getChildren.enqueue(new Callback<ResponseDiagnosa>() {
            @Override
            public void onResponse(Call<ResponseDiagnosa> call, Response<ResponseDiagnosa> response) {
                try {
                    if (response.body() != null){
                        if (response.body().getStatusCode() == 1) {
                            FancyToast.makeText(context,"Sukses",
                                    FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false);
                        }
                    }
                    else {
                        FancyToast.makeText(context,"Gagal!",
                                FancyToast.LENGTH_LONG,FancyToast.ERROR,false);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    FancyToast.makeText(context,"Gagal mengambil data!",
                            FancyToast.LENGTH_LONG,FancyToast.ERROR,false);
                }
            }

            @Override
            public void onFailure(Call<ResponseDiagnosa> call, Throwable t) {
                FancyToast.makeText(context, "Gagal Menghubungi Server | "
                                +t.getMessage(), FancyToast.LENGTH_LONG, FancyToast.WARNING,
                        false).show();
                Log.d("error", "AddBaby : "+t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
