package com.example.fooddelevery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.fooddelevery.modeldata.CardModel;
import com.example.fooddelevery.modeldata.DelightmainModel;
import com.example.fooddelevery.viewAdapter.CartAdapter;

public class CardsActivity extends AppCompatActivity {
    private CardModel cartViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adtocard);

        cartViewModel = new ViewModelProvider(this).get(CardModel.class);

        RecyclerView rvCartItems = findViewById(R.id.rv_cart_items);
        ImageButton btnBack = findViewById(R.id.btn_back);
        Button btnCompleteOrder = findViewById(R.id.btn_complete_order);

        CartAdapter adapter = new CartAdapter();
        rvCartItems.setLayoutManager(new LinearLayoutManager(this));
        rvCartItems.setAdapter(adapter);

        cartViewModel.getAllCartItems().observe(this, items -> {
            adapter.setCartItems(items);
        });

        adapter.setOnItemDeleteListener(item -> cartViewModel.delete(item));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                adapter.removeItem(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(rvCartItems);

        btnBack.setOnClickListener(v -> onBackPressed());

        btnCompleteOrder.setOnClickListener(v -> {

        });
    }
}