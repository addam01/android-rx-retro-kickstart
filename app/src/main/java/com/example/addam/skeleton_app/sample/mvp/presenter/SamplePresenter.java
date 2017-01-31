package com.example.addam.skeleton_app.sample.mvp.presenter;

import android.support.annotation.NonNull;

import com.example.addam.skeleton_app.core.Presenters;
import com.example.addam.skeleton_app.sample.mvp.model.SampleResponse;
import com.example.addam.skeleton_app.sample.mvp.retroRest.GetSample;
import com.example.addam.skeleton_app.sample.mvp.view.SampleView;

import rx.Subscriber;

/**
 * Created by Addam on 1/31/17.
 * All your logic codes goes here
 */

public class SamplePresenter implements Presenters<SampleView> {
    SampleView mSampleView;
    GetSample mGetSample;

    public SamplePresenter(GetSample getSample) {
        mGetSample = getSample;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void attachView(@NonNull SampleView view) {
        mSampleView = view;
    }

    public void getSampleRes(){
        mGetSample.execute(new Subscriber<SampleResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SampleResponse sampleResponse) {

            }
        });
    }

}
