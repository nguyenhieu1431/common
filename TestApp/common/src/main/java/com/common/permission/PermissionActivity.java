package com.common.permission;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 1/24/2017.
 */

public class PermissionActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION_SETTING = 9965;
    private static final int REQUEST_PERMISSION = 9975;
    public static final String PERMISSIONS = "PERMISSIONS";

    private String[] permissions = new String[]{};

    private List<String> permissionNeedAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissions = getIntent().getStringArrayExtra(PERMISSIONS);
        checkPermission();
    }

    private void checkPermission() {
        permissionNeedAdd = new ArrayList<>();
        for (final String permission : permissions) {
            boolean isGranted = ContextCompat.checkSelfPermission(PermissionActivity.this, permission) == PackageManager.PERMISSION_GRANTED;
            if (!isGranted) {
                permissionNeedAdd.add(permission);
            }
        }

        if (!permissionNeedAdd.isEmpty()) {
            ActivityCompat.requestPermissions(PermissionActivity.this, permissionNeedAdd.toArray(new String[permissionNeedAdd.size()]), REQUEST_PERMISSION);
        } else {
            sendSuccess();
            finish();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionNeedAdd.clear();
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0) {
                    //checkPermission();
                    boolean isAllGrant = false;
                    for (int grantRs : grantResults) {
                        if (grantRs == PackageManager.PERMISSION_GRANTED) {
                            isAllGrant = true;
                        } else {
                            isAllGrant = false;
                        }
                    }
                    if (!isAllGrant) {
                        boolean isRationale = true;
                        for (String permission : permissions) {
                            //if isRationale==false mean don't ask again checkbox was checked
                            isRationale = ActivityCompat.shouldShowRequestPermissionRationale(PermissionActivity.this, permission);
                            if (!isRationale) {
                                break;
                            }
                        }

                        if (isRationale) {
                            sendDenied();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(PermissionActivity.this);
                            builder.setTitle(null);
                            builder.setMessage("Please allow in App Settings for additional functionality.");
                            builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    openAppSettings();
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.setNegativeButton("Denied", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    finish();
                                    sendDenied();
                                }
                            });
                            builder.show();
                        }

                    } else {
                        //success
                        sendSuccess();
                    }
                }
                break;
        }

    }

    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PERMISSION_SETTING) {
            if (resultCode == Activity.RESULT_OK) {
                sendSuccess();
            } else {
                sendDenied();
            }
        }
    }

    private void sendSuccess() {
        EventBus.getDefault().post(new PermissionEvent(PermissionEvent.PERMISSION_GRANTED));
    }

    private void sendDenied() {
        EventBus.getDefault().post(new PermissionEvent(PermissionEvent.PERMISSION_DENIED));
    }

}
