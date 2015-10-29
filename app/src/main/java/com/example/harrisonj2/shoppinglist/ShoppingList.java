package com.example.harrisonj2.shoppinglist;

/**
 * Created by harrisonj2 on 10/29/2015.
 */
public class ShoppingList {
    private int id;
    private String listName, storeName, tripDate;

    public ShoppingList(){
    }

    public ShoppingList(String listName, String storeName, String tripDate){
        this.listName = listName;
        this.storeName = storeName;
        this.tripDate = tripDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }
}
