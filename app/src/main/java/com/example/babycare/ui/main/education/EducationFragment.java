package com.example.babycare.ui.main.education;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babycare.databinding.FragmentEducationBinding;
import com.example.babycare.ui.main.education.adapter.EducationAdapter;
import com.example.babycare.ui.main.home.hasil.HasilActvity;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class EducationFragment extends Fragment {

    private FragmentEducationBinding binding;
    SweetAlertDialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentEducationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dialog = new SweetAlertDialog(requireContext(), SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#4283F5"));
        dialog.setTitleText("Loading");
        dialog.setCancelable(false);
        dialog.show();
        binding.rvYoutube.setHasFixedSize(true);
        binding.rvYoutube.setVisibility(View.GONE);

        try {
            new Handler().postDelayed(() -> {
                binding.rvYoutube.setVisibility(View.VISIBLE);
                String[] videoIds = {"O2c7HqIa9ck",
                        "MYX6DNgsMTs","2aYa9KcCMWY","CFuNH1MNQiE","JZllaFvwjmI"};
                RecyclerView.Adapter recyclerViewAdapter = new EducationAdapter(videoIds, this.getLifecycle());
                binding.rvYoutube.setAdapter(recyclerViewAdapter);
                dialog.dismiss();
            }, 5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}