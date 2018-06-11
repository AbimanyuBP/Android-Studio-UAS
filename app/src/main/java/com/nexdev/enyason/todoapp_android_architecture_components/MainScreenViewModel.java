package com.nexdev.enyason.todoapp_android_architecture_components;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

/**
 * Created by enyason on 6/7/18.
 */

public class MainScreenViewModel extends AndroidViewModel {

    private LiveData<List<Task>> taskList;


    public MainScreenViewModel(@NonNull Application application) {
        super(application);

        AppDataBase appDataBase = AppDataBase.getsInstance(this.getApplication());
        Log.i("View Model"," Retrieving data from database");
        taskList =  appDataBase.taskDao().loadAllTask();

    }


    public LiveData<List<Task>> getTaskList() {
        return taskList;
    }
}
