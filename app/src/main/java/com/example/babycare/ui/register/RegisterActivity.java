package com.example.babycare.ui.register;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.example.babycare.R;
import com.example.babycare.databinding.ActivityRegisterBinding;
import com.example.babycare.model.UserResponse;
import com.example.babycare.ui.login.LoginActivity;
import com.example.babycare.utils.Loading;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegisterPresenter.View {

    ActivityRegisterBinding binding;
    boolean isImage = false;
    Bitmap bitmap;
    Loading loading;
    Intent intent;
    String email, nama, password, photo;
    RegisterPresenter presenter;
    private final String FIELD_REQUIRED = "Field tidak boleh kosong";
    private final String FIELD_IS_NOT_VALID = "Email tidak valid";

    ActivityResultLauncher<Intent> launchImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();
                    if (intent != null){
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(
                                    getContentResolver(),intent.getData()
                            );
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                            byte[] byteArray = byteArrayOutputStream .toByteArray();
                            photo = Base64.encodeToString(byteArray, Base64.DEFAULT);
                            isImage = true;
                            binding.imgProfile.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle(getResources().getString(R.string.title_register));

        presenter = new RegisterPresenter(this, this);
        loading = new Loading(this);

        binding.btnLogin.setOnClickListener(this);
        binding.btnAdd.setOnClickListener(this);
        binding.btnDaftar.setOnClickListener(this);
        binding.ivPassword.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin){
//            loading.dialogLoading(getResources().getString(R.string.loading));
            goLogin();
        }else if (v.getId() == R.id.iv_password){
            switchPassword();
        }else if (v.getId() == R.id.btnDaftar){
            clickRegister();
        }else if (v.getId() == R.id.btn_add){
            gotoGalery();
        }
    }

    private boolean isValidEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void goLogin(){
        intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoGalery(){
        intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        launchImage.launch(intent);
    }

    private void clickRegister() {

        email = binding.edtEmail.getText().toString();
        password = binding.etPassword.getText().toString();
        nama = binding.edtName.getText().toString();

        if (!isValidEmail(email)) {
            binding.edtEmail.setError(FIELD_IS_NOT_VALID);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            binding.etPassword.setError(FIELD_REQUIRED);
            return;
        }
//        if (Objects.equals(photo, "") || photo == null) {
//            loading.dialogWarning("Register","Harap pilih foto");
//            return;
//        }
        Log.i("cek", "register: "+email+" "+password+" "+nama+" "+photo);
        presenter.onRegister(email,nama,password);
    }

    @Override
    public void register(Response<UserResponse> response) {
        if (response.body() != null) {
            Log.i("cekKode", String.valueOf(response.body().getKode()));
            switch(response.body().getKode()) {
                case 0:
                    loading.dialogError("Register","Gagal");
                    break;
                case 1:
                    loading.dialogSuccess("Register", "Sukses");
                    goLogin();
                    break;
            }
        }
    }

}