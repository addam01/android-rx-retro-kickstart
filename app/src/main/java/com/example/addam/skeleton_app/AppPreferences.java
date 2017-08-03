package com.example.addam.skeleton_app;

import android.app.Application;

import com.example.addam.skeleton_app.core.Rest.RxBus;

/**
 * Created by Addam on 1/25/17.
 * All of the Shared Preferences, and Locals goes here.
 * Just make sure in the Activities, must create an object that refers to this class
 */

public class AppPreferences extends Application {

    private RxBus mRxBus = null;


    public String getURL() {
        /**
         * Base URL for APIs here
         */
        return "https://api.github.com/";
    }
}
