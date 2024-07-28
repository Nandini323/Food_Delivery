package com.example.fooddelevery.AllMainFragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.fooddelevery.R;
import com.example.fooddelevery.modeldata.CardModel;
import com.example.fooddelevery.viewAdapter.FavoritesAdapter;

public class FavouriteFragment extends Fragment {
    private CardModel favoritesViewModel;
    public FavouriteFragment() {
        // Required empty public constructor
    }

    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favoritesViewModel = new ViewModelProvider(this).get(CardModel.class);

        RecyclerView rvFavoriteItems = view.findViewById(R.id.rv_favorite_items);
        ImageButton btnBack = view.findViewById(R.id.btn_back);

        FavoritesAdapter adapter = new FavoritesAdapter();
        rvFavoriteItems.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavoriteItems.setAdapter(adapter);

        favoritesViewModel.getAllFavoriteItems().observe(getViewLifecycleOwner(), items -> {
            adapter.setFavoriteItems(items);
        });
        adapter.setOnItemDeleteListener(item -> favoritesViewModel.delete(item));

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
        itemTouchHelper.attachToRecyclerView(rvFavoriteItems);


        btnBack.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.inside_fragment_container, HomeFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        });
    }

}