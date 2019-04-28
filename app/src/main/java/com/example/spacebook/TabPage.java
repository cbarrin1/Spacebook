package com.example.spacebook;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;


public class TabPage extends FragmentActivity implements AdapterView.OnItemSelectedListener  {

    private SQLiteDatabase db;
    private SQLHelper helper;
    private Cursor cursor;

    private ListView listview;
    private ArrayAdapter<String> adapt = null;

    private CalendarView calendarView;


    TabHost tabs;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);


        String user = getIntent().getStringExtra("user");
        System.out.println(user);

        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        try {
            helper = new SQLHelper(this);
            db = helper.getWritableDatabase();
            cursor = db.rawQuery("SELECT * FROM RESERVATIONS WHERE email = ?", new String[]{user});
            while (cursor.moveToNext()) {
                String usern = cursor.getString(cursor.getColumnIndex(SQLConstants.USER_EMAIL));
                String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
                String date = cursor.getString(cursor.getColumnIndex(SQLConstants.DATE));
                String st = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_START));
                String end = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_END));

                System.out.println(usern + " " + room + " " + date + " " + st + " " + end);
            }
        } catch(Exception e) { e.printStackTrace();}

        /*ArrayList<Reservation> resList = helper.getResList(user);
        for (Reservation item : resList) {
            System.out.println(item);
        }*/




        tabs = findViewById(R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec;


        // Initialize a TabSpec for tab1 and add it to the TabHost
        spec = tabs.newTabSpec("tag1");    //create new tab specification
        spec.setContent(R.id.My_Reservations);    //add tab view content
        spec.setIndicator("My Reservations");    //put text on tab
        tabs.addTab(spec);             //put tab in TabHost container


        // Initialize a TabSpec for tab2 and add it to the TabHost
        spec = tabs.newTabSpec("tag2");        //create new tab specification
        spec.setContent(R.id.Make_Reservations);            //add view tab content
        spec.setIndicator("Make Reservations");
        tabs.addTab(spec);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
