package com.wordingly.covidcontacttracer.contactbase;

import com.google.gson.annotations.SerializedName;

public class TokenResponse {

    @SerializedName("token")
    private String token;

    public TokenResponse() {

    }

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
