package com.example.babycare.ui.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.babycare.R;
import com.example.babycare.databinding.FragmentHomeBinding;
import com.example.babycare.ui.login.LoginActivity;
import com.example.babycare.ui.main.home.baby.BabyActivity;
import com.example.babycare.ui.register.RegisterActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Intent intent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnStart.setOnClickListener(v->{
            showDialog();
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showDialog(){

        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_rules, null);
        Button btnStart = view.findViewById(R.id.btn_next);
        ImageView btnClose = view.findViewById(R.id.btn_close);

        BottomSheetDialog dialog = new BottomSheetDialog(requireContext(),R.style.AppBottomSheetDialogTheme);
        dialog.setContentView(view);
        dialog.show();

        btnClose.setOnClickListener(v->{
            dialog.dismiss();
        });

        btnStart.setOnClickListener(v->{
            intent = new Intent(requireContext(), BabyActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        
    }
}