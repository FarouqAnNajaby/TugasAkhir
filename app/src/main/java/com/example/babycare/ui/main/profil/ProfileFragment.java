package com.example.babycare.ui.main.profil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.babycare.R;
import com.example.babycare.databinding.FragmentProfileBinding;
import com.example.babycare.helper.DbHelper;
import com.example.babycare.ui.login.LoginActivity;
import com.example.babycare.ui.main.profil.about_us.AboutActivity;
import com.example.babycare.ui.main.profil.edit.EditProfileActivity;
import com.example.babycare.utils.Constanta;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class ProfileFragment extends Fragment implements View.OnClickListener{

    private FragmentProfileBinding binding;
    DbHelper helper;
    SweetAlertDialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        helper = new DbHelper(requireActivity());
        binding.containerAboutUs.setOnClickListener(this);
        binding.containerLogout.setOnClickListener(this);
        binding.containerUbahProfil.setOnClickListener(this);
        setView();
        return root;
    }

    private void setView() {
        binding.tvName.setText(helper.getString(Constanta.PREF_NAME));
        binding.tvEmail.setText(helper.getString(Constanta.PREF_EMAIL));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.container_about_us){
            goAbout();
        }else if (v.getId() == R.id.container_ubah_profil){
            goEdit();
        }else if (v.getId() == R.id.container_logout) {
            helper.put(Constanta.PREF_IS_LOGIN, false);
            dialog = new SweetAlertDialog(requireActivity(), SweetAlertDialog.WARNING_TYPE);
            dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            dialog.setTitleText(requireActivity().getResources().getString(R.string.title_logout));
            dialog.setContentText(requireActivity().getResources().getString(R.string.title_confirm_logout));
            dialog.setCancelText(requireActivity().getResources().getString(R.string.title_no));
            dialog.setConfirmText(requireActivity().getResources().getString(R.string.title_yes));
            dialog.setCancelClickListener(sweetAlertDialog -> dialog.dismiss());
            dialog.setConfirmClickListener(sweetAlertDialog -> logout());
            dialog.show();
        }
    }

    private void goAbout(){
        Intent intent = new Intent(requireActivity(), AboutActivity.class);
        requireActivity().startActivity(intent);
    }

    private void logout(){
        Intent intent = new Intent(requireActivity(), LoginActivity.class);
        requireActivity().startActivity(intent);
        requireActivity().finish();
    }

    private void goEdit(){
        Intent intent = new Intent(requireActivity(), EditProfileActivity.class);
        requireActivity().startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        setView();
    }
}