package com.common.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.common.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Admin on 1/25/2017.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isHasConnected = NetworkUtils.isConnected(context);
        NetworkEvent event = new NetworkEvent(isHasConnected);
        EventBus.getDefault().post(event);
    }
}
