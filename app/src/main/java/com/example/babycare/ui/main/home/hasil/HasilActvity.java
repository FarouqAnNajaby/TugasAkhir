package com.example.babycare.ui.main.home.hasil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.babycare.R;
import com.example.babycare.databinding.ActivityHasilActvityBinding;

public class HasilActvity extends AppCompatActivity {

    ActivityHasilActvityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHasilActvityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}