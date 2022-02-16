package com.tasktodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Variable initialization
    EditText editText;
    Button btnadd, btnreset;
    RecyclerView recyclerView;

    List<MainTask> taskList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    ThetasksDB database;
    MainAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign variable
        editText = findViewById(R.id.edit_text);
        btnadd = findViewById(R.id.btn_add);
        btnreset = findViewById(R.id.btn_reset);
        recyclerView = findViewById(R.id.recycler_view);

        //Initialize database

        database = ThetasksDB.getInstance(this);
        //Store database value in task list
        taskList = database.mainDao().getAll();

        //Initialize linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        //Set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);
        // Initialize adapter
        adapter = new MainAdapter(MainActivity.this,taskList);
        //Set adapter
        recyclerView.setAdapter(adapter);


        btnadd.setOnClickListener(view -> {
            // Get string from edit text
            String sText  = editText.getText().toString().trim();
            //check condition
            if (!sText.equals("")){
                //When text is not empty
                //Initialize main task
                MainTask task = new MainTask();
                //Set text on main task
                task.setText(sText);
                //Insert text in database
                database.mainDao().insert(task);
                //Clear edit text
                editText.setText("");
                //Notify when task is inserted
                taskList.clear();
                taskList.addAll(database.mainDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });


        btnreset.setOnClickListener(view -> {
            //Delete all data from database
            database.mainDao().reset(taskList);
            //Notify when all data deleted
            taskList.clear();
            taskList.addAll(database.mainDao().getAll());
            adapter.notifyDataSetChanged();
        });



    }
}