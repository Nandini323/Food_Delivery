package com.example.fooddelevery.AllMainFragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fooddelevery.CardsActivity;
import com.example.fooddelevery.FoodItem;
import com.example.fooddelevery.R;
import com.example.fooddelevery.modeldata.DelightmainModel;
import com.example.fooddelevery.viewAdapter.FoodAdapter;

import java.util.List;

public class HomeFragment extends Fragment {
    private DelightmainModel mainViewModel;
    private RecyclerView foodRecyclerView;
    private FoodAdapter foodAdapter;
    ImageView cardviewdata;

    TextView food,drink,snacks,sauce;
    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public static Fragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = new ViewModelProvider(this).get(DelightmainModel.class);
        foodRecyclerView = view.findViewById(R.id.foodRecyclerView);
        food=view.findViewById(R.id.foodview);
        drink=view.findViewById(R.id.drinkview);
        snacks=view.findViewById(R.id.snackview);
        sauce=view.findViewById(R.id.sauceview);
        cardviewdata=view.findViewById(R.id.cartIcon);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        foodAdapter = new FoodAdapter();
        foodRecyclerView.setAdapter(foodAdapter);
        setupObservers();
        setupClickListeners();
    }
    private void setupObservers() {
        mainViewModel.getFoodItems().observe(getViewLifecycleOwner(), new Observer<List<FoodItem>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(List<FoodItem> foodItems) {
                foodAdapter.setFoodItems(foodItems);
                resetTextViewColors();
                food.setTextColor(getResources().getColor(R.color.orange));

            }
        });
    }

    private void setupClickListeners() {
        food.setOnClickListener(v -> {
            mainViewModel.getFoodItems().observe(getViewLifecycleOwner(), new Observer<List<FoodItem>>() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onChanged(List<FoodItem> foodItems) {
                    foodAdapter.setFoodItems(foodItems);
                    resetTextViewColors();
                    food.setTextColor(getResources().getColor(R.color.orange));
                }
            });
        });

        drink.setOnClickListener(v -> {
            mainViewModel.getdrinkItems().observe(getViewLifecycleOwner(), new Observer<List<FoodItem>>() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onChanged(List<FoodItem> foodItems) {
                    foodAdapter.setFoodItems(foodItems);
                    resetTextViewColors();
                    drink.setTextColor(getResources().getColor(R.color.orange));
                }
            });
        });

        snacks.setOnClickListener(v -> {
            mainViewModel.getsnacksItems().observe(getViewLifecycleOwner(), new Observer<List<FoodItem>>() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onChanged(List<FoodItem> foodItems) {
                    foodAdapter.setFoodItems(foodItems);
                    resetTextViewColors();
                    snacks.setTextColor(getResources().getColor(R.color.orange));
                }
            });
        });

        sauce.setOnClickListener(v -> {
            mainViewModel.getsauceItems().observe(getViewLifecycleOwner(), new Observer<List<FoodItem>>() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onChanged(List<FoodItem> foodItems) {
                    foodAdapter.setFoodItems(foodItems);
                    resetTextViewColors();
                    sauce.setTextColor(getResources().getColor(R.color.orange));
                }
            });
        });

        cardviewdata.setOnClickListener(v->{
            openDetailActivity();
        });
    }

    private void resetTextViewColors() {
        food.setTextColor(getResources().getColor(R.color.black));
        drink.setTextColor(getResources().getColor(R.color.black));
        snacks.setTextColor(getResources().getColor(R.color.black));
        sauce.setTextColor(getResources().getColor(R.color.black));
    }

    private void openDetailActivity() {
        Intent intent = new Intent(getActivity(), CardsActivity.class);
        startActivity(intent);
    }
}