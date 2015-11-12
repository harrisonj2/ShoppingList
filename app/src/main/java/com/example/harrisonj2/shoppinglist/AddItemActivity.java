package com.example.harrisonj2.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {

    TextView listNameTextView;

    EditText itemNameEditText;
    EditText quantityEditText;
    EditText estCostEditText;

    int shoppingListID;
    String shoppingListName;

    DBHandler dbHandler;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            shoppingListID = extras.getInt("shoppingListID");
            shoppingListName = extras.getString("shoppingListName");
        }

        itemNameEditText = (EditText) findViewById(R.id.itemNameEditText);
        quantityEditText = (EditText) findViewById(R.id.quantityEditText);
        estCostEditText = (EditText) findViewById(R.id.estCostEditText);

        dbHandler = new DBHandler(this, null);

        String message;
        listNameTextView = (TextView) findViewById(R.id.listNameTextView);
        //listId = (Integer) findViewById(R.id.listId);
        Bundle name = getIntent().getExtras();
        if(name==null)
            message = "";
        else
            message = name.getString("name");

        listNameTextView = (TextView) findViewById(R.id.listNameTextView);
        listNameTextView.setText(message);

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

    public void addItem(View view){
        String name = itemNameEditText.getText().toString();
        String quantity = quantityEditText.getText().toString();
        String estCost = estCostEditText.getText().toString();

        if(name.trim().equals("") || quantity.trim().equals("") || estCost.trim().equals("")){
            Toast.makeText(this, "Please enter data into all fields!", Toast.LENGTH_LONG).show();
        }else{
            dbHandler.addItem(name, quantity, estCost, shoppingListID);
            Toast.makeText(this, "Item added!!", Toast.LENGTH_LONG).show();
        }
    }

    public void goBack(View view) {
        intent = new Intent(this, openListActivity.class);
        intent.putExtra("shoppingListID", shoppingListID);
        intent.putExtra("shoppingListName", shoppingListName);
        startActivity(intent);
    }
}
