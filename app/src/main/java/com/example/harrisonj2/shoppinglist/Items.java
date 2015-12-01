package com.example.harrisonj2.shoppinglist;

/**
 * Created by harrisonj2 on 10/29/2015.
 */
public class Items {
    private int id, quantity;
    private String itemName, purchased;
    private double eCost;
    private int listId;

    public Items(int id, String itemName, int quantity, double eCost, String purchased, int listId){
        this.id = id;
        this.listId = listId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.eCost = eCost;
        this.purchased = purchased;
    }

    public String getPurchased() {
        return purchased;
    }

    public void setPurchased(String purchased) {
        this.purchased = purchased;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double geteCost() {
        return eCost;
    }

    public void seteCost(double eCost) {
        this.eCost = eCost;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }
}
