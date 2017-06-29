package com.aa.josh.aagrocerylist;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    // Whether the user wants to see the removed list items.
    public static boolean showRemoved;

    ArrayList<Button> activeListItems = new ArrayList<Button>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize showRemoved by grabbing the value from the switch.
        showRemoved = ((Switch)findViewById(R.id.showRemovedSwitch)).isChecked();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
        Called on click of add item button. Adds the new item to the list.
     */
    public void btn_addListItem(View view)
    {
        // View refs
        EditText itemNameTextView = (EditText) findViewById(R.id.itemName);
        EditText itemQuantityTextView = (EditText) findViewById(R.id.itemQuantity);
        LinearLayout listLinLay = (LinearLayout) findViewById(R.id.itemListLinearLayout);

        // Get info from input fields and check for legal input.
        /*
            Note: Have to check input with length method because an empty
            field doesn't return an empty string or a null object, I can't
            tell what it returns except it's nothing. But not an empty string
            or null object. So, if the length is 0, that means the input is
            empty. Doing this check avoids an exception with Integer.parseInt.
         */
        String itemName = (itemNameTextView.getText().length() == 0) ?
                "" : itemNameTextView.getText().toString();
        int itemQuantity = (itemQuantityTextView.getText().length() == 0) ?
                1 : Integer.parseInt(itemQuantityTextView.getText().toString());

        itemQuantityTextView.getText();

        // Clear the input fields for ease of use.
        itemNameTextView.setText("");
        itemQuantityTextView.setText("");

        // Create new item and parent it to the list's layout if the item has a name.
        if (itemName != "")
            // Create new item and parent it to the list's layout.
            listLinLay.addView(new ListItem(this, itemName, itemQuantity, true));

        // Close the keyboard after clicking the Add Item button.
        // Check if no view has focus:
        View v = this.getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /*
        Called on click of clear list button. Clears the entire list.
     */
    public void clearList(View view)
    {
        // Make sure to add some kind of confirmation prompt.
        LinearLayout listLinLay = (LinearLayout)findViewById(R.id.itemListLinearLayout);

        listLinLay.removeAllViews();
    }

    /*
        Called on state change of showRemovedSwitch. Updates the showRemoved bool
        and also updates the list view.
     */
    public void updateShowRemoved(View view)
    {
        // Update showRemoved.
        showRemoved = ((Switch)findViewById(R.id.showRemovedSwitch)).isChecked();

        LinearLayout listLinLay = (LinearLayout) findViewById(R.id.itemListLinearLayout);

        // Search the list for removed elements and act on them accordingly.
        for (int i = 0; i < listLinLay.getChildCount(); i++)
        {
            ListItem item = (ListItem)listLinLay.getChildAt(i);
            if (showRemoved)
            {
                if (item.isRemoved)
                    item.setVisibility(View.VISIBLE);
            }
            else
                if (item.isRemoved)
                    item.setVisibility(View.GONE);
        }
    }
}
