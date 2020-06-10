package com.example.runeexchange;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.runeexchange.data.AcquireData;
import com.example.runeexchange.data.DataTools;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private DataTools datatools = new DataTools();
    private ProgressBar coolSpinning;
    private ArrayList<String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coolSpinning = findViewById(R.id.progressBar);

        updateData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //skapar menyn
        getMenuInflater().inflate(R.menu.top_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent startOptionIntent = new Intent(this, SettingActivity.class);
                startOptionIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startOptionIntent);
                break;
            case R.id.menu_favourite:
                Intent startFavouriteIntent = new Intent(this, FavouritesActivity.class);
                startFavouriteIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startFavouriteIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void updateData(){
        requestQueue = AcquireData.getInstance(this).getReqQueue();
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, datatools.getBASE_URL() + "/item/prices", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(getApplicationContext(), "data loaded", Toast.LENGTH_SHORT).show();
                        Log.e("Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        Toast.makeText(getApplicationContext(), "Couldnt load data", Toast.LENGTH_SHORT).show();
                        Log.e("volley error", e.toString());
                    }
                });

        requestQueue.add(getRequest);

    }
}



