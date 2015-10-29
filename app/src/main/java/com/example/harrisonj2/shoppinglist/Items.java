package com.example.harrisonj2.shoppinglist;

/**
 * Created by harrisonj2 on 10/29/2015.
 */
public class Items {
    private int id;
    private String itemName, quantity, eCost;
    private int listId;

    public Items(int listId){
        this.listId = listId;
    }

    public Items(int listId, String itemName, String quantity, String eCost){
        this.listId = listId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.eCost = eCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String geteCost() {
        return eCost;
    }

    public void seteCost(String eCost) {
        this.eCost = eCost;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }
}
