package com.e.retrofitone.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PostViewModelFactory implements ViewModelProvider.Factory {

    private Application application;
    private String parameters;

    public PostViewModelFactory(Application application, String parameters) {
        this.application = application;
        this.parameters = parameters;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PostViewModel(application);
    }
}
