package com.example.shan.firebasetodo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TaskActivity extends AppCompatActivity {

    DatabaseReference mDatabaseReference;
    EditText mEditTextTask, mEditTextDueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        mEditTextTask = (EditText) findViewById(R.id.editTextTask);
        mEditTextDueDate = (EditText) findViewById(R.id.editTextDate);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        //initializeDateBase();
        //initializeDataChangeListner();

        final TextView textView = (TextView) findViewById(R.id.textViewTasksList);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {

            String searchTask = "04/04/2020";
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String tasksList="";
                for (DataSnapshot dataSnapshot1: dataSnapshot.child("tasks").getChildren()){
                    Task task = dataSnapshot1.getValue(Task.class);
                    tasksList += task.getTask() + ", ";
                }
                Toast.makeText(TaskActivity.this, tasksList, Toast.LENGTH_LONG).show();
                textView.setText(tasksList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








    }

    public void initializeDataChangeListner(){
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int taskCount = 0;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.child("tasks").getChildren()){

                    Task task = dataSnapshot1.getValue(Task.class);
                    //task.getTask()
                    //Log.d("tasksEvent", dataSnapshot1.getKey() + "/" +task.getTask() + ", "+ task.getDate());

                    taskCount++;
/*
                    if (taskCount > 3) {
                        Log.d("tasksEvent", "removing"+dataSnapshot1.getRef() + "/" +task.getTask());
                        dataSnapshot1.getRef().removeValue();
                        continue;
                    }*/

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void initializeDateBase(){
        mDatabaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child("taskList").exists() == false){
                            //mDatabaseReference.setValue("taskList");
                            //mDatabaseReference.child("tasks").setValue("");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }
        );
    }


    public void addTask(View view) {
        Task task = new Task(mEditTextTask.getText().toString()
                , mEditTextDueDate.getText().toString());
        mDatabaseReference.child("tasks").push().setValue(task);
    }

    public void addUSer(View view) {
        /*User user = new User(mEditTextTask.getText().toString(),
                Long.getLong(mEditTextDueDate.getText().toString()) );*/
        User user = new User("user1", 12345);
        mDatabaseReference.child("users").push().setValue(user);
    }
}
