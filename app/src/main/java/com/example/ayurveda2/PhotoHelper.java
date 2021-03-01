package com.example.ayurveda2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

import static com.example.ayurveda2.Constants.currentPhotoPath;

public class PhotoHelper {


    private static final String TAG = "PhotoHelper";
    private static String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    static boolean checkPermissions(Context context){
        return ContextCompat.checkSelfPermission(context, permissions[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, permissions[1]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, permissions[2]) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean verifyPermissions(Context context, int REQUEST_CODE){
        Log.d(TAG, "verifyPermissions: asking user for permissions");
        if(checkPermissions(context)){
            return true;
        }else{
            ActivityCompat.requestPermissions((Activity) context, permissions, REQUEST_CODE);
            return false;
        }
    }

    public static void clearStorage(Context context){
        File dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Log.d(TAG, "clearStorage: list length is " + dir.list().length);
        if(dir.isDirectory() && dir.list().length > 1) {
            String[] files = dir.list();
            new File(dir, files[1]).delete();
        }
    }

    public static void clearAll(Context context){
        File dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if(dir != null && dir.isDirectory()){
            String[] files = dir.list();
            for (String file : files){
                new File(dir, file).delete();
            }
        }
        currentPhotoPath = null;
    }
}