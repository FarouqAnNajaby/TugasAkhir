package com.example.babycare.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.babycare.R;
import com.example.babycare.databinding.ActivityLoginBinding;
import com.example.babycare.helper.DbHelper;
import com.example.babycare.model.ResponseLogin;
import com.example.babycare.ui.forgetpassword.ForgetPasswordActivity;
import com.example.babycare.ui.main.HomeActivity;
import com.example.babycare.ui.register.RegisterActivity;
import com.example.babycare.utils.Constanta;
import com.example.babycare.utils.Loading;

import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginPresenter.View {

    ActivityLoginBinding binding;
    private final String FIELD_REQUIRED = "Field tidak boleh kosong";
    private final String FIELD_IS_NOT_VALID = "Email tidak valid";
    String email,pass;
    Intent intent;
    DbHelper dbHelper;
    LoginPresenter presenter;
    Loading loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle(getResources().getString(R.string.title_login));

        presenter = new LoginPresenter(this,this);
        loading = new Loading(this);
        dbHelper = new DbHelper(this);
//        initLoading();
        binding.btnLogin.setOnClickListener(this);
        binding.btnRegister.setOnClickListener(this);
        binding.ivPassword.setOnClickListener(this);
        binding.forgotPassBtn.setOnClickListener(this);

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
    public void onClick(@NonNull View v) {
        if (v.getId() == R.id.btnRegister){
            goRegister();
        }else if (v.getId() == R.id.iv_password){
            switchPassword();
        }else if (v.getId() == R.id.btnLogin){
            toLogin();
        }else if (v.getId() == R.id.forgotPassBtn){
            goPassword();
        }
    }

    private void goRegister(){
        intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void goPassword(){
        intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }

    private void goHome(){
        intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isValidEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void toLogin() {

        email = binding.edtEmail.getText().toString();
        pass = binding.etPassword.getText().toString();

        if (!isValidEmail(email)) {
            binding.edtEmail.setError(FIELD_IS_NOT_VALID);
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            binding.etPassword.setError(FIELD_REQUIRED);
            return;
        }
        presenter.onlogin(email,pass);
    }

    @Override
    public void login(Response<ResponseLogin> response) {
        if (response.body() != null) {
            switch(response.body().getKode()) {
                case 0:
                    loading.dialogError("Login","Email atau Passsword Salah");
                    break;
                case 1:
                    dbHelper.put(Constanta.PREF_IS_LOGIN, true);
                    Log.i("cekPref", "login: "+dbHelper.getBoolean(Constanta.PREF_IS_LOGIN));
                    loading.dialogSuccess("Login", "Sukses");
                    goHome();
                    saveUserID(
                            response.body().getData().getIdUser(),
                            response.body().getData().getNameUser(),
                            response.body().getData().getEmailUser(),
                            response.body().getData().getPasswordUser()
                            );
                    break;
                default:
                    loading.dialogError("Login","Pengguna belum terdaftar");
                    Toast.makeText(getBaseContext(), "Pengguna belum terdaftar",
                            Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveUserID(String id, String name_user, String email_user, String pass) {
        dbHelper.put(Constanta.PREF_ID,id);
        dbHelper.put(Constanta.PREF_NAME, name_user);
        dbHelper.put(Constanta.PREF_EMAIL, email_user);
        dbHelper.put(Constanta.PREF_PASS, pass);
    }
}