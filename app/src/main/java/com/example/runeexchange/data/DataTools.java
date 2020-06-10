package com.example.runeexchange.data;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class DataTools {
    private static DataTools instance;
    private RequestQueue reqQueue;

    private DataTools(Context context){
        reqQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized DataTools getInstance(Context context){
        if(instance == null){
            instance = new DataTools(context);
        }
        return instance;
    }
}
