package com.example.cars;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Quiz {
    @SerializedName("id")
    private Integer Id;
    @SerializedName("isAnswerMultiple")
    private Integer IsAnswerMultiple;
    @SerializedName("answers")
    private String Answers;
    @SerializedName("containsImage")
    private Integer ContainsImage;
    @SerializedName("image")
    private String ImageUrl;
    @SerializedName("question")
    private  String Question;
    @SerializedName("quizOptions")
    private ArrayList<QuizOption> QuizOptions;

    public Quiz(Integer id, Integer isAnswerMultiple, String answers, Integer containsImage, String imageUrl, String question, ArrayList<QuizOption> quizOptions) {
        Id = id;
        IsAnswerMultiple = isAnswerMultiple;
        Answers = answers;
        ContainsImage = containsImage;
        ImageUrl = imageUrl;
        Question = question;
        QuizOptions = quizOptions;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getIsAnswerMultiple() {
        return IsAnswerMultiple;
    }

    public void setIsAnswerMultiple(Integer isAnswerMultiple) {
        IsAnswerMultiple = isAnswerMultiple;
    }

    public String getAnswers() {
        return Answers;
    }

    public void setAnswers(String answers) {
        Answers = answers;
    }

    public Integer getContainsImage() {
        return ContainsImage;
    }

    public void setContainsImage(Integer containsImage) {
        ContainsImage = containsImage;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public ArrayList<QuizOption> getQuizOptions() {
        return QuizOptions;
    }

    public void setQuizOptions(ArrayList<QuizOption> quizOptions) {
        QuizOptions = quizOptions;
    }
}
