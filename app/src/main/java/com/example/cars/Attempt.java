package com.example.cars;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Attempt {
    @SerializedName("id")
    private long ID ;
    @SerializedName(("isSubmitted"))
    private Integer IsSubmitted;
    @SerializedName("totalQuestions")
    private Integer TotalQuestions ;
    @SerializedName("totalCorrectAnswer")
    private Integer TotalCorrectAnswer;
    @SerializedName("startedAt")
    private String StartedAt;
    @SerializedName("finishedAT")
    private String FinishedAT;
    @SerializedName("appUserId")
    private long AppUserId;

    public Attempt(long ID, Integer isSubmitted, Integer totalQuestions, Integer totalCorrectAnswer, String startedAt, String finishedAT, long appUserId) {
        this.ID = ID;
        this.IsSubmitted = isSubmitted;
        this.TotalQuestions = totalQuestions;
        this.TotalCorrectAnswer = totalCorrectAnswer;
        this.StartedAt = startedAt;
        this.FinishedAT = finishedAT;
        this.AppUserId = appUserId;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Integer isSubmitted() {
        return IsSubmitted;
    }

    public void setSubmitted(Integer submitted) {
        IsSubmitted = submitted;
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

    public Long getAppUserId() {
        return AppUserId;
    }

    public void setAppUserId(long appUserId) {
        AppUserId = appUserId;
    }
}
