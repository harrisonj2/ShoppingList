package com.example.harrisonj2.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ItemSelect extends AppCompatActivity {

    Intent intent;
    int itemID, listID;
    String itemName, itemQuantity, itemECost, itemPurchased, listName, listStore, listDate;

    private Items item;
    DBHandler dbHandler;
    ListAdapter adapter;

    TextView listNameTextView, itemNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_select);

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

        listNameTextView = (TextView) findViewById(R.id.listNameTextView);
        listNameTextView.setText(listName);

        itemNameTextView = (TextView) findViewById(R.id.itemNameTextView);
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
        intent = new Intent(this, openListActivity.class);
        intent.putExtra("shoppingListID", listID);
        intent.putExtra("shoppingListName", listName);
        intent.putExtra("shoppingListStore", listStore);
        intent.putExtra("shoppingListDate", listDate);
        startActivity(intent);
    }

    public void deleteItem(View view){
        dbHandler.deleteItem(itemID);
        intent = new Intent(this, openListActivity.class);
        intent.putExtra("shoppingListID", listID);
        intent.putExtra("shoppingListName", listName);
        intent.putExtra("shoppingListStore", listStore);
        intent.putExtra("shoppingListDate", listDate);
        startActivity(intent);
    }

}
