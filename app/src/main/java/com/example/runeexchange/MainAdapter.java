package com.example.runeexchange;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runeexchange.model.ItemAsData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> implements Filterable {

    private ArrayList<ItemAsData> data;
    private ArrayList<ItemAsData> dataHard;

    public MainAdapter(ArrayList<ItemAsData> items){
        data = items;
        dataHard = new ArrayList<>(items);
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

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    private Filter myFilter = new Filter(){
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ItemAsData> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(dataHard);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(ItemAsData item : dataHard){
                    if (item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data.clear();
            data.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

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
