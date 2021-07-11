package com.devting.wombat.aplikasi_todo_list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

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

