package com.example.runeexchange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.runeexchange.LocalData.DBTools;
import com.example.runeexchange.data.AcquireData;
import com.example.runeexchange.data.DataTools;
import com.example.runeexchange.model.ItemAsData;
import com.example.runeexchange.model.ItemAsFavourite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {

    private ArrayList<ItemAsData> data;
    private ArrayList<ItemAsFavourite> favouriteData;
    private DBTools dbTools;
    private DataTools dataTools;

    private RecyclerView recyclerView;
    private FavouritesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private RequestQueue requestQueue;
    private DataTools datatools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        data = new ArrayList<ItemAsData>();
        dataTools = new DataTools();
        dbTools = new DBTools(this);
        datatools = new DataTools();

        updateData();

        favouriteData = dbTools.getAllFavourites();

        Log.e("bas", favouriteData.toString());
        favouriteData = dataTools.updateFavouriteItemPrices(data, favouriteData);
        Toolbar toolbar = findViewById(R.id.toolbar);
        Log.e("bas", favouriteData.toString());
        setSupportActionBar(toolbar);
        buildRecycleView();
    }

    private void buildRecycleView(){
        layoutManager = new LinearLayoutManager(this);
        adapter = new FavouritesAdapter((ArrayList<ItemAsFavourite>) favouriteData);
        recyclerView = findViewById(R.id.recyclerViewFavourites);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new FavouritesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ItemAsFavourite currentPress = favouriteData.get(position);
                dbTools.deleteByItemId(currentPress);
                favouriteData.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        menu.findItem(R.id.menu_search).setVisible(false);
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
        }
        return super.onOptionsItemSelected(item);
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
