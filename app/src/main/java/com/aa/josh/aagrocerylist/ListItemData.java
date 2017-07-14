package com.aa.josh.aagrocerylist;

import java.io.Serializable;

/**
 * Added to allow saving of the grocery list by saving its data using an object
 * that doesn't have anything to do with AppCompatButton, which gives me major
 * problems with serialization if I try using ListItem.Data as I originally
 * wanted to do. It's a little bit of a messy solution, but it works, and with
 * an app that's just supposed to serve as an example, that's fine. It's not like
 * I'm teaching anyone how to build the app.
 */

public class ListItemData implements Serializable
{
    private boolean isRemoved;
    private String itemName;
    private int itemQuantity;

    public ListItemData(boolean isRemoved, String itemName, int itemQuantity)
    {
        this.isRemoved = isRemoved;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    public boolean getIsRemoved()
    {
        return isRemoved;
    }

    public String getItemName()
    {
        return itemName;
    }

    public int getItemQuantity()
    {
        return itemQuantity;
    }
}
