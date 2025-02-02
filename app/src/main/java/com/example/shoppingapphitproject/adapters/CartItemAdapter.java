package com.example.shoppingapphitproject.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapphitproject.R;
import com.example.shoppingapphitproject.dataTypes.StoreItem;
import com.example.shoppingapphitproject.fragments.Shopping;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.myViewHolder> {
    private ArrayList<StoreItem> cartItems;
    private Shopping fragment;

    public CartItemAdapter(ArrayList<StoreItem> cartItems, Shopping fragment) {
        this.cartItems = cartItems;
        this.fragment = fragment;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        ImageView image;
        EditText count;
        Button remove;
        View parent;

        public myViewHolder(View itemView) {
            super(itemView);
            parent = itemView;
            name = itemView.findViewById(R.id.cart_item_name);
            price = itemView.findViewById(R.id.cart_item_price);
            image = itemView.findViewById(R.id.cart_item_image);
            count = itemView.findViewById(R.id.cart_item_count);
            remove = itemView.findViewById(R.id.remove_item);
        }
    }

    @NonNull
    @Override
    public CartItemAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        myViewHolder MyViewHolder = new myViewHolder(view);
        return MyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.myViewHolder holder, int position) {
        StoreItem item = cartItems.get(position);
        holder.name.setText(item.Name);
        holder.price.setText(item.Price + "");
        holder.count.setText(item.Count + "");
        holder.image.setImageResource(item.Image);
        holder.count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fragment.UpdateCosts();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fragment.UpdateCosts();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String countText = editable.toString();
                if(countText.isEmpty()){
                    item.Count = 0;
                }else{
                    item.Count = Integer.parseInt(countText);
                }
                fragment.UpdateCosts();
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.RemoveItemFromCart(holder.getAdapterPosition());
            }
        });
    }
    @Override
    public int getItemCount() {
        return cartItems.size();
    }
    public ArrayList<StoreItem> getItems(){
        return  cartItems;
    }
}
