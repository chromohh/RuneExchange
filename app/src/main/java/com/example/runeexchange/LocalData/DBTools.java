package com.example.runeexchange.LocalData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.runeexchange.model.ItemAsData;
import com.example.runeexchange.model.ItemAsFavourite;

import java.util.ArrayList;
import java.util.List;

public class DBTools extends SQLiteOpenHelper {


    private static final String ITEM_TABLE = "ITEM_TABLE";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_ITEM_ID = "ITEM_ID";
    private static final String COLUMN_ITEM_NAME = "NAME";
    private static final String COLUMN_ITEM_CURRENT_PRICE = "CURRENT_PRICE";

    public DBTools(@Nullable Context context){
        super(context, "RuneExchange.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_query = "CREATE TABLE " + ITEM_TABLE + " ( " + COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEM_ID + " INTEGER, " + COLUMN_ITEM_NAME + " TEXT, " + COLUMN_ITEM_CURRENT_PRICE + " INTEGER)";
        db.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addItemToDb (ItemAsFavourite item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITEM_ID, item.getId());
        cv.put(COLUMN_ITEM_NAME, item.getName());
        cv.put(COLUMN_ITEM_CURRENT_PRICE, item.getCurrentPrice());
        long insertStatus = db.insert(ITEM_TABLE,null,cv);
        if(insertStatus == -1){
            db.close();
            return false;
        }else{
            db.close();
            return true;
        }
    }


    public List<ItemAsFavourite> getAllFavourites(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<ItemAsFavourite> retList = new ArrayList<>();
        String query =  "SELECT * FROM " + ITEM_TABLE ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int itemId = cursor.getInt(1);
                String itemName = cursor.getString(2);
                int itemPrice = cursor.getInt(3);
                ItemAsFavourite ItemAsFavourite = new ItemAsFavourite(itemId, itemPrice, itemName);
                retList.add(ItemAsFavourite);
            }while(cursor.moveToNext());
        }else{
        }
        cursor.close();;
        db.close();
        return retList;
    }

    public boolean deleteByItemId(ItemAsFavourite itemToRemove){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(ITEM_TABLE,COLUMN_ITEM_ID + " = " + itemToRemove.getId(),null);

        return true;
    }

    public boolean convertDataItemToFavouriteItem(){
        return true;
    }




}
