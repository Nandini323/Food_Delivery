package com.example.fooddelevery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddelevery.modeldata.MainViewModel;
import com.google.android.material.textfield.TextInputEditText;


public class UserLoginActivity extends AppCompatActivity {
    MainViewModel viewModel;
    Button login;
    ImageView loginhover,signuphover;
    TextView Login ,Signup;
    TextInputEditText usernameEditText ,passwordEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        login=findViewById(R.id.btn_login);
        usernameEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_password);
        Login=findViewById(R.id.tv_login);
        Signup=findViewById(R.id.tv_signup);
        loginhover=findViewById(R.id.under_login);
        signuphover=findViewById(R.id.under_signup);

        Login.setOnClickListener(v ->
                {  showFragment(new LoginFragment());
                    loginhover.setVisibility(View.VISIBLE);
                    signuphover.setVisibility(View.INVISIBLE);
                }
        );
        Signup.setOnClickListener(v ->
        {
            showFragment(new SignupFragment());
            loginhover.setVisibility(View.INVISIBLE);
            signuphover.setVisibility(View.VISIBLE);
        }
        );
        if (savedInstanceState == null) {
            showFragment(new LoginFragment());
            loginhover.setVisibility(View.VISIBLE);
        }
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }
    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}