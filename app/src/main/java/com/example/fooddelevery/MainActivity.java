package com.example.fooddelevery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.fooddelevery.databinding.ActivityMainBinding;
import com.example.fooddelevery.modeldata.MainViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.setContext(this);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.navigateToNextActivity.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldNavigate) {
                if (shouldNavigate != null) {
                    Intent intent;
                    if (shouldNavigate) {
                        intent = new Intent(MainActivity.this, DelightFoodActivity.class);
                    } else {
                        intent = new Intent(MainActivity.this, UserLoginActivity.class);
                    }
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
