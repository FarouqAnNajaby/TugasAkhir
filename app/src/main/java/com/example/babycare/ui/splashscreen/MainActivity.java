package com.example.babycare.ui.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.babycare.R;
import com.example.babycare.databinding.ActivityMainBinding;
import com.example.babycare.helper.DbHelper;
import com.example.babycare.ui.login.LoginActivity;
import com.example.babycare.ui.main.HomeActivity;
import com.example.babycare.utils.Constanta;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        helper = new DbHelper(this);

        try {
            new Handler().postDelayed(() -> {
                if (helper.getBoolean(Constanta.PREF_IS_LOGIN)){
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}