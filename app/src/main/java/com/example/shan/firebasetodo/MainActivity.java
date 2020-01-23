package com.example.shan.firebasetodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseReference =
                FirebaseDatabase.getInstance().getReference();

    }

    public void addTask(View view) {

        String task1 =
                ((EditText)findViewById(R.id.editTextTask))
                .getText().toString();
        mDatabaseReference.push().setValue(task1);

    }
}
