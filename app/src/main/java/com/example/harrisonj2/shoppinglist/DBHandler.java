package com.example.harrisonj2.shoppinglist;

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

    private
}
