package com.example.fooddelevery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.fooddelevery.AllMainFragments.FavouriteFragment;
import com.example.fooddelevery.AllMainFragments.HomeFragment;
import com.example.fooddelevery.AllMainFragments.accountFragment;
import com.example.fooddelevery.AllMainFragments.historyFragment;
import com.example.fooddelevery.modeldata.MainViewModel;

public class DelightFoodActivity extends AppCompatActivity {
    private ImageView home, favourite, account, history;
    private MainViewModel mainViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delight_food);

        home = findViewById(R.id.homeIcon);
        favourite = findViewById(R.id.favoriteIcon);
        account = findViewById(R.id.accountIcon);
        history = findViewById(R.id.historyIcon);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        if (savedInstanceState == null) {
            showFragment(new HomeFragment());
            mainViewModel.setSelectedTab(R.id.homeIcon);
        }

        mainViewModel.getSelectedTab().observe(this, this::setSelectedIcon);

        home.setOnClickListener(v -> {
            showFragment(new HomeFragment());
            mainViewModel.setSelectedTab(R.id.homeIcon);
        });

        favourite.setOnClickListener(v -> {
            showFragment(new FavouriteFragment());
            mainViewModel.setSelectedTab(R.id.favoriteIcon);
        });

        account.setOnClickListener(v -> {
            showFragment(new accountFragment());
            mainViewModel.setSelectedTab(R.id.accountIcon);
        });

        history.setOnClickListener(v -> {
            showFragment(new historyFragment());
            mainViewModel.setSelectedTab(R.id.historyIcon);
        });
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.inside_fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void setSelectedIcon(int selectedIconId) {

        home.setColorFilter(getResources().getColor(R.color.default_icon_color));
        favourite.setColorFilter(getResources().getColor(R.color.default_icon_color));
        account.setColorFilter(getResources().getColor(R.color.default_icon_color));
        history.setColorFilter(getResources().getColor(R.color.default_icon_color));


        if (selectedIconId == R.id.homeIcon) {
            home.setColorFilter(getResources().getColor(R.color.selected_icon_color));
        } else if (selectedIconId == R.id.favoriteIcon) {
            favourite.setColorFilter(getResources().getColor(R.color.selected_icon_color));
        } else if (selectedIconId == R.id.accountIcon) {
            account.setColorFilter(getResources().getColor(R.color.selected_icon_color));
        } else if (selectedIconId == R.id.historyIcon) {
            history.setColorFilter(getResources().getColor(R.color.selected_icon_color));
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}
