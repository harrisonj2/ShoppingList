package com.example.harrisonj2.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Alexander on 11/2/2015.
 */
public class ViewListAdapter extends ArrayAdapter<ShoppingList> {

    ShoppingList shoppingList;

    TextView nameTextView;
    TextView storeNameTextView;
    TextView tripDateTextView;



    public ViewListAdapter(Context context, ShoppingList[] objects) {
        super(context, R.layout.custom_row, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View shoppingListView = inflater.inflate(R.layout.custom_row, parent, false);

        shoppingList = getItem(position);

        nameTextView = (TextView) shoppingListView.findViewById(R.id.shoppingListNameText);
        storeNameTextView = (TextView) shoppingListView.findViewById(R.id.storeNameText);
        tripDateTextView = (TextView) shoppingListView.findViewById(R.id.tripDateText);

        nameTextView.setText(shoppingList.getListName());
        storeNameTextView.setText(shoppingList.getStoreName());
        tripDateTextView.setText(shoppingList.getTripDate());

        return shoppingListView;

    }

}
