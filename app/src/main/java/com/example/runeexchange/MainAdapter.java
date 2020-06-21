package com.example.runeexchange;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runeexchange.model.ItemAsData;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private ArrayList<ItemAsData> data;

    public MainAdapter(ArrayList<ItemAsData> items){
        data = items;
    }

    @NonNull
    @Override
    public MainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MyViewHolder holder, int position) {
        ItemAsData currentItem = data.get(position);
        String price = currentItem.getPrice() + " GP";
        holder.title.setText(currentItem.getName());
        holder.price.setText(price);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_name);
            price = itemView.findViewById(R.id.card_price);

        }
    }
}
