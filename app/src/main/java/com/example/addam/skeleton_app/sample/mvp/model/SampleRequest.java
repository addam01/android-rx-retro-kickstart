package com.example.addam.skeleton_app.sample.mvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Addam on 2/1/17.
 */

public class SampleRequest {
    @SerializedName("username")
    private String name;
    @SerializedName("password")
    private String password;

    public SampleRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
