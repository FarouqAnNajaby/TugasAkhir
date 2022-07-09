package com.example.babycare.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.example.babycare.R;
import com.example.babycare.ui.login.LoginActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Loading {

    private SweetAlertDialog dialog;
    private Context context;

    public Loading(Context context) {
        this.context = context;
//        dialog = new SweetAlertDialog(context,type);
    }

    public void dialoggBasic(String title, String content){
        new SweetAlertDialog(context)
                .setTitleText(title)
                .setContentText(content)
                .show();
    }

    public void dialogLoading(String title){
        dialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText(title);
        dialog.setCancelable(false);
        dialog.setConfirmClickListener(sweetAlertDialog -> dialog.dismiss());
        dialog.show();
    }

    public void dialogSuccess(String title, String content){
        dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setCancelable(false);
        dialog.setConfirmClickListener(sweetAlertDialog -> dialog.dismiss());
        dialog.show();
    }

    public void dialogWarning(String title, String content){
        dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setCancelable(false);
        dialog.setConfirmClickListener(sweetAlertDialog -> dialog.dismiss());
        dialog.show();
    }

    public void dialogError(String title, String content){
        dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setConfirmClickListener(sweetAlertDialog -> dialog.dismiss());
        dialog.setCancelable(false);
        dialog.setConfirmClickListener(sweetAlertDialog -> dialog.dismiss());
        dialog.show();
    }

    public void dialogCustom(String title, String content){
        dialog = new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setCancelable(false);
        dialog.setConfirmClickListener(sweetAlertDialog -> dialog.dismiss());
        dialog.show();
    }
}
