package com.example.harrisonj2.shoppinglist;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ItemSelect extends AppCompatActivity {

    Intent intent;
    int itemID, listID;
    String itemName, itemQuantity, itemECost, itemPurchased, listName, listStore, listDate;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_select);

        dbHandler = new DBHandler(this, null);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            itemID =  extras.getInt("itemID");
            itemName = extras.getString("itemName");
            itemQuantity = extras.getString("itemQuantity");
            itemECost = extras.getString("itemECost");
            itemPurchased = extras.getString("itemPurchased");
            listID = extras.getInt("listID");
            listName = extras.getString("shoppingListName");
            listStore = extras.getString("shoppingListStore");
            listDate = extras.getString("shoppingListDate");
        }

        TextView listNameTextView = (TextView) findViewById(R.id.listNameTextView);
        listNameTextView.setText(listName);

        TextView itemNameTextView = (TextView) findViewById(R.id.itemNameTextView);
        itemNameTextView.setText(itemName);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void gotItem(View view){
        dbHandler.gotItem(itemID);

        Items[] gottenItems = dbHandler.getNotGottenItems(listID);

        if(gottenItems == null) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Shopping list")
                            .setContentText(listName + " completed!");

            Notification notification = mBuilder.build();
            notification.notify();
        }

        intent = new Intent(this, openListActivity.class);
        intent.putExtra("shoppingListID", listID);
        intent.putExtra("shoppingListName", listName);
        intent.putExtra("shoppingListStore", listStore);
        intent.putExtra("shoppingListDate", listDate);
        startActivity(intent);
    }

    public void goodbyeItem(View view){
        dbHandler.deleteItem(itemID);
        intent = new Intent(this, openListActivity.class);
        intent.putExtra("shoppingListID", listID);
        intent.putExtra("shoppingListName", listName);
        intent.putExtra("shoppingListStore", listStore);
        intent.putExtra("shoppingListDate", listDate);
        startActivity(intent);
    }

}
