package com.testapp;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.common.permission.BuilderPermission;
import com.common.permission.PermissionEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BuilderPermission().setActivity(this)
                .setPermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_CONTACTS})
                .build();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PermissionEvent event) {
        /* Do something */

    }
}
