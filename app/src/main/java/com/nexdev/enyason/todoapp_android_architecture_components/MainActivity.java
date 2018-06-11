package com.nexdev.enyason.todoapp_android_architecture_components;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    AppDataBase appDataBase;

    ToDoListAdapter toDoListAdapter;


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.fab);

        recyclerView = findViewById(R.id.recycler_view_main);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);


            }
        });

        toDoListAdapter = new ToDoListAdapter(this);
        appDataBase = AppDataBase.getsInstance(getApplicationContext());


        recyclerView.setAdapter(toDoListAdapter);


        //make recyclerView swipe to the left and right

        //implement delete upon swipe


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //  implement swipe to delete


                //get item position
                final int position = viewHolder.getAdapterPosition();
                //
                final List<Task> tasks = toDoListAdapter.getTasks();


                AppExecutor.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {

                        appDataBase.taskDao().deleteTask(tasks.get(position));

                    }
                });




            }
        }).attachToRecyclerView(recyclerView);


        getTasks("onCreate");


    }

    private void getTasks(final String s) {

//        AppExecutor.getInstance().diskIO().execute(new Runnable() {
//            @Override
//            public void run() {


//        final LiveData<List<Task>> tasks = appDataBase.taskDao().loadAllTask();


        MainScreenViewModel viewModel = ViewModelProviders.of(this).get(MainScreenViewModel.class);

        viewModel.getTaskList().observe(MainActivity.this, new Observer<List<Task>>() {

            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                toDoListAdapter.setTasks(tasks);

            }
        });

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        toDoListAdapter.setTasks(tasks);
//
//                    }
//                });
//
//
//            }
//        });


    }

    @Override
    protected void onResume() {
        super.onResume();


//        final List<Task> tasks = appDataBase.taskDao().loadAllTask();
//        toDoListAdapter.setTasks(tasks);


    }

}
