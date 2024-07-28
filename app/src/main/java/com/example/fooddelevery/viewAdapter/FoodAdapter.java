package com.example.fooddelevery.viewAdapter;

import android.content.Context;
import android.content.Intent;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelevery.DeliveryDatabase.FoodItemsingledata;
import com.example.fooddelevery.FoodItem;
import com.example.fooddelevery.R;
import com.example.fooddelevery.SingleItemActivity;
import com.example.fooddelevery.modeldata.CardModel;
import com.example.fooddelevery.modeldata.DelightmainModel;
import com.example.fooddelevery.modeldata.MainViewModel;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private List<FoodItem> foodItems;

    public static Context context;

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);
        holder.bind(foodItem);
    }

    @Override
    public int getItemCount() {
        return foodItems == null ? 0 : foodItems.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, foodDescription, foodPrice, foodRating, foodDeliveryTime;
        ImageView foodImage;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            foodDescription = itemView.findViewById(R.id.foodDescription);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            foodRating = itemView.findViewById(R.id.foodRating);
            foodDeliveryTime = itemView.findViewById(R.id.foodDeliveryTime);
            foodImage = itemView.findViewById(R.id.foodImage);
        }

        public void bind(FoodItem foodItem) {
            foodName.setText(foodItem.getName());
            foodDescription.setText(foodItem.getDescription());
            foodPrice.setText("Rp " + foodItem.getPrice());
            foodRating.setText(foodItem.getRating() + " • " + foodItem.getRatingsCount() + "+ ratings");
            foodDeliveryTime.setText(foodItem.getDeliveryTime() + " min • " + foodItem.getDistance() + " km");
            loadImage(foodImage,foodItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, SingleItemActivity.class);
                    intent.putExtra("foodname",foodItem.getName());
                    intent.putExtra("price","Rp " + foodItem.getPrice());
                    intent.putExtra("food description",foodItem.getDescription());
                    intent.putExtra("Rating",foodItem.getRating());
                    intent.putExtra("RatingsCount",foodItem.getRatingsCount());
                    intent.putExtra("DeliveryTime",foodItem.getDeliveryTime());
                    intent.putExtra("imguri",foodItem.getImgurl());
                    context.startActivity(intent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                }
            });

        }
    }


    public static void loadImage(final ImageView imageView,FoodItem foodItem) {
        Executor executor = Executors.newSingleThreadExecutor();
        final Handler handler = new Handler(Looper.getMainLooper());
        final Bitmap[] image = new Bitmap[1];
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String imageURL = foodItem.getImgurl();

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
