package com.example.cars;

import com.google.gson.annotations.SerializedName;

public class AttemptLine {
    @SerializedName("attemptId")
    private  int AttemptId;
    @SerializedName("quizId")
    private  int QuizId;
    @SerializedName("isAnswered")
    private int IsAnswered;
    @SerializedName("userSelection")
    private String UserSelection;
    @SerializedName("quiz")
    private  Quiz quiz;

    public AttemptLine(int attemptId, int quizId, int isAnswered, String userSelection, Quiz quiz) {
        AttemptId = attemptId;
        QuizId = quizId;
        IsAnswered = isAnswered;
        UserSelection = userSelection;
        this.quiz = quiz;
    }

    public int getAttemptId() {
        return AttemptId;
    }

    public void setAttemptId(int attemptId) {
        AttemptId = attemptId;
    }

    public int getQuizId() {
        return QuizId;
    }

    public void setQuizId(int quizId) {
        QuizId = quizId;
    }

    public int getIsAnswered() {
        return IsAnswered;
    }

    public void setIsAnswered(int isAnswered) {
        IsAnswered = isAnswered;
    }

    public String getUserSelection() {
        return UserSelection;
    }

    public void setUserSelection(String userSelection) {
        UserSelection = userSelection;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
