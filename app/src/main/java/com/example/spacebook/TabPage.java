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
import android.widget.TextView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class TabPage extends FragmentActivity implements AdapterView.OnItemSelectedListener{

    //DB objects
    private SQLiteDatabase db;
    private SQLHelper helper;
    private Cursor cursor;

    //User Reservation List
    private TextView resList;

    private CalendarView calendar;

    TabHost tabs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);

        //tab setup
        tabs = findViewById(R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec;

        //list of user reservations
        resList = findViewById(R.id.textView);
        //calendar
        calendar = (CalendarView)findViewById(R.id.calendarView);

        //getting the username from previous activity and saving for future use
        String user = getIntent().getStringExtra("user");
        System.out.println(user);

        //query DB for all reservations under the current username
        try {
            helper = new SQLHelper(this);
            db = helper.getWritableDatabase();
            cursor = db.rawQuery("SELECT * FROM RESERVATIONS WHERE email = ?", new String[]{user});
            while (cursor.moveToNext()) {
                String usern = cursor.getString(cursor.getColumnIndex(SQLConstants.USER_EMAIL));
                String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
                String date = cursor.getString(cursor.getColumnIndex(SQLConstants.DATE));
                String start = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_START));
                String end = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_END));

                resList.append("Room: " + room + "   Date: " + date + "   Start Time: " + start + "   End Time: " + end + "\n");

            }
        } catch(Exception e) { e.printStackTrace();}


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

        //listener for changing date on calendar
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //creating new strings for formatting purposes
                String newDay, newMonth;
                //setting up calendar
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, dayOfMonth);
                //saving date attributes
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int yr = cal.get(Calendar.YEAR);
                int mth = cal.get(Calendar.MONTH);

                //cleaning up data to match database format
                mth++;
                newDay = String.format("%02d", day);
                newMonth = String.format("%02d", mth);

                //creating date string for DB query
                String selectedDay = yr + "-" + newMonth + "-" + newDay;

                //running query to look for all records that match date
                //any records returned mean there is at least one reservation on that date
                try {
                    db = helper.getWritableDatabase();
                    cursor = db.rawQuery("SELECT * FROM RESERVATIONS WHERE date = ?", new String[]{selectedDay});
                    while (cursor.moveToNext()) {
                        String usern = cursor.getString(cursor.getColumnIndex(SQLConstants.USER_EMAIL));
                        String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
                        String date = cursor.getString(cursor.getColumnIndex(SQLConstants.DATE));
                        String start = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_START));
                        String end = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_END));

                        //println for debugging purposes
                        System.out.println("Room: " + room + "   Date: " + date + "   Start Time: " + start + "   End Time: " + end + "\n");

                    }
                } catch(Exception e) { e.printStackTrace();}

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
