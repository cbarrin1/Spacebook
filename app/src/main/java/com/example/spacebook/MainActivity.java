package com.example.spacebook;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private SQLHelper helper;

    private Cursor cursor;


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

        // TEST CODE FOR DB
        cursor = db.rawQuery("SELECT * FROM ROOMS WHERE roomNo ='001'",null);
        while (cursor.moveToNext()) {
            String roomno = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
            String loc = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_LOCATION));

            System.out.println(roomno + " " + loc);
        }
        helper.addRes(new Reservation("001", "test@bentley.edu", "2019-04-27", "08:00","09:00"));

        ArrayList<Reservation> resList = helper.getResList();
        for (Reservation item : resList) {
            System.out.println(item);
        }
        //END TEST CODE

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
