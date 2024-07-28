package com.example.fooddelevery.viewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelevery.DeliveryDatabase.FoodItemsingledata;
import com.example.fooddelevery.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {

    private List<FoodItemsingledata> favoriteItems = new ArrayList<>();
    private CartAdapter.OnItemDeleteListener onItemDeleteListener;

    public interface OnItemDeleteListener {
        void onItemDelete(FoodItemsingledata item);
    }

    public void setOnItemDeleteListener(CartAdapter.OnItemDeleteListener listener) {
        this.onItemDeleteListener = listener;
    }

    public void setCartItems(List<FoodItemsingledata> favoriteItems) {
        this.favoriteItems = favoriteItems;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        if (favoriteItems != null) {
            FoodItemsingledata item = favoriteItems.remove(position);
            notifyItemRemoved(position);
            if (onItemDeleteListener != null) {
                onItemDeleteListener.onItemDelete(item);
            }
        }
    }
    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        FoodItemsingledata currentItem = favoriteItems.get(position);
        holder.tvFoodName.setText(currentItem.getName());
        holder.tvFoodDescription.setText(currentItem.getDescription());
        holder.tvFoodPrice.setText(currentItem.getPrice());
        holder.tvFoodRating.setText(currentItem.getRating() + " • " + currentItem.getRatingsCount() + " ratings");
        // Set the image using Glide or any other image loading library
         Glide.with(holder.itemView).load(currentItem.getImguri()).into(holder.ivFoodImage);
    }

    @Override
    public int getItemCount() {
        return favoriteItems.size();
    }

    public void setFavoriteItems(List<FoodItemsingledata> favoriteItems) {
        this.favoriteItems = favoriteItems;
        notifyDataSetChanged();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFoodImage;
        private TextView tvFoodName, tvFoodDescription, tvFoodPrice, tvFoodRating;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoodImage = itemView.findViewById(R.id.iv_food_image);
            tvFoodName = itemView.findViewById(R.id.tv_food_name);
            tvFoodDescription = itemView.findViewById(R.id.tv_food_description);
            tvFoodPrice = itemView.findViewById(R.id.tv_food_price);
            tvFoodRating = itemView.findViewById(R.id.tv_food_rating);
        }
    }
}
