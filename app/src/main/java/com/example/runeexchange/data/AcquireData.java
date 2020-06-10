package com.example.runeexchange.data;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AcquireData {
    private static AcquireData instance;
    private RequestQueue reqQueue;

    private AcquireData(Context context){
        reqQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized AcquireData getInstance(Context context){
        if(instance == null){
            instance = new AcquireData(context);
        }
        return instance;
    }

    public RequestQueue getReqQueue(){
        return reqQueue;
    }
}
