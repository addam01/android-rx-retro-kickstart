package com.example.addam.skeleton_app.SampleGithub.RetroRest;

import com.example.addam.skeleton_app.core.Rest.APIs;
import com.example.addam.skeleton_app.core.RetrofitExecutable;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by addam on 8/3/17.
 */

public class GitUserREST implements RetrofitExecutable {
    private APIs mAPIs;

    private String username;

    public GitUserREST(APIs mAPIs) {
        this.mAPIs = mAPIs;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Subscription execute(Subscriber subscriber) {
        return mAPIs.getGithubUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
