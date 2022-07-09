package com.example.babycare.helper;

import android.content.Context;
import android.content.SharedPreferences;


import android.content.SharedPreferences.Editor;

import org.jetbrains.annotations.NotNull;

public class    DbHelper{
    private final String PREF_NAME = "TugasAkhir";
    private Context context;
    private SharedPreferences sharedPreferences;
    @NotNull
    private final Editor editor;

    public DbHelper(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public final void put(String key, boolean value){
        this.editor.putBoolean(key,value).apply();
    }

    public final Boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key, false);
    }

    public final void delete(String key){
        editor.remove(key).apply();
    }

    public final void put(String key, String value){
        editor.putString(key,value).apply();
    }

    public final String getString(String key){
        return sharedPreferences.getString(key, null);
    }

    public final void clear(){
        editor.clear().apply();
    }
}
