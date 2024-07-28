package com.example.fooddelevery.modeldata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

public class ImageSliderViewModel extends ViewModel {
    private MutableLiveData<List<String>> images;

    public LiveData<List<String>> getImages() {
        if (images == null) {
            images = new MutableLiveData<>();
            loadImages();
        }
        return images;
    }

    private void loadImages() {
        // Replace with your image URLs
        images.setValue(Arrays.asList(
               "https://www.tasteatlas.com/Images/Dishes/181106e0dc5249ac8f8089098048cc02.jpg?mw=1300",
               "https://www.tasteatlas.com/Images/Dishes/181106e0dc5249ac8f8089098048cc02.jpg?mw=1300",
                "https://www.tasteatlas.com/Images/Dishes/181106e0dc5249ac8f8089098048cc02.jpg?mw=1300"
        ));
    }
}