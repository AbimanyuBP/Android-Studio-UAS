package com.devting.wombat.aplikasi_todo_list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


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

