package com.nexdev.enyason.todoapp_android_architecture_components;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Date;

public class EditorActivity extends AppCompatActivity {


    // Constants for priority
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 3;


    Button buttonAdd;
    EditText etTask;

    AppDataBase mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        buttonAdd = findViewById(R.id.saveButton);

        etTask = findViewById(R.id.editTextTaskDescription);

        mdb = AppDataBase.getsInstance(getApplicationContext());


        buttonAdd.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

               String text =  etTask.getText().toString().trim();
               int priority = getPriorityFromViews();
               Date date = new Date();

               Task task  = new Task(text,priority,date);


               mdb.taskDao().insertTask(task);

               finish();


            }
        });



    }





    /**
     * getPriority is called whenever the selected priority needs to be retrieved
     */
    public int getPriorityFromViews() {
        int priority = 1;
        int checkedId = ((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.radButton1:
                priority = PRIORITY_HIGH;
                break;
            case R.id.radButton2:
                priority = PRIORITY_MEDIUM;
                break;
            case R.id.radButton3:
                priority = PRIORITY_LOW;
        }
        return priority;
    }
}
