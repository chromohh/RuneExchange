package com.example.runeexchange;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runeexchange.model.ItemAsFavourite;

import java.util.ArrayList;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.MyViewHolder> {

    private OnItemClickListener listener;
    private ArrayList<ItemAsFavourite> favouriteData;

    public FavouritesAdapter(ArrayList<ItemAsFavourite> items){
        favouriteData = items;
    }

    @NonNull
    @Override
    public FavouritesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item, parent, false);
        MyViewHolder mvh = new MyViewHolder(v, listener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesAdapter.MyViewHolder holder, int position) {
        ItemAsFavourite currentItem = favouriteData.get(position);

        String price = String.valueOf(currentItem.getCurrentPrice()) + " GP";
        String priceWhenBought = String.valueOf(currentItem.getPriceWhenAdded()) + " GP";

        holder.title.setText(currentItem.getName());
        holder.change.setText(currentItem.getChange());
        holder.priceBoughtAt.setText(priceWhenBought);
        holder.price.setText(price);
    }

    @Override
    public int getItemCount() {
        return favouriteData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView price;
        private TextView change;
        private TextView priceBoughtAt;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.favourite_name);
            price = itemView.findViewById(R.id.favourite_price);
            change = itemView.findViewById(R.id.favourite_change);
            priceBoughtAt = itemView.findViewById(R.id.favourite_price_bought_at);

            itemView.findViewById(R.id.favourite_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public  void setOnItemClickListener(OnItemClickListener lisen){
        listener = lisen;
    }
}
