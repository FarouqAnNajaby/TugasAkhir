package com.example.babycare.ui.forgetpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.babycare.R;
import com.example.babycare.databinding.ActivityForgetPasswordBinding;
import com.example.babycare.model.UserResponse;
import com.example.babycare.utils.Constanta;
import com.example.babycare.utils.Loading;

import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity implements
        ForgetPasswordPresenter.View, View.OnClickListener {

    ActivityForgetPasswordBinding binding;
    Loading loading;
    String pass, pass1, email;
    private final String FIELD_REQUIRED = "Field tidak boleh kosong";
    ForgetPasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loading = new Loading(this);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_ubah_sandi));
        presenter = new ForgetPasswordPresenter(this,this);
        binding.btnReset.setOnClickListener(this);
        binding.ivPassword.setOnClickListener(this);
        binding.ivPassword1.setOnClickListener(this);
    }


    private void switchPassword() {
        if (binding.ivPassword.getTag().toString().equals("hidden")) {
            binding.ivPassword.setTag("show");
            binding.ivPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_show_password));
            binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            binding.ivPassword.setTag("hidden");
            binding.ivPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_hide_password));
            binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    private void switchPassword1() {
        if (binding.ivPassword1.getTag().toString().equals("hidden")) {
            binding.ivPassword1.setTag("show");
            binding.ivPassword1.setImageDrawable(getResources().getDrawable(R.drawable.ic_show_password));
            binding.etPassword1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            binding.ivPassword1.setTag("hidden");
            binding.ivPassword1.setImageDrawable(getResources().getDrawable(R.drawable.ic_hide_password));
            binding.etPassword1.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    @Override
    public void resetPassword(Response<UserResponse> response) {
        if (response.body() != null) {
            if (response.body().getKode() == 1) {
                loading.dialogSuccess("Reset Password", "Sukses");
                clear();
            } else {
                loading.dialogError("Reset Password", "Gagal");
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == android.R.id.home){
            finish();
        }else if (v.getId() == R.id.btnReset){
            reset();
        }else if (v.getId() == R.id.iv_password){
            switchPassword();
        }else if (v.getId() == R.id.iv_password1){
            switchPassword1();
        }
    }

    private void reset() {

        email = binding.edtEmail.getText().toString();
        pass = binding.etPassword.getText().toString();
        pass1 = binding.etPassword1.getText().toString();

        if (TextUtils.isEmpty(pass)) {
            binding.etPassword.setError(FIELD_REQUIRED);
            return;
        }
        if (TextUtils.isEmpty(pass1)) {
            binding.etPassword1.setError(FIELD_REQUIRED);
            return;
        }

        if (!pass.equals(pass1)){
            loading.dialogError(getResources().getString(R.string.title_ubah_sandi),
                    getResources().getString(R.string.title_password_different));
        }else {
            presenter.onReset(email,pass);
        }
    }

    private void clear(){
        binding.edtEmail.setText("");
        binding.etPassword.setText("");
        binding.etPassword1.setText("");
    }
}