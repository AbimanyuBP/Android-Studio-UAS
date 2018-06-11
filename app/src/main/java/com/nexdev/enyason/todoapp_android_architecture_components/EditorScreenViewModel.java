package com.nexdev.enyason.todoapp_android_architecture_components;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

/**
 * Created by enyason on 6/7/18.
 */

public class EditorScreenViewModel extends ViewModel {


    private LiveData<Task> task;

    public EditorScreenViewModel(AppDataBase appDataBase, int id) {

        task = appDataBase.taskDao().getTaskById(id);
        Log.i(" Editor View Model "," Loading a task");

    }

    public LiveData<Task> getTask() {

        return task;
    }
}

