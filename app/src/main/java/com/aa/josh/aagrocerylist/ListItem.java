package com.aa.josh.aagrocerylist;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * A custom button class that holds a little more information
 * relevant to the grocery app, such as whether the item has
 * been removed or not.
 */

public class ListItem extends AppCompatButton
{
    public boolean isRemoved;
    public String itemName;
    public int itemQuantity;

    private int backgroundColor = 0xff0099cc; // 0xffdddddd
    private int removedBackgroundColor = 0x55ff0000;
    private int textColor = 0xffffffff;
    private int removedTextColor = 0xffffffff;

    // If setDefaults is true, the default styling for a list
    // item button is applied.
    public ListItem(Context c, String itemName, int itemQuantity, boolean setDefaults)
    {
        super(c);

        this.itemName = itemName;
        this.itemQuantity = itemQuantity;

        if (setDefaults)
        {
            setAllCaps(false);
            setText(itemName + " (" + itemQuantity + ")");

            // Set onClick behavior
            setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListItem li = (ListItem) v;

                    if (li.isRemoved)
                        li.restore();
                    else
                        li.remove();
                }
            });

            // In-depth styling
            setBackgroundColor(backgroundColor);
            setTextColor(textColor);
            // START SET MARGIN
            // Create linear layout.
            LinearLayout.LayoutParams llp =
                    new LinearLayout.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT,
                            Toolbar.LayoutParams.WRAP_CONTENT);

            // Convert 5dp to pixels since setMargins only takes pixels
            float scale = getResources().getDisplayMetrics().density;
            int dpAsPixels = (int) (5 * scale + 0.5f);

            // Set margin.
            llp.setMargins(0, 0, 0, dpAsPixels);
            // Set new params
            setLayoutParams(llp);
            // END SET MARGIN
        }
    }

    /*
        Properly removes the item from the grocery list, setting
        its status and changing its appearance.
     */
    public void remove()
    {
        isRemoved = true;

        setBackgroundColor(removedBackgroundColor);
        setTextColor(removedTextColor);

        if (!MainActivity.showRemoved)
            setVisibility(View.GONE);
    }

    /*
        Properly restores the item to the grocery list, setting
        its status and changing its appearance.
     */
    public void restore()
    {
        isRemoved = false;

        setBackgroundColor(backgroundColor);
        setTextColor(textColor);
    }
}
