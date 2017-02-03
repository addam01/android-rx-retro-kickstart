package com.example.addam.skeleton_app.sample.mvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Addam on 1/31/17.
 * This must corresponse to your model JSON API.
 */

public class SampleResponse {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("token")
    private String token;

    public SampleResponse(Boolean status, String token) {
        this.status = status;
        this.token = token;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
