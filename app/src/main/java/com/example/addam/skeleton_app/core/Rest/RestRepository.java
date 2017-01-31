package com.example.addam.skeleton_app.core.Rest;

import android.content.Context;
import android.util.Log;

import com.example.addam.skeleton_app.AppPreferences;
import com.example.addam.skeleton_app.sample.mvp.model.SampleRequest;
import com.example.addam.skeleton_app.sample.mvp.model.SampleResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Addam on 1/31/17.
 */

public class RestRepository implements APIs {
    private Retrofit mRetrofit;
    private String Token;

    RestServices mRestServices;

    public RestRepository(Context context, String token) {
        Token = token;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        /**
         * You may want to create an adapter for any nested JSON here
         * just pass the nested Model class and the Adapter class into it.
         * Google up GSON type adapter to create new adapter classes.
         */
        Gson gson = gsonBuilder
//                .registerTypeAdapter(SomeNestedModelClass, new AdapterModelClass)
                .create();
        /**
         * A logging interceptor for that you can see all outbound and inbound connections
         */
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        /**
         * The trustmanager is for incase you have a self signed SSL or TLS cert connection.
         * Else, just ignore this
         */
        createTrustManager(loggingInterceptor);

        OkHttpClient clientLIVE = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(loggingInterceptor)
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();

                    Request.Builder requestBuilder = originalRequest.newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("Content-type", "application/json")
                            .method(originalRequest.method(), originalRequest.body());

                    // Add JWT Token to the header if not empty
                    if (!authorizationTokenIsEmpty()) {
                        requestBuilder.addHeader("Authorization", "Bearer " + Token);
                    }

                    Request request = requestBuilder.build();

                    return chain.proceed(request);
                })
                //If you want to add the SSL Socket,
//                .sslSocketFactory(createTrustManager(loggingInterceptor))
                .build();
        AppPreferences appPreferences = (AppPreferences) context.getApplicationContext();
        String baseURL = appPreferences.getURL();
        Log.d("Pref", baseURL);
        /**
         * This is for the putting together the Retrofit client with GSON
         * and the RestServices APIs urls. We put it seperate because this is the main connector for multiple services
         */
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(clientLIVE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mRestServices = mRetrofit.create(RestServices.class);

    }

    @Override
    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    /**
     * this is a trust manager functions
     * @param httpLoggingInterceptor
     * @return
     */
    @Override
    public SSLSocketFactory createTrustManager(HttpLoggingInterceptor httpLoggingInterceptor) {
        // Install the all-trusting trust manager
        final SSLContext sslContext;
        final SSLSocketFactory sslSocketFactory;

        // Create a trust manager that does not validate certificate chains
        final TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            sslSocketFactory = sslContext.getSocketFactory();
            return sslSocketFactory;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public boolean authorizationTokenIsEmpty() {
        return Token.length()<=0;
    }

    @Override
    public Observable<SampleResponse> getSampleResponse(String username, String password) {
        return mRestServices.ResquestSample(new SampleRequest(username,password));
    }

}
