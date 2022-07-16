package com.example.babycare.model;

import java.util.List;

public class AnswerQuestion {
    List<String> id_gejala;
    String id_baby;
    String id_user;

    public AnswerQuestion() {
    }

    public List<String> getId_gejala() {
        return id_gejala;
    }

    public void setId_gejala(List<String> id_gejala) {
        this.id_gejala = id_gejala;
    }

    public String getId_baby() {
        return id_baby;
    }

    public void setId_baby(String id_baby) {
        this.id_baby = id_baby;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
