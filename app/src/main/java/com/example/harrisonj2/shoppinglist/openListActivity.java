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
import android.widget.TextView;

public class openListActivity extends AppCompatActivity {

    Intent intent;
    int shoppingListID;
    String shoppingListName;
    String shoppingListStore;
    String shoppingListDate;

    //0 = itemID
    //1 = name
    //2 = quantity
    //3 = cost
    int orderByItems;

    private Items[] itemData;
    private Items item;
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
            orderByItems = extras.getInt("orderBy");
        }

        dbHandler = new DBHandler(this, null);

        String[] noLists = {"No items Found"};

        switch(orderByItems){
            case 0 :
                itemData = dbHandler.getItems(shoppingListID);
                break;
            case 1 :
                itemData = dbHandler.getItemsName(shoppingListID);
                break;
            case 2 :
                itemData = dbHandler.getItemsQuantity(shoppingListID);
                break;
            case 3 :
                itemData = dbHandler.getItemsCost(shoppingListID);
                break;
            default :
                itemData = dbHandler.getItems(shoppingListID);
                break;
        }


        if (itemData != null) {
            adapter = new ViewItemAdapter(this, itemData);
        } else {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noLists);
        }

        ListView listView = (ListView) findViewById(R.id.itemListView);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        item = (Items) parent.getItemAtPosition(position);

                        Intent i = new Intent(openListActivity.this, ItemSelect.class);
                        i.putExtra("itemID", item.getId());
                        i.putExtra("itemName", item.getItemName());
                        i.putExtra("itemQuantity", item.getQuantity());
                        i.putExtra("itemECost", item.geteCost());
                        i.putExtra("itemPurchased", item.getPurchased());
                        i.putExtra("listID", item.getListId());
                        i.putExtra("shoppingListID", shoppingListID);
                        i.putExtra("shoppingListName", shoppingListName);
                        i.putExtra("shoppingListStore", shoppingListStore);
                        i.putExtra("shoppingListDate", shoppingListDate);

                        startActivity(i);
                    }
                }
        );

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
        intent.putExtra("shoppingListStore", shoppingListStore);
        intent.putExtra("shoppingListDate", shoppingListDate);
        startActivity(intent);
    }

    public void deleteList(View view) {
        dbHandler.deleteShoppingList(shoppingListID);
        intent = new Intent(this, ViewListActivity.class);
        startActivity(intent);
    }

    public void calculateCost(View view){
        Double total = 0.0;
        int position = 0;
        while(position < itemData.length) {
            total += itemData[position].geteCost();
            position++;
        }
        TextView costTextView = (TextView) findViewById(R.id.costTextView);
        costTextView.setText(total.toString());
    }

    public void orderByNames(View view){
        intent = new Intent(this, openListActivity.class);
        intent.putExtra("shoppingListID", shoppingListID);
        intent.putExtra("shoppingListName", shoppingListName);
        intent.putExtra("shoppingListStore", shoppingListStore);
        intent.putExtra("shoppingListDate", shoppingListDate);
        intent.putExtra("orderBy", 1);
        startActivity(intent);
    }

    public void orderByQuantity(View view){
        intent = new Intent(this, openListActivity.class);
        intent.putExtra("shoppingListID", shoppingListID);
        intent.putExtra("shoppingListName", shoppingListName);
        intent.putExtra("shoppingListStore", shoppingListStore);
        intent.putExtra("shoppingListDate", shoppingListDate);
        intent.putExtra("orderBy", 2);
        startActivity(intent);
    }

    public void orderByCost(View view){
        intent = new Intent(this, openListActivity.class);
        intent.putExtra("shoppingListID", shoppingListID);
        intent.putExtra("shoppingListName", shoppingListName);
        intent.putExtra("shoppingListStore", shoppingListStore);
        intent.putExtra("shoppingListDate", shoppingListDate);
        intent.putExtra("orderBy", 3);
        startActivity(intent);
    }
}
