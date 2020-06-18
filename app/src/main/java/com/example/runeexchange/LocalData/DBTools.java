package com.example.runeexchange.LocalData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBTools extends SQLiteOpenHelper {


    private static final String ITEM_TABLE = "ITEM_TABLE";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_ITEM_ID = "ITEM_ID";
    private static final String COLUMN_ITEM_NAME = "NAME";
    private static final String COLUMN_ITEM_CURRENT_PRICE = "CURRENT_PRICE";

    public DBTools(@Nullable Context context){
        super(context, "RuneExchange.db", null, 1);
    }

    public void getItemFromDB(){

    }

    public void updatePrice(){

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_query = "CREATE TABLE " + ITEM_TABLE + " (" + COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEM_ID + " INTEGER, " + COLUMN_ITEM_NAME + " TEXT, " + COLUMN_ITEM_CURRENT_PRICE + " INTEGER)";
        db.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
