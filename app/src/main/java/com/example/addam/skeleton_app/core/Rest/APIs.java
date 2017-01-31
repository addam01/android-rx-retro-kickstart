package com.example.addam.skeleton_app.core.Rest;

import com.example.addam.skeleton_app.sample.mvp.model.SampleResponse;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by Addam on 1/31/17.
 */

public interface APIs {
    Retrofit getRetrofit();

    SSLSocketFactory createTrustManager(HttpLoggingInterceptor httpLoggingInterceptor);
    boolean authorizationTokenIsEmpty();

    /**
     * There are 2 ways to pass parameters, either as a whole BODY or as individual param
     * @param username
     * @param password
     */
    Observable<SampleResponse> getSampleResponse(String username, String password);

}
