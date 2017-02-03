package com.example.addam.skeleton_app.sample.mvp.view;

import com.example.addam.skeleton_app.core.View;
import com.example.addam.skeleton_app.sample.mvp.model.SampleResponse;

/**
 * Created by Addam on 1/31/17.
 */

public interface SampleView extends View {

    void doSomething(SampleResponse sampleResponse);
}
