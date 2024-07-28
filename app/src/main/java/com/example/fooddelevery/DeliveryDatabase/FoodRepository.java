package com.example.fooddelevery.DeliveryDatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FoodRepository {

    private FoodDao foodDao;
    private ExecutorService executorService;

    public FoodRepository(Application application) {
        FoodDatabase db = FoodDatabase.getInstance(application);
        foodDao = db.foodDao();
        executorService = Executors.newFixedThreadPool(2);
    }

    public LiveData<List<FoodItemsingledata>> getItemsByCategory(String category) {
        return foodDao.getItemsByCategory(category);
    }

    public void insert(FoodItemsingledata foodItem) {
        executorService.execute(() -> foodDao.insert(foodItem));
    }

    public void update(FoodItemsingledata foodItem) {
        executorService.execute(() -> foodDao.update(foodItem));
    }

    public void delete(FoodItemsingledata foodItem) {
        executorService.execute(() -> foodDao.delete(foodItem));
    }
}
