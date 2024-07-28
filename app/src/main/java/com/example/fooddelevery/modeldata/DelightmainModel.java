package com.example.fooddelevery.modeldata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelevery.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class DelightmainModel extends ViewModel {
    private MutableLiveData<List<FoodItem>> foodItems;
    private MutableLiveData<List<FoodItem>> drinkItems;
    private MutableLiveData<List<FoodItem>> snacksItems;
    private MutableLiveData<List<FoodItem>> sauceItems;
String img="https://images.unsplash.com/photo-1549128247-37e905ebdb3f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80";
    public LiveData<List<FoodItem>> getFoodItems() {
        if (foodItems == null) {
            foodItems = new MutableLiveData<>();
            loadFoodItems();
        }
        return foodItems;
    }

    public LiveData<List<FoodItem>> getdrinkItems() {
        if (drinkItems == null) {
            drinkItems = new MutableLiveData<>();
            loaddrinkItems();
        }
        return drinkItems;
    }
 public LiveData<List<FoodItem>> getsnacksItems() {
        if (snacksItems == null) {
            snacksItems = new MutableLiveData<>();
            loadsnacksItems();
        }
        return snacksItems;
    }
 public LiveData<List<FoodItem>> getsauceItems() {
        if (sauceItems == null) {
            sauceItems = new MutableLiveData<>();
            loadsauceItems();
        }
        return sauceItems;
    }

    private void loadFoodItems() {
        // Fetch data from repository or API
        List<FoodItem> items = fetchFoodItems();
        foodItems.setValue(items);
    }

    private void loaddrinkItems() {
        // Fetch data from repository or API
        List<FoodItem> items = fetchdrinkItems();
        drinkItems.setValue(items);
    }
    private void loadsnacksItems() {
            // Fetch data from repository or API
            List<FoodItem> items = fetchsnackItems();
            snacksItems.setValue(items);
        }
    private void loadsauceItems() {
            // Fetch data from repository or API
            List<FoodItem> items = fetchsauceItems();
            sauceItems.setValue(items);
        }

    private List<FoodItem> fetchFoodItems() {
        // Dummy data for illustration
        List<FoodItem> items = new ArrayList<>();
        items.add(new FoodItem("Buttergarlic nan","https://www.tasteatlas.com/images/dishes/bb78aadeae4e4a1c87b3843d8120aa9a.jpg?mw=1300", "Porridge, Rice, Chicken", 10000, 12, 1.0, 4.9, 400, true, false));
        items.add(new FoodItem("Amritsari kulcha","https://www.tasteatlas.com/Images/Dishes/dec410f7acfa475bbf94668ba691d96f.jpg?mw=1300", "Satay, Chicken, Meat", 10000, 20, 1.2, 4.7, 200, false, true));
        items.add(new FoodItem("Butter chicken","https://www.tasteatlas.com/images/dishes/713e5f01cbb2463386be9bc57c037639.jpg?mw=1300", "Fried Rice, Chicken", 12000, 15, 0.8, 4.8, 350, true, false));
        items.add(new FoodItem("Shahi paneer","https://www.tasteatlas.com/images/dishes/ceff65c140064d6285fc3814534a349b.jpg?mw=1300", "Meatball, Noodles", 15000, 10, 1.5, 4.6, 500, false, true));
        items.add(new FoodItem("Dal tadka","https://www.tasteatlas.com/images/dishes/617f5d93003c43a992244eff2dd52b6d.jpg?mw=1300", "Spicy Chicken, Rice", 11000, 18, 0.9, 4.9, 250, true, true));
        items.add(new FoodItem("Misal","https://www.tasteatlas.com/Images/Dishes/181106e0dc5249ac8f8089098048cc02.jpg?mw=1300", "Soup, Rice, Beef", 14000, 25, 1.4, 4.5, 300, false, false));
        return items;
    }


    private List<FoodItem> fetchdrinkItems() {
        // Dummy data for illustration
        List<FoodItem> items = new ArrayList<>();
        items.add(new FoodItem("Orange juice","https://www.thehealthy.com/wp-content/uploads/2019/09/GettyImages-825882916.jpg?fit=700,700","Porridge, Rice, Chicken", 10000, 12, 1.0, 4.9, 400, true, false));
        items.add(new FoodItem("Lemonade","https://www.thehealthy.com/wp-content/uploads/2019/09/GettyImages-832033616.jpg?fit=700,700", "Satay, Chicken, Meat", 10000, 20, 1.2, 4.7, 200, false, true));
        items.add(new FoodItem("Grape juice","https://www.thehealthy.com/wp-content/uploads/2019/09/GettyImages-459366433.jpg?fit=700,700", "Fried Rice, Chicken", 12000, 15, 0.8, 4.8, 350, true, false));
        items.add(new FoodItem("Cranberry juice","https://www.thehealthy.com/wp-content/uploads/2019/09/GettyImages-1193255058.jpg?fit=700,700", "Meatball, Noodles", 15000, 10, 1.5, 4.6, 500, false, true));
        items.add(new FoodItem("Vegetable juice","https://www.thehealthy.com/wp-content/uploads/2019/09/GettyImages-800866722.jpg?fit=700,700", "Spicy Chicken, Rice", 11000, 18, 0.9, 4.9, 250, true, true));
        items.add(new FoodItem("Prune juice","https://www.thehealthy.com/wp-content/uploads/2019/09/GettyImages-1135703988.jpg?fit=700,700", "Soup, Rice, Beef", 14000, 25, 1.4, 4.5, 300, false, false));
       return items;
    }
    private List<FoodItem> fetchsnackItems() {
        // Dummy data for illustration
        List<FoodItem> items = new ArrayList<>();
        items.add(new FoodItem("Dough-Based Snacks","https://www.lacademie.com/wp-content/uploads/2022/05/donuts-chocolate.webp", "Porridge, Rice, Chicken", 10000, 12, 1.0, 4.9, 400, true, false));
        items.add(new FoodItem("Cookies","https://www.lacademie.com/wp-content/uploads/2022/05/cookies-chocolate-chip.webp", "Satay, Chicken, Meat", 10000, 20, 1.2, 4.7, 200, false, true));
        items.add(new FoodItem("Snack Cakes","https://www.lacademie.com/wp-content/uploads/2022/05/snack-cakes.webp", "Fried Rice, Chicken", 12000, 15, 0.8, 4.8, 350, true, false));
        items.add(new FoodItem("Confectionery","https://www.lacademie.com/wp-content/uploads/2022/05/candies-jars.webp", "Meatball, Noodles", 15000, 10, 1.5, 4.6, 500, false, true));
        items.add(new FoodItem("Pastries","https://www.lacademie.com/wp-content/uploads/2022/05/choco-pies.webp", "Spicy Chicken, Rice", 11000, 18, 0.9, 4.9, 250, true, true));
        items.add(new FoodItem("Chips","https://www.lacademie.com/wp-content/uploads/2022/05/potato-chips.webp", "Soup, Rice, Beef", 14000, 25, 1.4, 4.5, 300, false, false));
        return items;
    }
    private List<FoodItem> fetchsauceItems() {
        // Dummy data for illustration
        List<FoodItem> items = new ArrayList<>();
        items.add(new FoodItem("Bordelaise Sauce","https://i0.wp.com/homequirer.com/wp-content/uploads/2022/10/Bordelaise-Sauce.jpg?resize=1024%2C768&ssl=1", "Porridge, Rice, Chicken", 10000, 12, 1.0, 4.9, 400, true, false));
        items.add(new FoodItem("Steak Sauce","https://i0.wp.com/homequirer.com/wp-content/uploads/2022/10/Steak-Sauce.jpg?resize=1024%2C682&ssl=1", "Satay, Chicken, Meat", 10000, 20, 1.2, 4.7, 200, false, true));
        items.add(new FoodItem("Demi-Glace","https://i0.wp.com/homequirer.com/wp-content/uploads/2022/10/Demi-Glace.jpg?resize=1024%2C682&ssl=1", "Fried Rice, Chicken", 12000, 15, 0.8, 4.8, 350, true, false));
        items.add(new FoodItem("Chocolate Sauce","https://i0.wp.com/homequirer.com/wp-content/uploads/2022/10/Chocolate-Sauce.webp?resize=1024%2C577&ssl=1", "Meatball, Noodles", 15000, 10, 1.5, 4.6, 500, false, true));
        items.add(new FoodItem("Caramel Sauce","https://i0.wp.com/homequirer.com/wp-content/uploads/2022/10/Caramel-Sauce.jpg?resize=1024%2C678&ssl=1", "Spicy Chicken, Rice", 11000, 18, 0.9, 4.9, 250, true, true));
        items.add(new FoodItem("Cranberry Sauce","https://i0.wp.com/homequirer.com/wp-content/uploads/2022/10/Cranberry-Sauce.jpg?resize=1024%2C682&ssl=1", "Soup, Rice, Beef", 14000, 25, 1.4, 4.5, 300, false, false));
        return items;
    }
}
