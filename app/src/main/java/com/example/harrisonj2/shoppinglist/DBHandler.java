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
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "shopppingList.db";
    private static final String TABLE_LIST = "list";
    private static final String TABLE_ITEM = "item";

    private static final String COLUMN_LISTID = "_id";
    private static final String COLUMN_LISTNAME = "listName";
    private static final String COLUMN_STORENNAME = "storeName";
    private static final String COLUMN_TRIPDATE = "date";

    private static final String COLUMN_ITEMID = "_id";
    private static final String COLUMN_ITEMNAME = "itemName";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_ECOST = "estimatedCost";

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
                COLUMN_QUANTITY + " TEXT, " +
                COLUMN_ECOST + " TEXT " +
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
        db.execSQL("DROP TABLE IF EXIST " + TABLE_ITEM + ", " + TABLE_LIST + ";");
        onCreate(db);
    }

    public void addList(String listName, String storeName, String tripDate){
        ContentValues value = new ContentValues();
        value.put(COLUMN_LISTNAME, listName);
        value.put(COLUMN_STORENNAME, storeName);
        value.put(COLUMN_TRIPDATE, tripDate);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_LIST, null, value);
        db.close();
    }

    public void addItem(String itemName, String quantity, String eCost, int listId){
        ContentValues value = new ContentValues();
        value.put(COLUMN_ITEMNAME, itemName);
        value.put(COLUMN_QUANTITY, quantity);
        value.put(COLUMN_ECOST, eCost);
        value.put(COLUMN_LISTID, listId);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ITEM, null, value);
        db.close();
    }

    public ShoppingList[] getShoppingLists() {
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_LIST + ";";

        Cursor c = db.rawQuery(query, null);

        int numOfLists = c.getCount();

        if (numOfLists >= 1) {

            listData = new ShoppingList[numOfLists];
            int i = 0;
            c.moveToFirst();

            while (!c.isAfterLast()) {
                listData[i] = new ShoppingList(c.getString(c.getColumnIndex(COLUMN_LISTNAME)),
                        c.getString(c.getColumnIndex(COLUMN_STORENNAME)),
                        c.getString(c.getColumnIndex(COLUMN_TRIPDATE)));

                c.moveToNext();
                i++;
            }


        }

        return listData;

    }
}
