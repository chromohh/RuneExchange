package com.example.runeexchange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runeexchange.LocalData.DBTools;
import com.example.runeexchange.data.DataTools;
import com.example.runeexchange.model.ItemAsData;
import com.example.runeexchange.model.ItemAsFavourite;

import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {

    private List<ItemAsData> data;
    private List<ItemAsFavourite> favouriteData;
    private DBTools dbTools;
    private DataTools dataTools;

    private RecyclerView recyclerView;
    private FavouritesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        dataTools = new DataTools();
        dbTools = new DBTools(this);

        data = this.getIntent().getParcelableArrayListExtra("data");

        favouriteData = dbTools.getAllFavourites();
        favouriteData = dataTools.updateFavouriteItemPrices(data, favouriteData);
        Toolbar toolbar = findViewById(R.id.toolbar);
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
}
