package com.common.permission;

/**
 * Created by Admin on 1/24/2017.
 */

public class PermissionEvent {
    public static final int PERMISSION_GRANTED = 1;
    public static final int PERMISSION_DENIED = 2;

    private int isGranted;

    public PermissionEvent(int isGranted) {
        this.isGranted = isGranted;
    }

    public int getIsGranted() {
        return isGranted;
    }

    public void setIsGranted(int isGranted) {
        this.isGranted = isGranted;
    }

    public boolean isGranted() {
        if (isGranted == PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
}