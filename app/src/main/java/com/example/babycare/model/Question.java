package com.example.babycare.model;

public class Question {
    String Title, id;
    int ScreenImg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public int getScreenImg() {
        return ScreenImg;
    }
}
