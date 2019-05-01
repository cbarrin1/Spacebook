package com.example.spacebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SearchResultPage extends FragmentActivity {

    //DB objects
    private SQLiteDatabase db;
    private SQLHelper helper;
    private Cursor cursor;

    private ArrayList<Reservation> reservations;
    private ArrayList<String> roomList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        // retrieving reservation list from tab activity
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        String startTime = intent.getStringExtra("start");
        String endTime = intent.getStringExtra("end");


        try {
            cursor = db.rawQuery("SELECT roomNo FROM ROOMS", null);
            while (cursor.moveToNext()) {
                String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
                roomList.add(room);
            }
        } catch(Exception e) {e.printStackTrace();}

        reservations = (ArrayList<Reservation>) args.getSerializable("ARRAYLIST");


        for (Reservation r : reservations) {
            if (roomList.contains(r.getRoomNo())){
                roomList.remove(r.getRoomNo());
            }
        }

        for (String s : roomList){
            System.out.println(s);
        }

    }
}
