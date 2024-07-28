package com.example.fooddelevery.modeldata;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private MutableLiveData<Boolean> _navigateToNextActivity = new MutableLiveData<>();
    public LiveData<Boolean> navigateToNextActivity = _navigateToNextActivity;
    private final MutableLiveData<Boolean> isLoggedIn = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    private final MutableLiveData<String> loginStatus = new MutableLiveData<>();
    private Context context;
    private final MutableLiveData<Integer> selectedTab = new MutableLiveData<>();


    public void setContext(Context context) {
        this.context = context;
        checkLoginStatus();
    }

    private void checkLoginStatus() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean loggedIn = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
        isLoggedIn.setValue(loggedIn);
    }

    public void onGetStartedClicked() {
        checkLoginStatus();
        if (isLoggedIn.getValue() != null && isLoggedIn.getValue()) {
            _navigateToNextActivity.setValue(true); // Navigate to main content screen
        } else {
            _navigateToNextActivity.setValue(false); // Navigate to login screen
        }
    }

    public void doneNavigating() {
        _navigateToNextActivity.setValue(null);
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public LiveData<String> getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password.setValue(password);
    }

    public LiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }

    public LiveData<String> getLoginStatus() {
        return loginStatus;
    }

    public void onLoginClicked(Context context,String mail, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        String savedEmail = sharedPreferences.getString("email", "Default Email");
        String savedPassword = sharedPreferences.getString("password", "Default Password");

        if (mail.equals(savedEmail) && password.equals(savedPassword)) {
            sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, true).apply();
            loginStatus.setValue("Login successful");
            isLoggedIn.setValue(true);
        } else {
            loginStatus.setValue("Login failed");
            isLoggedIn.setValue(false);
        }
    }

    public void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, false).apply();
        isLoggedIn.setValue(false);
    }

    public LiveData<Integer> getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(int tabId) {
        selectedTab.setValue(tabId);
    }
}
