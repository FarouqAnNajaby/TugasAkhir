package com.example.babycare.model;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("kode")
    private int kode;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
