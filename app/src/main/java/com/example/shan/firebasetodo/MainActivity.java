package com.example.shan.firebasetodo;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private ValueEventListener mPostListener, mEventListenerOnce;
    private TextView mTextView;

    private int lastId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseReference =
                FirebaseDatabase.getInstance().getReference();

        mTextView = (TextView)findViewById(R.id.textViewTitle);

        setEventListner();
        iterateTasks();
        //iterateUSers();


    }

    public void setEventListner(){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //if (dataSnapshot.child("lastId").exists()) {
                //    lastId = Integer.valueOf(
                //            dataSnapshot.child("lastId").getValue().toString());
                if (dataSnapshot.child("tasks").exists() == false){
                    mDatabaseReference.setValue("tasks");
                    //mDatabaseReference.setValue("users");
                    //mDatabaseReference.child("tasks").setValue()

                }
                //lastId++;

                //}else{
                //mDatabaseReference.child("lastId").setValue(1);
                //lastId = 1;
                //}


                //Task task = dataSnapshot.child("tasks").getValue(Task.class);

                /*for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Task user = snapshot.getValue(Task.class);

                }*/

                    /*if (task !=null)
                   mTextView.setText(task.getTask());*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Toast.makeText(MainActivity.this, "Failed to load post.",
                //        Toast.LENGTH_SHORT).show();

            }
        };
        mDatabaseReference.addValueEventListener(postListener);

        // Keep copy of post listener so we can remove it when app stops
        mPostListener = postListener;

    }



    public void addTask(View view) throws ParseException {

        String task1 = ((EditText)findViewById(R.id.editTextTask))
                .getText().toString();

        String dueDate = ((EditText)findViewById(R.id.editTextDate))
                        .getText().toString();

        //Task task = new Task(task1, new SimpleDateFormat("dd/MM/yyyy").parse (dueDate));
        Task task = new Task(task1, dueDate);

        String key = mDatabaseReference.push().getKey();
        //mDatabaseReference.push().setValue(task);
        //lastId++;
        //mDatabaseReference.child("tasks").child( String.valueOf(lastId) ).setValue(task);
        mDatabaseReference.child("tasks").child( key ).setValue(task);


    }



    public void iterateTasks(){
        mDatabaseReference.child("tasks")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Task task = snapshot.getValue(Task.class);

                            Log.d("snapShot", snapshot.getValue().toString());

                            /*if (task !=null)
                                mTextView.setText(task.getTask());*/

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    public void iterateUSers(){
        mDatabaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    User user = childSnapshot.getValue(User.class);
                    Log.d("snapShot", user.toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void addUser(View view) {
        User user = new User("abc", 1244);

        String key = mDatabaseReference.push().getKey();
        mDatabaseReference.child("users").child( key ).setValue(user);
    }
}
