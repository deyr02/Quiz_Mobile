package com.example.cars;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Attempt {
    @SerializedName("id")
    private Integer ID ;
    @SerializedName(("isSubmitted"))
    private Integer IsSubmitted;
    @SerializedName("totalQuestions")
    private Integer TotalQuestions ;
    @SerializedName("totalCorrectAnswer")
    private Integer TotalCorrectAnswer;
    @SerializedName("startedAt")
    private String StartedAt;
    @SerializedName("finishedAt")
    private String FinishedAT;
    @SerializedName("userId")
    private Integer UserId;

    public Attempt(Integer ID, Integer isSubmitted, Integer totalQuestions, Integer totalCorrectAnswer, String startedAt, String finishedAT, Integer userId) {
        this.ID = ID;
        this.IsSubmitted = isSubmitted;
        this.TotalQuestions = totalQuestions;
        this.TotalCorrectAnswer = totalCorrectAnswer;
        this.StartedAt = startedAt;
        this.FinishedAT = finishedAT;
        this.UserId = userId;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIsSubmitted() {
        return IsSubmitted;
    }

    public void setIsSubmitted(Integer isSubmitted) {
        IsSubmitted = isSubmitted;
    }

    public Integer getTotalQuestions() {
        return TotalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        TotalQuestions = totalQuestions;
    }

    public Integer getTotalCorrectAnswer() {
        return TotalCorrectAnswer;
    }

    public void setTotalCorrectAnswer(Integer totalCorrectAnswer) {
        TotalCorrectAnswer = totalCorrectAnswer;
    }

    public String getStartedAt() {
        return StartedAt;
    }

    public void setStartedAt(String startedAt) {
        StartedAt = startedAt;
    }

    public String getFinishedAT() {
        return FinishedAT;
    }

    public void setFinishedAT(String finishedAT) {
        FinishedAT = finishedAT;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }
}
