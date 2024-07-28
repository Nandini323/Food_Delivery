package com.example.fooddelevery.viewAdapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelevery.DeliveryDatabase.FoodItemsingledata;
import com.example.fooddelevery.FoodItem;
import com.example.fooddelevery.R;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<FoodItemsingledata> cartItems;
    private OnItemDeleteListener onItemDeleteListener;

    public interface OnItemDeleteListener {
        void onItemDelete(FoodItemsingledata item);
    }

    public void setOnItemDeleteListener(OnItemDeleteListener listener) {
        this.onItemDeleteListener = listener;
    }

    public void setCartItems(List<FoodItemsingledata> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        if (cartItems != null) {
            FoodItemsingledata item = cartItems.remove(position);
            notifyItemRemoved(position);
            if (onItemDeleteListener != null) {
                onItemDeleteListener.onItemDelete(item);
            }
        }
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_card_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        FoodItemsingledata item = cartItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return cartItems != null ? cartItems.size() : 0;
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        private  CircleImageView ivFoodImage;
        private final TextView tvFoodName;
        private final TextView tvFoodPrice;
        private final TextView tvFoodQuantity;


        @SuppressLint("WrongViewCast")
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoodImage = itemView.findViewById(R.id.iv_food_image);
            tvFoodName = itemView.findViewById(R.id.tv_food_name);
            tvFoodPrice = itemView.findViewById(R.id.tv_food_price);
            tvFoodQuantity = itemView.findViewById(R.id.tv_food_quantity);
        }

        public void bind(FoodItemsingledata item) {
            tvFoodName.setText(item.getName());
            tvFoodPrice.setText(item.getPrice());
            tvFoodQuantity.setText(item.getRating());
            loadImage(ivFoodImage,item);
             Glide.with(ivFoodImage.getContext()).load(item.getImguri()).into(ivFoodImage);
        }
    }

    public static void loadImage(final ImageView imageView, FoodItemsingledata foodItem) {
        Executor executor = Executors.newSingleThreadExecutor();
        final Handler handler = new Handler(Looper.getMainLooper());
        final Bitmap[] image = new Bitmap[1];
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String imageURL = foodItem.getImguri();

                try {
                    InputStream inputStream = new java.net.URL(imageURL).openStream();
                    image[0] = BitmapFactory.decodeStream(inputStream);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(image[0]);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
