package com.example.spacebook;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private SQLHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new SQLHelper(this);

        //create database
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {
            Log.d("Spacebook", "Create database failed");
        }

        //query database
        ArrayList<User> userList = helper.getUserList();

        //write contents of list to screen
        for (User item : userList) {
            System.out.println(item);

        }

        //helper.addUser(new User("barring_char@bentley.edu", "password123"));


        //query database
        ArrayList<User> userList1 = helper.getUserList();

        //write contents of list to screen
        for (User item : userList1) {
            System.out.println(item);

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(db != null)
            db.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(db != null) {
            db.close();
            Log.d("Spacebook", "DB CLOSED");
        }

    }
}
