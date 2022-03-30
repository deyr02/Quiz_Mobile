package com.example.cars;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Attempt {
    @SerializedName("id")
    private long ID ;
    @SerializedName(("isSubmitted"))
    private boolean IsSubmitted;
    @SerializedName("totalQuestions")
    private Integer TotalQuestions ;
    @SerializedName("totalCorrectAnswer")
    private Integer TotalCorrectAnswer;
    @SerializedName("startedAt")
    private String StartedAt;
    @SerializedName("finishedAT")
    private String FinishedAT;
    @SerializedName("appUserId")
    private String AppUserId;

    public Attempt(long ID, boolean isSubmitted, Integer totalQuestions, Integer totalCorrectAnswer, String startedAt, String finishedAT, String appUserId) {
        this.ID = ID;
        IsSubmitted = isSubmitted;
        TotalQuestions = totalQuestions;
        TotalCorrectAnswer = totalCorrectAnswer;
        StartedAt = startedAt;
        FinishedAT = finishedAT;
        AppUserId = appUserId;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public boolean isSubmitted() {
        return IsSubmitted;
    }

    public void setSubmitted(boolean submitted) {
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

    public String getAppUserId() {
        return AppUserId;
    }

    public void setAppUserId(String appUserId) {
        AppUserId = appUserId;
    }
}
