package com.example.harrisonj2.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class CreateListActivity extends AppCompatActivity {

    EditText listNameEditText;
    EditText storeNameEditText;
    EditText dateEditText;

    Intent intent;

    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        listNameEditText = (EditText) findViewById(R.id.listNameEditText);
        storeNameEditText = (EditText) findViewById(R.id.storeNameEditText);
        dateEditText = (EditText) findViewById(R.id.dateEditText);

        dbHandler = new DBHandler(this, null);

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

    public void addList(View view){
        String name = listNameEditText.getText().toString();
        String store = storeNameEditText.getText().toString();
        String date = dateEditText.getText().toString();

        if(name.trim().equals("") || store.trim().equals("") || date.trim().equals("")){
            Toast.makeText(this, "Please enter data into all fields!", Toast.LENGTH_LONG).show();
        }else{
            dbHandler.addList(name, store, date);
            Toast.makeText(this, "List added!!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    public void viewLists(View view) {
        intent = new Intent(this, ViewListActivity.class);
        startActivity(intent);
    }
}
