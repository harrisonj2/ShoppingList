package com.example.harrisonj2.shoppinglist;

import android.content.Context;
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
                "); \n" +
                "CREATE TABLE " + TABLE_ITEM + "(" +
                COLUMN_ITEMID + " INTEGER PRIMARY KEY AUTOCREMENT, " +
                COLUMN_ITEMNAME + " TEXT, " +
                COLUMN_QUANTITY + " TEXT, " +
                COLUMN_ECOST + " TEXT " +
                COLUMN_LISTID + " INTEGER FOREIGN KEY" +
                ");";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_ITEM + ", " + TABLE_LIST + ";");
        onCreate(db);
    }
}
