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
    TextView costTextView;
    TextView purchasedTextView;

    public ViewItemAdapter(Context context, Items[] objects){
        super(context, R.layout.item_rows, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View itemListView = inflater.inflate(R.layout.item_rows, parent, false);

        items = getItem(position);

        nameTextView = (TextView) itemListView.findViewById(R.id.itemNameText);
        quantityTextView = (TextView) itemListView.findViewById(R.id.itemQuantityText);
        costTextView = (TextView) itemListView.findViewById(R.id.itemCostText);
        purchasedTextView = (TextView) itemListView.findViewById(R.id.purchasedTextView);

        itemId = items.getId();
        nameTextView.setText(items.getItemName());
        quantityTextView.setText(String.valueOf(items.getQuantity()));
        Double cost = items.geteCost();
        costTextView.setText(cost.toString());
        purchasedTextView.setText(items.getPurchased());

        return itemListView;
    }
}
