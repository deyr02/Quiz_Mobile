package com.example.cars;

import com.google.gson.annotations.SerializedName;

public class AccountResponseDTO {
    @SerializedName("success")
    private  boolean success;
    @SerializedName("data")
    private  User user;
    @SerializedName("message")
    private String message;

    public AccountResponseDTO(boolean success, User user, String message) {
        this.success = success;
        this.user = user;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
