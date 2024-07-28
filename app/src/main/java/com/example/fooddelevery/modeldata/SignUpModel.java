package com.example.fooddelevery.modeldata;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SignUpModel extends ViewModel {
    private MutableLiveData<Boolean> _navigateToNextActivity = new MutableLiveData<>();
    public LiveData<Boolean> navigateToNextActivity = _navigateToNextActivity;
    private final MutableLiveData<Boolean> issignupIn = new MutableLiveData<>();

    private final MutableLiveData<String> signupStatus = new MutableLiveData<>();

    public LiveData<String> getsignupStatus() {
        return signupStatus;
    }
    public LiveData<Boolean> getIssignupIn() {
        return issignupIn;
    }
    public void signup(Context context,String username, String contact, String email, String password) {
        SharedPreferences   sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(contact) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username);
            editor.putString("contact", contact);
            editor.putString("email", email);
            editor.putString("password", password);
            editor.apply(); // or editor.commit() if you need synchronous saving
        }
    }
    public void onSignupClicked() {
            signupStatus.setValue("signup successful");
            issignupIn.setValue(true);
    }

}