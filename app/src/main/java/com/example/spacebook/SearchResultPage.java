package com.example.spacebook;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    private Button reserve;
    private Button cancel;

    String room, email, date, start, end;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        // setup for buttons
        reserve = findViewById(R.id.reserve);
        cancel = findViewById(R.id.cancel);

        // setup for listview
        list = findViewById(R.id.listView1);
        list.setOnItemClickListener(this);
        adapter = new ArrayAdapter<String>(this, R.layout.item, roomList);
        list.setAdapter(adapter);

        // retrieving extras from tab activity
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        reservations = (ArrayList<Reservation>) args.getSerializable("res");
        // used to create new reservation
        email = intent.getStringExtra("email");
        date = intent.getStringExtra("date");
        start = intent.getStringExtra("start");
        end = intent.getStringExtra("end");

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
        for (Reservation r : reservations) {
            // removes the room associated with the reservation, leaving only available rooms
            if (roomList.contains(r.getRoomNo())){
                roomList.remove(r.getRoomNo());
                adapter.notifyDataSetChanged();
            }
        }

        //prints available rooms for debugging purposes
        for (String s : roomList){
            System.out.println(s);
        }

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create dialog
                AlertDialog dialog = new AlertDialog.Builder(SearchResultPage.this).create();

                //set message, title, and icon
                dialog.setTitle("Room Reservation");
                dialog.setMessage("Confirm Room Selection");

                //set three option buttons
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        helper.addRes(new Reservation(room, email, date, start, end));
                        //go to email page
                        Intent goToEmails = new Intent(SearchResultPage.this, EmailConfirmation.class);
                        startActivity(goToEmails);
                        finish();
                    }
                });

                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //does not do anything
                    }
                });


                dialog.show();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().removeExtra("BUNDLE");
                reservations.clear();
                finish();
            }
        });

    } // closes OnCreate

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        v.setSelected(true);
        room = parent.getItemAtPosition(position).toString();

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
