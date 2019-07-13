package com.alc_challenge_android;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

public class Access_Permission_Request {
  public static final int INTERNET_PERMISSION_CONDE= 1;

  public static final String[] INTERNET_PERMISSION = {Manifest.permission.INTERNET};
  public static boolean hasPremissions(Context context, String... permissions){
    if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M && context !=null && permissions !=null){
      for (String permission:permissions){
        if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
          return false;
        }
      }
    }
    return true;
  }
}
