package com.example.runeexchange.LocalData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBTools extends SQLiteOpenHelper {


    private static final String ITEM_TABLE = "ITEM_TABLE";
    private static final String COLUMN_ITEM_ID = "id";
    private static final String COLUMN_ITEM_NAME = "name";
    private static final String COLUMN_ITEM_CURRENT_PRICE = "currentPrice";
    private static final String COlUMN_ITEM_PRICE_CHANGE = "priceChange";
    private static final String ITEM_DONE = "done";

    public DBTools(@Nullable Context context){
        super(context, "RuneExchange.db", null, 1);
    }

    public void getItemFromDB(){

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
