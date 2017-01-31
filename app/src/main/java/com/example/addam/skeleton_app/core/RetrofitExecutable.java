package com.example.addam.skeleton_app.core;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Addam on 1/31/17.
 */

public interface RetrofitExecutable {
    /**
     *Trigger the class will execute retrofit observable.
     * @param subscriber
     * @return
     */
    Subscription execute(Subscriber subscriber);
}
