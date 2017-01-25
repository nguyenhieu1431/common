package com.testapp;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.common.network.NetworkChangeReceiver;

/**
 * Created by Admin on 1/25/2017.
 */

public class TestApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(new NetworkChangeReceiver(),new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
}
