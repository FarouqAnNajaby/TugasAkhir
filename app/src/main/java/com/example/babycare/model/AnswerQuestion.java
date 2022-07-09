package com.example.babycare.model;

public class AnswerQuestion {
    String id;
    Boolean answer;

    public AnswerQuestion(String id, Boolean answer) {
        this.id = id;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }
}
