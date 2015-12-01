package com.example.harrisonj2.shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by harrisonj2 on 10/29/2015.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "shopppingList.db";
    private static final String TABLE_LIST = "list";
    private static final String TABLE_ITEM = "item";

    private static final String COLUMN_LISTID = "list_id";
    private static final String COLUMN_LISTNAME = "listName";
    private static final String COLUMN_STORENNAME = "storeName";
    private static final String COLUMN_TRIPDATE = "date";

    private static final String COLUMN_ITEMID = "item_id";
    private static final String COLUMN_ITEMNAME = "itemName";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_ECOST = "estimatedCost";
    private static final String COLUMN_GOTTEN = "itemPurchased";

    private ShoppingList[] listData;
    private Items[] itemData;

    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_LIST + "(" +
                COLUMN_LISTID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LISTNAME + " TEXT, " +
                COLUMN_STORENNAME + " TEXT, " +
                COLUMN_TRIPDATE + " TEXT " +
                ");";
        String query2 = "CREATE TABLE " + TABLE_ITEM + "(" +
                COLUMN_ITEMID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ITEMNAME + " TEXT, " +
                COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_ECOST + " DECIMAL(10,2), " +
                COLUMN_GOTTEN + " TEXT, " +
                COLUMN_LISTID + " INTEGER " +
                /*"FOREIGN KEY (" + COLUMN_LISTID + ")" +
                "REFERENCES " + TABLE_LIST + "(" + COLUMN_LISTID + ")" +
                "ON DELETE CASCADE " +*/
                ");";

        db.execSQL(query);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST + ";");
        onCreate(db);
    }

    public void addList(String listName, String storeName, long tripDate){
        ContentValues value = new ContentValues();
        value.put(COLUMN_LISTNAME, listName);
        value.put(COLUMN_STORENNAME, storeName);
        value.put(COLUMN_TRIPDATE, tripDate);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_LIST, null, value);
        db.close();
    }

    public void addItem(String itemName, int quantity, double eCost, int listId){
        ContentValues value = new ContentValues();
        value.put(COLUMN_ITEMNAME, itemName);
        value.put(COLUMN_QUANTITY, quantity);
        value.put(COLUMN_ECOST, eCost);
        value.put(COLUMN_GOTTEN, "false");
        value.put(COLUMN_LISTID, listId);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ITEM, null, value);
        db.close();
    }

    public ShoppingList[] getShoppingLists() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_LIST + ";";
        Cursor cur = db.rawQuery(query, null);
        int numOfLists = cur.getCount();

        if (numOfLists >= 1) {
            listData = new ShoppingList[numOfLists];
            int i = 0;
            cur.moveToFirst();
            while (!cur.isAfterLast()) {
                listData[i] = new ShoppingList(cur.getInt(cur.getColumnIndex(COLUMN_LISTID)),
                        cur.getString(cur.getColumnIndex(COLUMN_LISTNAME)),
                        cur.getString(cur.getColumnIndex(COLUMN_STORENNAME)),
                        cur.getString(cur.getColumnIndex(COLUMN_TRIPDATE)));
                cur.moveToNext();
                i++;
            }
        }
        db.close();
        return listData;
    }

    public ShoppingList[] getListName() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_LIST +
                " ORDER BY " + COLUMN_LISTNAME + " ASC;";
        Cursor cur = db.rawQuery(query, null);
        int numOfLists = cur.getCount();

        if (numOfLists >= 1) {
            listData = new ShoppingList[numOfLists];
            int i = 0;
            cur.moveToFirst();
            while (!cur.isAfterLast()) {
                listData[i] = new ShoppingList(cur.getInt(cur.getColumnIndex(COLUMN_LISTID)),
                        cur.getString(cur.getColumnIndex(COLUMN_LISTNAME)),
                        cur.getString(cur.getColumnIndex(COLUMN_STORENNAME)),
                        cur.getString(cur.getColumnIndex(COLUMN_TRIPDATE)));
                cur.moveToNext();
                i++;
            }
        }
        db.close();
        return listData;
    }

    public ShoppingList[] getListStore() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_LIST +
                " ORDER BY " + COLUMN_STORENNAME + " ASC;";
        Cursor cur = db.rawQuery(query, null);
        int numOfLists = cur.getCount();

        if (numOfLists >= 1) {
            listData = new ShoppingList[numOfLists];
            int i = 0;
            cur.moveToFirst();
            while (!cur.isAfterLast()) {
                listData[i] = new ShoppingList(cur.getInt(cur.getColumnIndex(COLUMN_LISTID)),
                        cur.getString(cur.getColumnIndex(COLUMN_LISTNAME)),
                        cur.getString(cur.getColumnIndex(COLUMN_STORENNAME)),
                        cur.getString(cur.getColumnIndex(COLUMN_TRIPDATE)));
                cur.moveToNext();
                i++;
            }
        }
        db.close();
        return listData;
    }

    public ShoppingList[] getListDate() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_LIST +
                " ORDER BY " + COLUMN_TRIPDATE + " ASC;";
        Cursor cur = db.rawQuery(query, null);
        int numOfLists = cur.getCount();

        if (numOfLists >= 1) {
            listData = new ShoppingList[numOfLists];
            int i = 0;
            cur.moveToFirst();
            while (!cur.isAfterLast()) {
                listData[i] = new ShoppingList(cur.getInt(cur.getColumnIndex(COLUMN_LISTID)),
                        cur.getString(cur.getColumnIndex(COLUMN_LISTNAME)),
                        cur.getString(cur.getColumnIndex(COLUMN_STORENNAME)),
                        cur.getString(cur.getColumnIndex(COLUMN_TRIPDATE)));
                cur.moveToNext();
                i++;
            }
        }
        db.close();
        return listData;
    }

    public Items[] getItems(int listId){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ITEM +
                " WHERE " + COLUMN_LISTID + " = " + listId;

        Cursor c = db.rawQuery(query, null);
        int numOfItems = c.getCount();

        if(numOfItems >= 1){
            itemData = new Items[numOfItems];
            int i = 0;
            c.moveToFirst();
            while(!c.isAfterLast()){
                itemData[i] = new Items(c.getInt(c.getColumnIndex(COLUMN_ITEMID)),
                        c.getString(c.getColumnIndex(COLUMN_ITEMNAME)),
                        c.getInt(c.getColumnIndex(COLUMN_QUANTITY)),
                        c.getDouble(c.getColumnIndex(COLUMN_ECOST)),
                        c.getString(c.getColumnIndex(COLUMN_GOTTEN)),
                        c.getInt(c.getColumnIndex(COLUMN_LISTID)));

                c.moveToNext();
                i++;
            }
        }
        db.close();

        return itemData;
    }

    public Items[] getItemsName(int listId){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ITEM +
                " WHERE " + COLUMN_LISTID + " = " + listId +
                " ORDER BY " + COLUMN_ITEMNAME + " ASC;";

        Cursor c = db.rawQuery(query, null);
        int numOfItems = c.getCount();

        if(numOfItems >= 1){
            itemData = new Items[numOfItems];
            int i = 0;
            c.moveToFirst();
            while(!c.isAfterLast()){
                itemData[i] = new Items(c.getInt(c.getColumnIndex(COLUMN_ITEMID)),
                        c.getString(c.getColumnIndex(COLUMN_ITEMNAME)),
                        c.getInt(c.getColumnIndex(COLUMN_QUANTITY)),
                        c.getDouble(c.getColumnIndex(COLUMN_ECOST)),
                        c.getString(c.getColumnIndex(COLUMN_GOTTEN)),
                        c.getInt(c.getColumnIndex(COLUMN_LISTID)));

                c.moveToNext();
                i++;
            }
        }
        db.close();

        return itemData;
    }

    public Items[] getItemsQuantity(int listId){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ITEM +
                " WHERE " + COLUMN_LISTID + " = " + listId +
                " ORDER BY " + COLUMN_QUANTITY + " ASC;";

        Cursor c = db.rawQuery(query, null);
        int numOfItems = c.getCount();

        if(numOfItems >= 1){
            itemData = new Items[numOfItems];
            int i = 0;
            c.moveToFirst();
            while(!c.isAfterLast()){
                itemData[i] = new Items(c.getInt(c.getColumnIndex(COLUMN_ITEMID)),
                        c.getString(c.getColumnIndex(COLUMN_ITEMNAME)),
                        c.getInt(c.getColumnIndex(COLUMN_QUANTITY)),
                        c.getDouble(c.getColumnIndex(COLUMN_ECOST)),
                        c.getString(c.getColumnIndex(COLUMN_GOTTEN)),
                        c.getInt(c.getColumnIndex(COLUMN_LISTID)));

                c.moveToNext();
                i++;
            }
        }
        db.close();

        return itemData;
    }

    public Items[] getItemsCost(int listId){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ITEM +
                " WHERE " + COLUMN_LISTID + " = " + listId +
                " ORDER BY " + COLUMN_ECOST + " ASC;";

        Cursor c = db.rawQuery(query, null);
        int numOfItems = c.getCount();

        if(numOfItems >= 1){
            itemData = new Items[numOfItems];
            int i = 0;
            c.moveToFirst();
            while(!c.isAfterLast()){
                itemData[i] = new Items(c.getInt(c.getColumnIndex(COLUMN_ITEMID)),
                        c.getString(c.getColumnIndex(COLUMN_ITEMNAME)),
                        c.getInt(c.getColumnIndex(COLUMN_QUANTITY)),
                        c.getDouble(c.getColumnIndex(COLUMN_ECOST)),
                        c.getString(c.getColumnIndex(COLUMN_GOTTEN)),
                        c.getInt(c.getColumnIndex(COLUMN_LISTID)));

                c.moveToNext();
                i++;
            }
        }
        db.close();

        return itemData;
    }

    public Items[] getNotGottenItems(int listId) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ITEM +
                " WHERE " + COLUMN_LISTID + " = " + listId + " AND " + COLUMN_GOTTEN + " = 'false'";

        Cursor c = db.rawQuery(query, null);
        int numOfItems = c.getCount();

        if(numOfItems >= 1){
            itemData = new Items[numOfItems];
            int i = 0;
            c.moveToFirst();
            while(!c.isAfterLast()){
                itemData[i] = new Items(c.getInt(c.getColumnIndex(COLUMN_ITEMID)),
                        c.getString(c.getColumnIndex(COLUMN_ITEMNAME)),
                        c.getInt(c.getColumnIndex(COLUMN_QUANTITY)),
                        c.getDouble(c.getColumnIndex(COLUMN_ECOST)),
                        c.getString(c.getColumnIndex(COLUMN_GOTTEN)),
                        c.getInt(c.getColumnIndex(COLUMN_LISTID)));

                c.moveToNext();
                i++;
            }
        }
        db.close();

        return itemData;
    }

    public void deleteShoppingList(int id) {

        String query = "DELETE FROM " + TABLE_LIST + " WHERE " + COLUMN_LISTID + " = " + id;

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);

        db.close();
    }

    public void deleteItem(int id) {

        String query = "DELETE FROM " + TABLE_ITEM + " WHERE " + COLUMN_ITEMID + " = " + id;

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);

        db.close();
    }

    public void gotItem(int id){
        String query = "UPDATE " + TABLE_ITEM + " SET " + COLUMN_GOTTEN + " = \"true\" " + " WHERE " +
                COLUMN_ITEMID + " = " + id;

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
}
