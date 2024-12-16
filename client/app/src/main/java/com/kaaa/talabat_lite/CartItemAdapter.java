package com.kaaa.talabat_lite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    // Inner class for cart item data
    protected static class cartItemData {
        int id;
        int itemCount;
        public String itemName;
        public String merchName;
        public float itemPrice;
        public float itemTotalPrice;
        public Bitmap itemImage;

        public cartItemData(int id, int itemCount, String itemName, String merchName, float itemPrice, float itemTotalPrice, Bitmap itemImage) {
            this.id = id;
            this.itemCount = itemCount;
            this.itemName = itemName;
            this.merchName = merchName;
            this.itemPrice = itemPrice;
            this.itemTotalPrice = itemTotalPrice;
            this.itemImage = itemImage;
        }
    }

    private List<cartItemData> cartItemList;
    private WeakReference<Context> contextReference;

    public CartItemAdapter(Context context, List<cartItemData> cartItemList) {
        // Ensure the list is not null to avoid crashes
        this.cartItemList = cartItemList != null ? cartItemList : new ArrayList<>();
        this.contextReference = new WeakReference<>(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_row, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (cartItemList == null || position >= cartItemList.size()) {
            Log.e("CartItemAdapter", "Invalid position or null cart item list");
            return;
        }

        cartItemData cartItem = cartItemList.get(position);
        // Set item details
        holder.itemName.setText(cartItem.itemName);
        holder.itemMerch.setText(cartItem.merchName);
        holder.itemCount.setText(String.valueOf(cartItem.itemCount));
        holder.totalPrice.setText(String.format("%.1f", cartItem.itemTotalPrice));
        holder.itemPrice.setText(String.format("%.1f", cartItem.itemPrice));

        // Handle item image
        if (cartItem.itemImage != null) {
            holder.itemImage.setImageBitmap(cartItem.itemImage);
        } else {
            Log.w("CartItemAdapter", "Null image for item: " + cartItem.itemName);
            holder.itemImage.setImageResource(R.drawable.profile_icon); // Default image
        }

        // Item image click listener
        holder.itemImage.setOnClickListener(v -> {
            Context context = contextReference.get();
            if (context != null) {
                Intent viewItem = new Intent(context, ItemActivity.class);
                viewItem.putExtra("id", cartItem.id);
                context.startActivity(viewItem);
            }
        });

        // Remove item button listener
        holder.btnRemoveItem.setOnClickListener(v -> {
            Log.d("CartItemAdapter", "Remove button clicked for item: " + cartItem.itemName);
            // Implement removal logic here
            // Example: Remove from list and notify adapter
            cartItemList.remove(position);
            notifyItemRemoved(position);
            // Optionally update the cart count and total in CartActivity
        });
    }

    @Override
    public int getItemCount() {
        if (cartItemList == null) {
            Log.e("CartItemAdapter", "Cart item list is null");
            return 0;
        }
        return cartItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemMerch, itemName, itemPrice, itemCount, totalPrice;
        ImageView itemImage;
        Button btnRemoveItem;

        public ViewHolder(View cartItemView) {
            super(cartItemView);
            itemName = cartItemView.findViewById(R.id.cartItemName);
            itemMerch = cartItemView.findViewById(R.id.cartItemMerch);
            itemPrice = cartItemView.findViewById(R.id.cartItemPrice);
            itemImage = cartItemView.findViewById(R.id.cartItemImage);
            totalPrice = cartItemView.findViewById(R.id.cartItemTotalPrice);
            btnRemoveItem = cartItemView.findViewById(R.id.btnRemoveCartItem);
        }
    }
}