package com.example.shoppingapphitproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoppingapphitproject.R;
import com.example.shoppingapphitproject.adapters.CartItemAdapter;
import com.example.shoppingapphitproject.adapters.ShopItemAdapter;
import com.example.shoppingapphitproject.dataTypes.StoreItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Shopping extends Fragment {
    private RecyclerView shopView;
    private RecyclerView cartView;
    private ShopItemAdapter shopItemAdapter;
    private CartItemAdapter cartItemAdapter;
    private LinearLayoutManager shopLayoutManager;
    private LinearLayoutManager cartLayoutManager;
    private TextView yourMoney;
    private TextView cartCost;
    private TextView noMoneyMsg;
    private Button buyBtn;
    private double totalMoney = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref =  db.getReference("customer");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);

        ArrayList<StoreItem> cart_items = new ArrayList<>();
        ArrayList<StoreItem> shop_items = new ArrayList<>();
        {
            shop_items.add(new StoreItem(){{
                Name = "Milk";
                Image = R.drawable.milk;
                Price = 1;
                Count = 0;
            }});
            shop_items.add(new StoreItem(){{
                Name = "Tomato";
                Image = R.drawable.tomato;
                Price = 2;
                Count = 0;
            }});
            shop_items.add(new StoreItem(){{
                Name = "Egg";
                Image = R.drawable.egg;
                Price = 3;
                Count = 0;
            }});
        }

        cartLayoutManager = new LinearLayoutManager(getContext());
        cartView = view.findViewById(R.id.cart);
        cartView.setLayoutManager(cartLayoutManager);
        cartView.setItemAnimator(new DefaultItemAnimator());
        cartItemAdapter = new CartItemAdapter(cart_items, this);
        cartView.setAdapter(cartItemAdapter);

        shopLayoutManager = new LinearLayoutManager(getContext());
        shopView = view.findViewById(R.id.shop);
        shopView.setLayoutManager(shopLayoutManager);
        shopView.setItemAnimator(new DefaultItemAnimator());
        shopItemAdapter = new ShopItemAdapter(shop_items, this);
        shopView.setAdapter(shopItemAdapter);

        yourMoney = view.findViewById(R.id.your_money);
        noMoneyMsg = view.findViewById(R.id.ur_poor);
        cartCost = view.findViewById(R.id.cart_cost);
        buyBtn = view.findViewById(R.id.buy);

        yourMoney.setText("Your account: "+totalMoney);
        cartCost.setText(CartCost() + "");
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalMoney -= CartCost();
                yourMoney.setText("Your Account: "+ totalMoney + "");
                Log.d("Shop", "You payed "+ CartCost()+ ", You have "+ totalMoney + " remaining on your account.");
                int shopStart = shopItemAdapter.getItemCount();
                int cartStart = cartItemAdapter.getItemCount();
                for(StoreItem item: cartItemAdapter.getItems()){
                    shopItemAdapter.getItems().add(item);
                }
                cartItemAdapter.getItems().clear();
                cartItemAdapter.notifyItemRangeRemoved(0, cartStart);
                shopItemAdapter.notifyItemRangeInserted(shopStart, shopItemAdapter.getItemCount() - 1);
            }
        });

        return view;
    }
    public double CartCost(){
        double sum = 0;
        for(StoreItem item: cartItemAdapter.getItems()){
            sum += item.Count * item.Price;
        }
        return sum;
    }
    public void UpdateCosts(){
        double total = CartCost();
        if(total > totalMoney){
            noMoneyMsg.setVisibility(View.VISIBLE);
            buyBtn.setEnabled(false);
        } else {
            noMoneyMsg.setVisibility(View.GONE);
            buyBtn.setEnabled(true);
        }
        cartCost.setText(total + "");
    }
    public void AddItemToCart(int shop_pos){
        StoreItem item = shopItemAdapter.getItems().get(shop_pos);
        item.Count = 1;
        shopItemAdapter.getItems().remove(shop_pos);
        shopItemAdapter.notifyItemRemoved(shop_pos);
        cartItemAdapter.getItems().add(item);
        cartItemAdapter.notifyItemInserted(cartItemAdapter.getItems().size() - 1);
        UpdateCosts();
    }
    public void RemoveItemFromCart(int cart_pos){
        StoreItem item = cartItemAdapter.getItems().get(cart_pos);
        cartItemAdapter.getItems().remove(cart_pos);
        cartItemAdapter.notifyItemRemoved(cart_pos);
        shopItemAdapter.getItems().add(item);
        shopItemAdapter.notifyItemInserted(shopItemAdapter.getItems().size() - 1);
        UpdateCosts();
    }
}