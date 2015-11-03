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

public class ViewListActivity extends AppCompatActivity {

    private ShoppingList[] listData;

    DBHandler dbHandler;

    ListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        dbHandler = new DBHandler(this, null);

        String[] noLists = {"No Shopping Lists Found"};

        listData = dbHandler.getShoppingLists();

        if(listData != null) {
            adapter = new ViewListAdapter(this, listData);
        } else {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noLists);
        }

        ListView listView = (ListView) findViewById(R.id.shoppingListView);

        listView.setAdapter(adapter);



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

    public void viewOpenList(View view) {
        Intent intent = new Intent(this, openListActivity.class);
        startActivity(intent);
    }

    public void onDeleteList(){
        dbHandler.deleteShoppingList(1);
    }


}
