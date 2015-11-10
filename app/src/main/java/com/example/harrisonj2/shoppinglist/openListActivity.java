package com.example.harrisonj2.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class openListActivity extends AppCompatActivity {

    Intent intent;
    int shoppingListID;
    String shoppingListName;
    String shoppingListStore;
    String shoppingListDate;

    private Items[] listData;
    DBHandler dbHandler;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_list);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            shoppingListID = extras.getInt("shoppingListID");
            shoppingListName = extras.getString("shoppingListName");
            shoppingListStore = extras.getString("shoppingListStore");
            shoppingListDate = extras.getString("shoppingListDate");
        }

        dbHandler = new DBHandler(this, null);

        String[] noLists = {"No items Found"};

        listData = dbHandler.getItems(shoppingListID);

        if (listData != null) {
            adapter = new ViewItemAdapter(this, listData);
        } else {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noLists);
        }

        ListView listView = (ListView) findViewById(R.id.itemListView);

        listView.setAdapter(adapter);

        TextView shoppingListNameTV = (TextView) findViewById(R.id.shoppingListNameTV);
        shoppingListNameTV.setText(shoppingListName);

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

    public void goToAddItem(View view){
        intent = new Intent(this, AddItemActivity.class);
        intent.putExtra("shoppingListID", shoppingListID);
        intent.putExtra("shoppingListName", shoppingListName);
        startActivity(intent);
    }

    public void deleteList(View view) {
        dbHandler.deleteShoppingList(shoppingListID);
        intent = new Intent(this, ViewListActivity.class);
        startActivity(intent);
    }

}
