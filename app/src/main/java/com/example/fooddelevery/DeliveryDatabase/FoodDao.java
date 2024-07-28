package com.example.fooddelevery.DeliveryDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodDao {

        @Insert
        void insert(FoodItemsingledata foodItem);

        @Update
        void update(FoodItemsingledata foodItem);
        @Delete
        void delete(FoodItemsingledata foodItem);

        @Query("SELECT * FROM food_table WHERE category = :category")
        LiveData<List<FoodItemsingledata>> getItemsByCategory(String category);

}

