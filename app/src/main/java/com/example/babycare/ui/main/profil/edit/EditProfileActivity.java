package com.example.babycare.ui.main.profil.edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.babycare.R;
import com.example.babycare.databinding.ActivityEditProfileBinding;
import com.example.babycare.helper.DbHelper;
import com.example.babycare.model.ResponseLogin;
import com.example.babycare.ui.main.profil.edit.presenter.EditProfilPresenter;
import com.example.babycare.utils.Constanta;
import com.example.babycare.utils.Loading;

import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener,
    EditProfilPresenter.View{

    ActivityEditProfileBinding binding;
    DbHelper helper;
    EditProfilPresenter presenter;
    Loading loading;
    private final String FIELD_REQUIRED = "Field tidak boleh kosong";
    String nama,email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_ubah_profile));

        helper = new DbHelper(this);
        loading = new Loading(this);
        presenter = new EditProfilPresenter(this,this);
        binding.btnUpdate.setOnClickListener(this);
        binding.ivPassword.setOnClickListener(this);

        setView();
    }

    private void setView() {
        binding.edtEmail.setText(helper.getString(Constanta.PREF_EMAIL));
        binding.edtName.setText(helper.getString(Constanta.PREF_NAME));
        binding.etPassword.setText(helper.getString(Constanta.PREF_PASS));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        if (v.getId() == R.id.btn_update){
            updateData();
        }else if (v.getId() == R.id.iv_password){
            switchPassword();
        }
    }

    public void updateUser(Response<ResponseLogin> response) {
        if (response.body() != null) {
            switch(response.body().getKode()) {
                case 0:
                    loading.dialogError("Ubah Profile","Tidak ada day");
                    break;
                case 1:
                    loading.dialogSuccess("Ubah Profile", "Sukses");;
                    saveUserID(
                            response.body().getData().getIdUser(),
                            response.body().getData().getNameUser(),
                            response.body().getData().getEmailUser(),
                            response.body().getData().getPasswordUser()
                    );
                    break;
                default:
                    loading.dialogError("Ubah Profile","Terjadi kesalahan dengan sistem");
                    Toast.makeText(getBaseContext(), "Terjadi kesalahan dengan sistemr",
                            Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveUserID(String id, String name_user, String email_user, String pass) {
        helper.put(Constanta.PREF_ID,id);
        helper.put(Constanta.PREF_NAME, name_user);
        helper.put(Constanta.PREF_EMAIL, email_user);
        helper.put(Constanta.PREF_PASS, pass);
    }

    private void updateData() {
        email = binding.edtEmail.getText().toString();
        pass = binding.etPassword.getText().toString();
        nama = binding.edtName.getText().toString();

        if (TextUtils.isEmpty(pass)) {
            binding.etPassword.setError(FIELD_REQUIRED);
            return;
        }
        presenter.onUpdate(nama,email,pass);
    }
}