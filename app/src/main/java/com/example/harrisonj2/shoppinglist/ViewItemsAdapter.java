package com.example.harrisonj2.shoppinglist;

/**
 * Created by Alexander on 11/9/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Alexander on 11/2/2015.
 */
public class ViewItemsAdapter extends ArrayAdapter<Items> {

    Items item;

    int listId;
    TextView nameTextView;
    TextView quantityTextView;
    TextView costTextView;



    public ViewItemsAdapter(Context context, Items[] objects) {
        super(context, R.layout.list_rows, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View itemsListView = inflater.inflate(R.layout.item_rows, parent, false);

        item = getItem(position);

        nameTextView = (TextView) itemsListView.findViewById(R.id.itemNameText);
        quantityTextView = (TextView) itemsListView.findViewById(R.id.itemQuantityText);
        costTextView = (TextView) itemsListView.findViewById(R.id.itemCostText);

        listId = item.getId();
        nameTextView.setText(item.getItemName());
        quantityTextView.setText(item.getQuantity());
        costTextView.setText(item.geteCost());

        return itemsListView;

    }

}
