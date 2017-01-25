package com.common.permission;

import android.app.Activity;
import android.content.Intent;

import static com.common.permission.PermissionActivity.PERMISSIONS;

/**
 * Created by Admin on 1/24/2017.
 */

public class BuilderPermission {
    private String[] permissions = new String[]{};
    private Activity activity;

    public BuilderPermission setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public BuilderPermission setPermission(String[] permissions) {
        this.permissions = permissions;
        return this;
    }

    public BuilderPermission setPermission(String permissions) {
        this.permissions[permissions.length()] = permissions;
        return this;
    }

    public void build() {
        Intent intent = new Intent(activity, PermissionActivity.class);
        intent.putExtra(PERMISSIONS, permissions);
        activity.startActivity(intent);
    }
}
