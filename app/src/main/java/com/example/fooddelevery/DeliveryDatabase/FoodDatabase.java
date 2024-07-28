package com.example.fooddelevery.DeliveryDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FoodItemsingledata.class}, version = 1)
public abstract class FoodDatabase extends RoomDatabase {
    private static volatile FoodDatabase INSTANCE;

    public abstract FoodDao foodDao();

    public static FoodDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (FoodDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    FoodDatabase.class, "food_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}