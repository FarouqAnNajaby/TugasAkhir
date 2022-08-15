package com.example.babycare.ui.main.home.baby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import com.example.babycare.R;
import com.example.babycare.databinding.ActivityAddBabyBinding;
import com.example.babycare.helper.DbHelper;
import com.example.babycare.model.Baby;
import com.example.babycare.model.ResponseGetBaby;
import com.example.babycare.model.UserResponse;
import com.example.babycare.ui.main.home.baby.presenter.BabyPresenter;
import com.example.babycare.utils.Constanta;
import com.example.babycare.utils.Loading;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Response;

public class AddUpdateBabyActivity extends AppCompatActivity implements View.OnClickListener,
        BabyPresenter.View {

    public static final String EXTRA_BABY = "extra_baby";
    public static final String LABEL = "extra_label";
    ActivityAddBabyBinding binding;
    DatePickerDialog datePickerDialog;
    Loading loading;
    DbHelper dbHelper;
    Date date = null;
    Baby baby;
    SweetAlertDialog dialog;
    String idUser, name, tanggal, kelamin, label, idBaby;
    BabyPresenter presenter;
    private final String FIELD_REQUIRED = "Field tidak boleh kosong";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBabyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loading = new Loading(this);
        dbHelper = new DbHelper(this);
        presenter = new BabyPresenter(this,this);

        idUser = dbHelper.getString(Constanta.PREF_ID);

        getSupportActionBar().setTitle(getResources().getString(R.string.title_tambah_baby));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        label = getIntent().getStringExtra(LABEL);

        if (label.equalsIgnoreCase("edit")){
            fetchDataBaby();
        }

        kelamin = getResources().getString(R.string.title_laki);

        binding.etTglLahir.setOnClickListener(this);
        binding.btnAdd.setOnClickListener(this);

    }

    private void fetchDataBaby() {
        Baby data = getIntent().getParcelableExtra(EXTRA_BABY);
        binding.btnAdd.setText("Ubah data");
        idBaby = data.getIdBaby();
        binding.edtNameBaby.setText(data.getNameBaby());
        binding.etTglLahir.setText(data.getDateBirthBaby());
        if (data.getSexBaby().equalsIgnoreCase("laki - laki")){
            binding.rbLaki.setChecked(true);
            binding.rbPerempuan.setChecked(false);
        }else {
            binding.rbPerempuan.setChecked(true);
            binding.rbLaki.setChecked(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void tampilTanggalLahir(){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(AddUpdateBabyActivity.this,
                (view1, year, monthOfYear, dayOfMonth) -> {
                    String hari,bulan,tahun;

                    monthOfYear += 1;

                    if (monthOfYear > 9){
                        bulan = String.valueOf(monthOfYear);
                    }else {
                        bulan = "0" + String.valueOf(monthOfYear);
                    }

                    if (dayOfMonth > 9){
                        hari = String.valueOf(dayOfMonth);
                    }else {
                        hari = "0" + String.valueOf(dayOfMonth);
                    }
                    tahun = String.valueOf(year);
                    String formattt = tahun + "-" + bulan + "-" + hari;
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        date = df.parse(formattt);
                        String tglLahir = dateFormat.format(date);
                        binding.etTglLahir.setText(tglLahir);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.etTglLahir){
            tampilTanggalLahir();
        }else if (v.getId() == R.id.btn_add){
            if (label.equalsIgnoreCase("edit")){
                updateDataBaby();
            }else {
                addNewBaby();
            }
        }
    }

    @Override
    public void addBaby(Response<UserResponse> response) {
        if (response.body() != null) {
            if (response.body().getMessage().contains("Sucessfully")){
                dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
                dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                dialog.setTitleText("Tambah Bayi");
                dialog.setContentText("Sukses");
                dialog.setCancelable(false);
                dialog.setConfirmClickListener(sweetAlertDialog -> {
                    goBaby();
                    dialog.dismiss();
                });
            }else {
                dialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
                dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                dialog.setTitleText("Tambah Bayi");
                dialog.setContentText("Gagal");
                dialog.setCancelable(false);
                dialog.setConfirmClickListener(sweetAlertDialog -> {
                    dialog.dismiss();
                });
            }
            dialog.show();
        }
    }

    @Override
    public void updateBaby(Response<UserResponse> response) {
        if (response.body() != null) {
            Log.i("cekResponse", "updateBaby: "+response.body().getMessage());
            if (response.body().getMessage().contains("Successfully")){
                dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
                dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                dialog.setTitleText("Ubah Bayi");
                dialog.setContentText("Sukses");
                dialog.setCancelable(false);
                dialog.setConfirmClickListener(sweetAlertDialog -> {
                    goBaby();
                    dialog.dismiss();
                });
            }else {
                dialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
                dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                dialog.setTitleText("Ubah Bayi");
                dialog.setContentText("Gagal");
                dialog.setCancelable(false);
                dialog.setConfirmClickListener(sweetAlertDialog -> {
                    dialog.dismiss();
                });
            }
            dialog.show();
        }
    }

    private void goBaby(){
        Intent intent = new Intent(this,BabyActivity.class);
        startActivity(intent);
        finish();
    }

    private void addNewBaby(){
        name = binding.edtNameBaby.getText().toString();
        tanggal = binding.etTglLahir.getText().toString();

        if (TextUtils.isEmpty(name)) {
            binding.edtNameBaby.setError(FIELD_REQUIRED);
            return;
        }

        if (Objects.equals(kelamin, "")){
            loading.dialogWarning("Tambah Bayi", "Harap pilih jenis kelamin");
        }

        if (TextUtils.isEmpty(tanggal)){
            loading.dialogWarning("Tambah Bayi", "Harap isi Tanggal Lahir");
            return;
        }
        baby = new Baby();
        baby.setNameBaby(name);
        baby.setSexBaby(kelamin);
        baby.setDateBirthBaby(tanggal);
        baby.setIdUser(dbHelper.getString(Constanta.PREF_ID));

        presenter.onAddBaby(baby);
    }

    private void updateDataBaby(){
        name = binding.edtNameBaby.getText().toString();
        tanggal = binding.etTglLahir.getText().toString();

        if (TextUtils.isEmpty(name)) {
            binding.edtNameBaby.setError(FIELD_REQUIRED);
            return;
        }

        if (Objects.equals(kelamin, "")){
            loading.dialogWarning("Tambah Bayi", "Harap pilih jenis kelamin");
        }

        if (TextUtils.isEmpty(tanggal)){
            loading.dialogWarning("Tambah Bayi", "Harap isi Tanggal Lahir");
            return;
        }
        baby = new Baby();
        baby.setNameBaby(name);
        baby.setSexBaby(kelamin);
        baby.setDateBirthBaby(tanggal);
        baby.setIdUser(dbHelper.getString(Constanta.PREF_ID));

        presenter.onUpdateBaby(idBaby,baby);
    }

    public void onRadioButtonClicked(@NonNull View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rb_laki:
                if (checked)
                    kelamin = getResources().getString(R.string.title_laki);
                    break;
            case R.id.rb_perempuan:
                if (checked)
                    kelamin = getResources().getString(R.string.title_perempuan);
                    break;
        }
    }
}