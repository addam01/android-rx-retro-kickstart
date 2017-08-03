package com.example.addam.skeleton_app.core.Rest;

import com.example.addam.skeleton_app.SampleGithub.Models.GitUserResponse;
import com.example.addam.skeleton_app.sample.mvp.model.SampleRequest;
import com.example.addam.skeleton_app.sample.mvp.model.SampleResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Addam on 2/1/17.
 */

public interface RestServices {
    @POST("some request end")
    Observable<SampleResponse> ResquestSample(@Body SampleRequest sampleRequest);

    @GET("users/{username}")
    Observable<GitUserResponse> RequestUserGitub(@Path("username") String user);
}

