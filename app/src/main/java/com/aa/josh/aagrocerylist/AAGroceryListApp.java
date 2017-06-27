package com.aa.josh.aagrocerylist;

import android.app.ActivityManager;
import android.app.Application;
import android.util.Log;

import com.adadapted.sdk.addit.AdAdapted;
import com.adadapted.sdk.addit.core.content.AdditAddToListItem;
import com.adadapted.sdk.addit.core.content.AdditContent;
import com.adadapted.sdk.addit.core.content.Content;
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

        //Replace the API_KEY with the key provided to you by AdAdapted
        //Once your app is ready for production, change the DEV to PROD.
        AdAdapted.init()
                .withAppId("API_KEY")
                .inEnv(AdAdapted.Env.DEV)
                .setAdditContentListener(new AdditContentListener()
                {
                    @Override
                    public void onContentAvailable(final AdditContent content)
                    {
                        /*
                        // Get payload info.
                        switch(content.getType())
                        {
                            case 0: // Add to list item.

                                break;
                            case 1: // Add to list items.

                                break;
                        }
                        */

                        if (content.getPayload().isEmpty())
                        {
                            // Failure
                            content.failed("Empty payload.");
                            Log.d("ADDITFAILED", "Empty payload.");
                        }
                        else
                        {
                            for (AdditAddToListItem li : content.getPayload())
                            {
                                MainActivity.addExternalListItem(li.getTitle() + "from"
                                        + li.getBrand(), 1);
                            }

                            // Success
                            content.acknowledge();
                        }
                    }
                })
                .start(this);
    }
}
