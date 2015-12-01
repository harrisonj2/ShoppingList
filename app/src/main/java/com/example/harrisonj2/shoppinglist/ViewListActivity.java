package com.example.harrisonj2.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ViewListActivity extends AppCompatActivity {

    private ShoppingList[] listData;
    private ShoppingList shoppingList;

    //0 = name
    //1 = store
    //2 = date
    int orderByLists;
    DBHandler dbHandler;
    ListAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        dbHandler = new DBHandler(this, null);

        String[] noLists = {"No Shopping Lists Found"};

        switch(orderByLists){
            case 0 :
                listData = dbHandler.getListName();
                break;
            case 1 :
                listData = dbHandler.getListStore();
                break;
            case 2 :
                listData = dbHandler.getListDate();
                break;
            default :
                listData = dbHandler.getShoppingLists();
                break;
        }

        if (listData != null) {
            adapter = new ViewListAdapter(this, listData);
        } else {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noLists);
        }

        ListView listView = (ListView) findViewById(R.id.shoppingListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        shoppingList = (ShoppingList) parent.getItemAtPosition(position);

                        Intent i = new Intent(ViewListActivity.this, openListActivity.class);
                        i.putExtra("shoppingListID", shoppingList.getId());
                        i.putExtra("shoppingListName", shoppingList.getListName());
                        i.putExtra("shoppingListStore", shoppingList.getStoreName());
                        i.putExtra("shoppingListDate", shoppingList.getTripDate());
                        i.putExtra("orderBy", 0);

                        startActivity(i);
                    }
                }
        );

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

    public void orderByName(View view){
        intent = new Intent(this, ViewListActivity.class);
        intent.putExtra("orderBy", 0);
        startActivity(intent);
    }

    public void orderByStore(View view){
        intent = new Intent(this, ViewListActivity.class);
        intent.putExtra("orderBy", 1);
        startActivity(intent);
    }

    public void orderByDate(View view){
        intent = new Intent(this, ViewListActivity.class);
        intent.putExtra("orderBy", 2);
        startActivity(intent);
    }
}