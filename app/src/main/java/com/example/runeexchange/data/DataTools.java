package com.example.runeexchange.data;

import android.util.Log;

import com.example.runeexchange.model.ItemAsData;
import com.example.runeexchange.model.ItemAsFavourite;

import java.util.List;

public class DataTools {

    private final static String BASE_URL = "https://api.runelite.net/runelite-1.6.20";

    public String getBASE_URL() {
        return BASE_URL;
    }

    public List<ItemAsFavourite> updateFavouriteItemPrices(List<ItemAsData> data, List<ItemAsFavourite> favouriteData){
        for(int i = 0; i < favouriteData.size(); i++ ){
            for(ItemAsData item2 : data){
                if(favouriteData.get(i).getName().equals(item2.getName())){
                    favouriteData.get(i).setCurrentPrice(Integer.parseInt(item2.getPrice()));
                }
            }
        }
        return favouriteData;
    }
}
