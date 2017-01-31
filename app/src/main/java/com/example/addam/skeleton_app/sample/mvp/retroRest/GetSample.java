package com.example.addam.skeleton_app.sample.mvp.retroRest;

import com.example.addam.skeleton_app.core.Rest.APIs;
import com.example.addam.skeleton_app.core.RetrofitExecutable;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Addam on 1/31/17.
 *
 * This is to tie your REST connections to the Retrofit APIs
 * which in turn will be tied to your activity or presenters
 */

public class GetSample implements RetrofitExecutable {
    private String name;
    private String password;

    private APIs mAPIs;

    public GetSample(APIs APIs) {
        mAPIs = APIs;
    }

    public void setSample(String name, String password){
        this.name = name;
        this.password = password;
    }

    @Override
    public Subscription execute(Subscriber subscriber) {
        return mAPIs.getSampleResponse(name,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
