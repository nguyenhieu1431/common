package com.common.network;

/**
 * Created by Admin on 1/25/2017.
 */

public class NetworkEvent {
    private boolean isHasNetwork;

    public NetworkEvent(boolean isHasNetwork) {
        this.isHasNetwork = isHasNetwork;
    }

    public boolean isHasNetwork() {
        return isHasNetwork;
    }

    public void setHasNetwork(boolean hasNetwork) {
        isHasNetwork = hasNetwork;
    }
}
