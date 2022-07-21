package com.example.babycare.ui.main.history;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.babycare.api.ApiClient;
import com.example.babycare.databinding.FragmentHistoryBinding;
import com.example.babycare.helper.DbHelper;
import com.example.babycare.model.DataDiagnosa;
import com.example.babycare.model.ResponseDiagnosa;
import com.example.babycare.model.ResponseGetBaby;
import com.example.babycare.ui.main.history.adapter.HistoryAdapter;
import com.example.babycare.ui.main.home.adapter.BabyAdapter;
import com.example.babycare.ui.main.home.baby.BabyActivity;
import com.example.babycare.ui.main.home.hasil.HasilActvity;
import com.example.babycare.utils.Constanta;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment {

    FragmentHistoryBinding binding;
    DbHelper helper;
    List<DataDiagnosa> list;
    HistoryAdapter adapter;
    SweetAlertDialog dialog;

    public HistoryFragment() {
    }

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        helper = new DbHelper(requireContext());

        dialog = new SweetAlertDialog(requireContext(), SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#4283F5"));
        dialog.setTitleText("Loading");
        dialog.setCancelable(false);
        dialog.show();

        getListHstory(helper.getString(Constanta.PREF_ID));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void getListHstory(String id) {

        dialog.show();
        binding.rvHistory.setVisibility(View.GONE);
        binding.layoutEmpty.setVisibility(View.GONE);

        Map<String, String> data = new HashMap<>();
        data.put("id_user",id);

        Call<ResponseDiagnosa> getChildren = ApiClient.getApiServiceLaravel().toGeListHistory(data);

        getChildren.enqueue(new Callback<ResponseDiagnosa>() {
            @Override
            public void onResponse(Call<ResponseDiagnosa> call, Response<ResponseDiagnosa> response) {
                try {
                    dialog.dismiss();
                    if (response.body() != null){
                        if (response.body().getStatusCode() == 1) {
                            if (response.body().getData() != null) {
                                binding.rvHistory.setVisibility(View.VISIBLE);
                                list = response.body().getData();
                                adapter = new HistoryAdapter(requireContext(), list);
                                binding.rvHistory.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                    else {
                        binding.layoutEmpty.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    Toast.makeText(requireContext(), "catch", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseDiagnosa> call, Throwable t) {
                dialog.dismiss();
                FancyToast.makeText(requireContext(), "Gagal Menghubungi Server | "
                                +t.getMessage(), FancyToast.LENGTH_LONG, FancyToast.WARNING,
                        false).show();
                Log.d("error", "AddBaby : "+t.getMessage());
                t.printStackTrace();
            }
        });
    }

}