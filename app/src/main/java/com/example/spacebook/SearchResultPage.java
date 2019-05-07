package com.example.spacebook;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import android.graphics.Color;

public class SearchResultPage extends FragmentActivity implements AdapterView.OnItemClickListener {

    //DB objects
    private SQLiteDatabase db;
    private SQLHelper helper;
    private Cursor cursor;

    //for notifications
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder = null;
    private String textTitle = "Room Successfully Booked";
    private String textContent = "Click here to see all your reservations";
    private int SIMPLE_NOTFICATION_ID = 25;

    //list view setup
    ListView list;
    ArrayAdapter<String> adapter = null;

    private ArrayList<Reservation> reservations = new ArrayList<>();
    private ArrayList<String> roomList = new ArrayList<>();



    Button reserve;
    Button cancel;

    String room, email, date, start, end;
    int roomID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        // setup for buttons
        reserve = findViewById(R.id.reserve);
        cancel = findViewById(R.id.cancel);

        // setup for listview
        list = findViewById(R.id.listView1);
        list.setOnItemClickListener(this);

        // retrieving extras from tab activity
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        reservations = (ArrayList<Reservation>) args.getSerializable("res");
        Bundle args2 = intent.getBundleExtra("rooms");
        roomList = (ArrayList<String>) args2.getSerializable("room");
        // set up array adapter
        if (roomList != null){
            adapter = new ArrayAdapter<>(this, R.layout.item, roomList);
        }
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // used to create new reservation
        email = intent.getStringExtra("email");
        date = intent.getStringExtra("date");
        start = intent.getStringExtra("start");
        end = intent.getStringExtra("end");

        // setting up DB connection
        helper = new SQLHelper(this);
        db = helper.getWritableDatabase();

        // loops through all reservations passed from previous activity
        for (Reservation r : reservations) {
            // removes the room associated with the reservation, leaving only available rooms
            if (roomList.contains(r.getRoomNo())){
                roomList.remove(r.getRoomNo());
                adapter.notifyDataSetChanged();
            }
        }

        // for notifications
        mNotificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        //As of API 26 Notification Channels must be assigned to a channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default",
                    "Channel foobar",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Channel description");
            channel.setLightColor(Color.GREEN);
            channel.enableVibration(true);
            mNotificationManager.createNotificationChannel(channel);
        }
        //create intent for action when notification selected
        //from expanded status bar
        Intent notifyIntent = new Intent(this, TabPage.class);

        //create pending intent to wrap intent so that it
        //will fire when notification selected.
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        //set parameter values for Notification
        mBuilder = new NotificationCompat.Builder(this, "default")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.droid)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setAutoCancel(true)     //cancel Notification after clicking on it
                //set Android to vibrate when notified
                .setVibrate(new long[] {1000, 1000, 2000, 2000})
                //allow heads up notification; otherwise use PRIORITY_DEFAULT
                .setPriority(NotificationCompat.PRIORITY_HIGH);


        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cursor = db.rawQuery("SELECT room_id FROM ROOMS WHERE roomNo =?", new String[]{room});
                    while (cursor.moveToNext()) {
                        roomID = cursor.getInt(cursor.getColumnIndex(SQLConstants.ROOM_ID));
                    }
                } catch (Exception e) {e.printStackTrace();}

                //create dialog
                AlertDialog dialog = new AlertDialog.Builder(SearchResultPage.this).create();
                //set message, title, and icon
                dialog.setTitle("Room Reservation");
                dialog.setMessage("Confirm Room Selection");

                //set three option buttons
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //for notifications
                        mNotificationManager.notify(SIMPLE_NOTFICATION_ID,
                                mBuilder.build());



                        Reservation r = new Reservation(roomID, room, email, date, start, end);
                        helper.addRes(r);
                        //go to email page
                        Intent goToEmails = new Intent(SearchResultPage.this, EmailConfirmation.class);

                        Bundle args = new Bundle();
                        args.putSerializable("res",r);
                        goToEmails.putExtra("reservation",args);
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
                getIntent().removeExtra("rooms");
                roomList.clear();
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
            getIntent().removeExtra("rooms");
            roomList.clear();
            reservations.clear();
            finish();
            return true;
        }
        return (super.onKeyDown(keyCode, event));
    }
}
