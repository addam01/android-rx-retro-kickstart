package com.example.addam.skeleton_app.core;

import android.support.annotation.NonNull;

/**
 * Created by Addam on 1/31/17.
 */

public interface Presenters<T extends com.example.addam.skeleton_app.core.View> {
    void onStart();

    void onStop();

    void onError(Throwable e);

    void attachView(@NonNull T view);
}
