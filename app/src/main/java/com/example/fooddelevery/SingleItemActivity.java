package com.example.fooddelevery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fooddelevery.DeliveryDatabase.FoodItemsingledata;
import com.example.fooddelevery.modeldata.CardModel;
import com.example.fooddelevery.modeldata.ImageSliderViewModel;
import com.example.fooddelevery.modeldata.SignUpModel;
import com.example.fooddelevery.viewAdapter.ImageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;



public class SingleItemActivity extends AppCompatActivity {
TextView itemname,itemprice;
Button addtocard;

    String foodName,price,description,imgUri;
    String rating;
    int ratingsCount;
    int deliveryTime;

    ImageView favourite,backpress;

    private ImageSliderViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);
        itemname=findViewById(R.id.textViewTitle);
        itemprice=findViewById(R.id.textViewPrice);
        addtocard=findViewById(R.id.buttonAddToCart);
        backpress=findViewById(R.id.backpress);
        favourite=findViewById(R.id.topCornerImage);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.indicator_layout);

        Intent intent = getIntent();
        if (intent != null) {
             foodName = intent.getStringExtra("foodname");
             price = intent.getStringExtra("price");
             description = intent.getStringExtra("food_description");
             rating = intent.getStringExtra("Rating");
             ratingsCount = intent.getIntExtra("RatingsCount", 0);
             deliveryTime = intent.getIntExtra("DeliveryTime",1);
             imgUri = intent.getStringExtra("imguri");
            itemname.setText(foodName);
            itemprice.setText(price);
        }

        viewModel = new ViewModelProvider(this).get(ImageSliderViewModel.class);

        viewModel.getImages().observe(this, images -> {
            ImageAdapter adapter = new ImageAdapter(images);
            viewPager.setAdapter(adapter);


            new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {

            }).attach();
        });


        addtocard.setOnClickListener(v->{
            CardModel model= new ViewModelProvider(this).get(CardModel.class);
            model.addFoodItemToCart(foodName,imgUri,price,description,rating,ratingsCount,deliveryTime);
            finish();
        });

        backpress.setOnClickListener(v->finish());
        favourite.setOnClickListener(v->{
            CardModel model= new ViewModelProvider(this).get(CardModel.class);
            model.addFoodItemToFavorites(foodName,imgUri,price,description,rating,ratingsCount,deliveryTime);
            favourite.setColorFilter(getResources().getColor(R.color.orange), PorterDuff.Mode.SRC_IN);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    favourite.clearColorFilter();
                }
            }, 2000);
        });
    }

}