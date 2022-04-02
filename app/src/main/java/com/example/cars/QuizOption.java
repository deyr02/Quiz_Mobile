package com.example.cars;

import com.google.gson.annotations.SerializedName;

public class QuizOption {
    @SerializedName("id")
    private Integer Id;
    @SerializedName("description")
    private String Description;
    @SerializedName("quizId")
    private Integer QuizId;

    public QuizOption(Integer id, String description, Integer quizId) {
        Id = id;
        Description = description;
        QuizId = quizId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getQuizId() {
        return QuizId;
    }

    public void setQuizId(Integer quizId) {
        QuizId = quizId;
    }
}
