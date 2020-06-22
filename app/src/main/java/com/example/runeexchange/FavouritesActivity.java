package com.example.runeexchange;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        dataTools = new DataTools();
        dbTools = new DBTools(this);

        Bundle bundleObject = getIntent().getExtras();
        data = (ArrayList<ItemAsData>) bundleObject.getSerializable("data");

        favouriteData = dbTools.getAllFavourites();
        dataTools.updateFavouriteItemPrices(data, favouriteData);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
