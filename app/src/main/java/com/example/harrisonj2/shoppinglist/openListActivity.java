package com.example.harrisonj2.shoppinglist;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
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

    private Items[] itemData;
    private Items item;
    DBHandler dbHandler;
    ListAdapter adapter;

    NotificationCompat.Builder notification;
    private static final int uniqueID = 456852;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_list);

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        if(allGotten(this)){
            notification(this);
        }

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            shoppingListID = extras.getInt("shoppingListID");
            shoppingListName = extras.getString("shoppingListName");
            shoppingListStore = extras.getString("shoppingListStore");
            shoppingListDate = extras.getString("shoppingListDate");
        }

        dbHandler = new DBHandler(this, null);

        String[] noLists = {"No items Found"};

        itemData = dbHandler.getItems(shoppingListID);

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
        double total = 0;
        int position = 0;
        while(position < itemData.length){
            total += (Double.parseDouble(itemData[position].geteCost()));
        }
        Double tot = total;
        String message = tot.toString();
        TextView costTextView = (TextView) findViewById(R.id.costTextView);
        costTextView.setText(message);
    }

    public void notification(openListActivity view){
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("List Complete");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("List Complete");
        notification.setContentText("All items in " + shoppingListName + " have been purchased.");

        intent = new Intent(this, ViewListActivity.class);
        PendingIntent pend = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pend);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build());
    }

    public boolean allGotten(openListActivity view){
        boolean gotten = true;

        if(itemData != null){
            for (int i = 0; i < itemData.length; i++){
                if(itemData[i].getPurchased().equals("false"))
                    gotten = false;
            }
        }else
            gotten = false;


        return gotten;
    }
}
