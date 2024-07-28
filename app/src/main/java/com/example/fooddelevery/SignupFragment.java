package com.example.fooddelevery;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddelevery.modeldata.MainViewModel;
import com.example.fooddelevery.modeldata.SignUpModel;
import com.google.android.material.textfield.TextInputEditText;

public class SignupFragment extends Fragment {

    private SignUpModel viewModel;
    public SignupFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SignUpModel.class);
        TextInputEditText usernameEditText = view.findViewById(R.id.username);
        TextInputEditText contactEditText = view.findViewById(R.id.contact);
        TextInputEditText emailEditText = view.findViewById(R.id.et_email);
        TextInputEditText passwordEditText = view.findViewById(R.id.et_password);

        view.findViewById(R.id.btn_Signup).setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String contact = contactEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            viewModel.signup(getActivity(),username, contact, email, password);
            viewModel.onSignupClicked();
        });

        viewModel.getsignupStatus().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("signup successful")) {
                    // Start the new activity
                    Intent intent = new Intent(getActivity(), UserLoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        viewModel.getIssignupIn().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Intent intent=new Intent(getActivity(),UserLoginActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
}