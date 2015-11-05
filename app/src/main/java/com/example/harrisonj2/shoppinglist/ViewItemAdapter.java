package com.example.harrisonj2.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by harrisonj2 on 11/5/2015.
 */
public class ViewItemAdapter extends ArrayAdapter<Items> {
    Items items;

    int itemId;
    TextView nameTextView;
    TextView quantityTextView;
    TextView estCostTextView;
    int listId;

    public ViewItemAdapter(Context context, Items[] objects){
        super(context, R.layout.item_row, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View itemListView = inflater.inflate(R.layout.item_row, parent, false);

        items = getItem(position);

        nameTextView = (TextView) itemListView.findViewById(R.id.itemNameTextView);
        quantityTextView = (TextView) itemListView.findViewById(R.id.quantityTextView);
        estCostTextView = (TextView) itemListView.findViewById(R.id.estCostTextView);

        itemId = items.getId();
        nameTextView.setText(items.getItemName());
        quantityTextView.setText(items.getQuantity());
        estCostTextView.setText(items.geteCost());
        listId = items.getListId();

        return itemListView;
    }
}
