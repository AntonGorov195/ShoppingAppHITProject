package com.example.shoppingapphitproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapphitproject.R;
import com.example.shoppingapphitproject.dataTypes.StoreItem;
import com.example.shoppingapphitproject.fragments.Shopping;

import java.util.ArrayList;

public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemAdapter.myViewHolder> {
    private ArrayList<StoreItem> shopItems;
    private Shopping fragment;

    public ShopItemAdapter(ArrayList<StoreItem> shopItems, Shopping fragment) {
        this.shopItems = shopItems;
        this.fragment = fragment;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView price;
        Button add;
        View parent;

        public myViewHolder(View itemView) {
            super(itemView);
            parent = itemView;
            name = itemView.findViewById(R.id.shop_item_name);
            price = itemView.findViewById(R.id.shop_item_price);
            image = itemView.findViewById(R.id.shop_item_image);
            add = itemView.findViewById(R.id.add_item_to_cart);
        }

    }

    @NonNull
    @Override
    public ShopItemAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
        myViewHolder MyViewHolder = new myViewHolder(view);
        return MyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopItemAdapter.myViewHolder holder, int position) {
        StoreItem item = shopItems.get(position);
        holder.name.setText(item.Name);
        holder.price.setText(item.Price + "");
        holder.image.setImageResource(item.Image);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.AddItemToCart(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopItems.size();
    }
    public ArrayList<StoreItem> getItems(){
        return  shopItems;
    }
}
