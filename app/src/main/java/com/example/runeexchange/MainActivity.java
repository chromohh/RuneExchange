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
import com.example.runeexchange.model.ItemAsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private DataTools datatools = new DataTools();
    private ProgressBar coolSpinning;
    private ArrayList<ItemAsData> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coolSpinning = findViewById(R.id.progressBar);

        data =  new ArrayList<ItemAsData>();
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
                        for(int i=0; i<response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                ItemAsData temp = new ItemAsData(
                                        object.getString("id"),
                                        object.getString("name"),
                                        object.getString("price"));
                                data.add(temp);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        coolSpinning.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "data loaded", Toast.LENGTH_SHORT).show();
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



