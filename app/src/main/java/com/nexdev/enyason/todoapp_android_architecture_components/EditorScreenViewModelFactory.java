package com.nexdev.enyason.todoapp_android_architecture_components;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by enyason on 6/7/18.
 */

public class EditorScreenViewModelFactory extends ViewModelProvider.NewInstanceFactory {


    private final AppDataBase appDataBase;
    private final int id;

    public EditorScreenViewModelFactory(AppDataBase db, int taskId) {

        appDataBase = db;
        id = taskId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EditorScreenViewModel(appDataBase,id);
    }
}

