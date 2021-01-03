package com.dropapp.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {
    private String email;
    private String password;

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
