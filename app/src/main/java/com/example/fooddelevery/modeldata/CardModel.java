package com.example.fooddelevery.modeldata;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fooddelevery.DeliveryDatabase.FoodItemsingledata;
import com.example.fooddelevery.DeliveryDatabase.FoodRepository;

import java.util.List;

public class CardModel extends AndroidViewModel {

    private FoodRepository repository;
    private LiveData<List<FoodItemsingledata>> allCartItems;
    private LiveData<List<FoodItemsingledata>> allFavoriteItems;

    public CardModel(@NonNull Application application) {
        super(application);
        repository = new FoodRepository(application);
        allCartItems = repository.getItemsByCategory("cart");
        allFavoriteItems = repository.getItemsByCategory("favorite");
    }

    public LiveData<List<FoodItemsingledata>> getAllCartItems() {
        return allCartItems;
    }

    public LiveData<List<FoodItemsingledata>> getAllFavoriteItems() {
        return allFavoriteItems;
    }

    public void insert(FoodItemsingledata foodItem) {
        repository.insert(foodItem);
        Log.d("ListData", foodItem.getName());
    }

    public void delete(FoodItemsingledata foodItem) {
        repository.delete(foodItem);
        Log.d("DatabaseData", "Deleted: " + foodItem.getName());
    }

    public void addFoodItemToCart(String name, String imguri,String price, String description, String rating, int ratingCount, int time) {
        FoodItemsingledata foodItem = new FoodItemsingledata();
        foodItem.setName(name);
        foodItem.setImguri(imguri);
        foodItem.setDescription(description);
        foodItem.setPrice(price);
        foodItem.setRating(rating);
        foodItem.setRatingsCount(ratingCount);
        foodItem.setDeliveryTime(time);
        foodItem.setDistance(2.5f);
        foodItem.setCategory("cart");
        insert(foodItem);
        Log.d("DatabaseData", foodItem.getName());
    }

    public void addFoodItemToFavorites(String name,String imguri, String price, String description, String rating, int ratingCount, int time) {
        FoodItemsingledata foodItem = new FoodItemsingledata();
        foodItem.setName(name);
        foodItem.setImguri(imguri);
        foodItem.setDescription(description);
        foodItem.setPrice(price);
        foodItem.setRating(rating);
        foodItem.setRatingsCount(ratingCount);
        foodItem.setDeliveryTime(time);
        foodItem.setDistance(2.5f);
        foodItem.setCategory("favorite");
        insert(foodItem);
        Log.d("DatabaseData", foodItem.getName());
    }
}
