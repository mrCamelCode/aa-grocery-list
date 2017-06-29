package com.aa.josh.aagrocerylist;

import android.app.ActivityManager;
import android.app.Application;
import android.util.Log;

import com.adadapted.android.sdk.AdAdapted;
import com.adadapted.android.sdk.core.addit.AddToListItem;
import com.adadapted.android.sdk.core.addit.Content;
import com.adadapted.android.sdk.ui.messaging.AaSdkAdditContentListener;
import com.adadapted.android.sdk.ui.messaging.AaSdkSessionListener;
import com.adadapted.sdk.addit.ui.AdditContentListener;

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
                .setSdkAdditContentListener(new AaSdkAdditContentListener()
                {
                    @Override
                    public void onContentAvailable(final Content content)
                    {
                        // Handle the payload if it has something in it.
                        if (content.getPayload().size() > 0)
                        {
                            // Go through the payload and add every item.
                            for (AddToListItem li : content.getPayload())
                                MainActivity.externalAddListItem(
                                        li.getTitle() + " from " + li.getBrand(), 1);

                            // Acknowledge success.
                            content.acknowledge();
                        }
                        else
                        {
                            // Payload was empty, content delivery failed.
                            content.failed("The payload was empty.");
                        }
                    }
                })
                .start(this);
    }
}
