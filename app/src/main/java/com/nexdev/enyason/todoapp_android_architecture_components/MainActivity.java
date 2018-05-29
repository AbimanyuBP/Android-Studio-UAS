package com.nexdev.enyason.todoapp_android_architecture_components;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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


                Intent intent = new Intent(MainActivity.this,EditorActivity.class);
                startActivity(intent);


            }
        });

        toDoListAdapter = new ToDoListAdapter(this);
        appDataBase = AppDataBase.getsInstance(getApplicationContext());


        recyclerView.setAdapter(toDoListAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        toDoListAdapter.setTasks(appDataBase.taskDao().loadAllTask());

    }
}
