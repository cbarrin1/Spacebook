package com.example.spacebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SearchResultPage extends FragmentActivity implements AdapterView.OnItemClickListener {

    //DB objects
    private SQLiteDatabase db;
    private SQLHelper helper;
    private Cursor cursor;

    //list view setup
    private ListView list;
    private ArrayAdapter<String> adapter = null;

    private ArrayList<Reservation> reservations = new ArrayList<>();
    private ArrayList<String> roomList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        list = findViewById(R.id.listView1);
        list.setOnItemClickListener(this);

        adapter = new ArrayAdapter<String>(this, R.layout.item, roomList);
        list.setAdapter(adapter);

        // retrieving reservation list from tab activity
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        reservations = (ArrayList<Reservation>) args.getSerializable("res");

        // setting up DB connection
        helper = new SQLHelper(this);
        db = helper.getWritableDatabase();

        // retrieves list of all rooms
        try {
            cursor = db.rawQuery("SELECT * FROM ROOMS", null);
            while (cursor.moveToNext()) {
                String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
                roomList.add(room);
            }
        } catch(Exception e) {e.printStackTrace();}

        // loops through all reservations passed from previous activity
        // removes the room associated with the reservation, leaving only available rooms
        for (Reservation r : reservations) {
            if (roomList.contains(r.getRoomNo())){
                roomList.remove(r.getRoomNo());
                adapter.notifyDataSetChanged();
            }
        }

        //prints available rooms for debugging purposes
        for (String s : roomList){
            System.out.println(s);
        }

    }

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            getIntent().removeExtra("BUNDLE");
            reservations.clear();
            finish();
            return true;
        }
        return (super.onKeyDown(keyCode, event));
    }
}
