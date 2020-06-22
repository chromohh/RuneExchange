package com.example.runeexchange.data;

import com.example.runeexchange.model.ItemAsData;
import com.example.runeexchange.model.ItemAsFavourite;

import java.util.List;

public class DataTools {

    private final static String BASE_URL = "https://api.runelite.net/runelite-1.6.20";

    public String getBASE_URL() {
        return BASE_URL;
    }

    public List<ItemAsFavourite> updateFavouriteItemPrices(List<ItemAsData> data, List<ItemAsFavourite> favouritedata){
        try{
        int i = 0;
        for(ItemAsFavourite item : favouritedata){
            for(ItemAsData item2 : data){
                if(item.getId() == Integer.getInteger(item2.getId())){
                    favouritedata.get(i).setCurrentPrice(Integer.getInteger(item2.getPrice()));
                }
            }
            i++;
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return favouritedata;
    }
}
