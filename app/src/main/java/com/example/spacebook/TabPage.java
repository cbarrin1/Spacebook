package com.example.spacebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TabHost;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import android.widget.Button;

public class TabPage extends FragmentActivity implements AdapterView.OnItemSelectedListener {

    //DB objects
    private SQLiteDatabase db;
    private SQLHelper helper;
    private Cursor cursor;

    //User Reservation List
    private TextView resList;
    private TextView dateChosen;

    private RadioButton library;
    private RadioButton stu;

    private CalendarView calendar;

    private String timeChosen;
    private String startTime;
    private String endTime;
    private Button seeAvailable;
    private Spinner spin;

    //date format for dateChosen
    SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy");

    private ArrayList<Reservation> list;

    String selectedDate;
    TabHost tabs;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //this gets the clicked item from the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                break;
            case R.id.menu_logout:
                SharedPreferences.Editor editor = MainActivity.sharedpreferences.edit();
                editor.remove("LOGIN");
                editor.apply();
                Intent intent = new Intent(TabPage.this, MainActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

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
        calendar = findViewById(R.id.calendarView);

        //displays date chosen
        dateChosen = findViewById(R.id.textView9);
        //dateChosen.setText("(choose a date on calendar)");

        //setting date display to current date
        String today = df.format(new Date(calendar.getDate()));
        dateChosen.setText(today);

        //radio buttons for location
        library = findViewById(R.id.radioButton);
        stu = findViewById(R.id.radioButton2);

        spin = findViewById(R.id.spinner1);
        spin.setOnItemSelectedListener(this);

        //button to see results
        seeAvailable = findViewById(R.id.button7);

        seeAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //query all reservations for selected day
                try {
                    list = new ArrayList<>();
                    cursor = db.rawQuery("SELECT * FROM RESERVATIONS WHERE date = ?", new String[]{selectedDate});
                    while (cursor.moveToNext()) {
                        String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
                        String date = cursor.getString(cursor.getColumnIndex(SQLConstants.DATE));
                        String start = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_START));
                        String end = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_END));

                        //set up time format
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                        //convert all time strings to date objects
                        Date dbStart = format.parse(start);
                        Date dbEnd = format.parse(end);
                        Date selectStart = format.parse(startTime);
                        Date selectEnd = format.parse(endTime);

                        //if there is a reservation between the selected range, it is added to list
                        if (dbStart.equals(selectStart) || dbStart.equals(selectEnd) || (dbStart.after(selectStart)) && dbStart.before(selectEnd)){
                            list.add(new Reservation(room, date, start, end));
                        }
                    }

                    // moves to search results page
                    Intent intent = new Intent(TabPage.this, SearchResultPage.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST",list);
                    intent.putExtra("BUNDLE",args);
                    startActivity(intent);


                } catch(Exception e) {e.printStackTrace();}
            }
        });

        // TAB 1 ----------------------------------------------------------------------------------------------
        // Initialize a TabSpec for tab1 and add it to the TabHost
        spec = tabs.newTabSpec("tag1");    //create new tab specification
        spec.setContent(R.id.My_Reservations);    //add tab view content
        spec.setIndicator("My Reservations");    //put text on tab
        tabs.addTab(spec);             //put tab in TabHost container

        //grabbing user email
        String user_login = MainActivity.sharedpreferences.getString("LOGIN", null);

        //query DB for all reservations under the current username
        try {
            helper = new SQLHelper(this);
            db = helper.getWritableDatabase();
            cursor = db.rawQuery("SELECT * FROM RESERVATIONS WHERE email = ?", new String[]{user_login});
            while (cursor.moveToNext()) {
                String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
                String date = cursor.getString(cursor.getColumnIndex(SQLConstants.DATE));
                String start = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_START));
                String end = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_END));

                resList.append("Room: " + room + "   Date: " + date + "   Start Time: " + start + "   End Time: " + end + "\n");

            }
        } catch (Exception e) {e.printStackTrace();}

        // TAB 2 ----------------------------------------------------------------------------------------------
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

                Toast.makeText(getApplicationContext(), String.valueOf(dayOfMonth), Toast.LENGTH_SHORT).show();

                dateChosen.setText((month+1) + "/" + day + "/" + year);

                //cleaning up data to match database format
                mth++;
                newDay = String.format("%02d", day);
                newMonth = String.format("%02d", mth);

                //saving date string for DB query
                selectedDate = yr + "-" + newMonth + "-" + newDay;

            }
        });

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        //spinner
        if (item.equals("8:00AM-10:00AM")) {
            timeChosen = "8:00AM - 10:00AM";
            startTime = "08:00";
            endTime = "10:00";
        } else if (item.equals("10:00AM-12:00PM")) {
            timeChosen = "10:00AM - 12:00PM";
            startTime = "10:00";
            endTime = "12:00";
        } else if (item.equals("12:00PM-2:00PM")) {
            timeChosen = "12:00PM - 2:00PM";
            startTime = "12:00";
            endTime = "14:00";
        } else if (item.equals("2:00PM-4:00PM")) {
            timeChosen = "2:00PM - 4:00PM";
            startTime = "14:00";
            endTime = "16:00";
        } else if (item.equals("4:00PM-6:00PM")) {
            timeChosen = "4:00PM - 6:00PM";
            startTime = "16:00";
            endTime = "18:00";
        } else if (item.equals("6:00PM-8:00PM")) {
            timeChosen = "6:00PM - 8:00PM";
            startTime = "18:00";
            endTime = "20:00";
        } else if (item.equals("8:00PM-10:00PM")) {
            timeChosen = "8:00PM - 10:00PM";
            startTime = "20:00";
            endTime = "22:00";
        } else if (item.equals("10:00PM-12:00AM")) {
            timeChosen = "10:00PM - 12:00AM";
            startTime = "22:00";
            endTime = "24:00";
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
