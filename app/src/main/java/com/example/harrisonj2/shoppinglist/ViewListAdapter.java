package com.example.harrisonj2.shoppinglist;

import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

/**
 * Created by Alexander on 11/2/2015.
 */
public class ViewListAdapter extends ArrayAdapter<ShoppingList> {

    ShoppingList shoppingList;


    public ViewListAdapter(Context context, ShoppingList[] objects) {
        super(context, R.layout.custom_row, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View shoppingListView = inflater.inflate(R.layout.custom_row, parent, false);

        ShoppingList = getItem(position);


    }

}
