package com.aa.josh.aagrocerylist;

import android.app.ActivityManager;
import android.app.Application;
import android.util.Log;

import com.adadapted.android.sdk.AdAdapted;

/**
 * Added for AddIt's sake.
 */

public class AAGroceryListApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        // Replace the API_KEY with the key provided to you
        // If your app is in production, change the DEV to PROD.
        AdAdapted.init()
                .withAppId("NTK1MJG4MTC2YTJL")
                .inEnv(AdAdapted.Env.DEV)
                .start(this);
    }
}
