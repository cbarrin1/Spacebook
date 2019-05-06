package com.example.spacebook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CheckBox;
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
import java.util.HashMap;


import android.widget.Button;

public class TabPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    //DB objects
    private SQLiteDatabase db;
    private SQLHelper helper;
    private Cursor cursor;

    // UI Items
    private CheckBox library;
    private CheckBox stu;

    private CalendarView calendar;

    private Button delete;
    int deleteID;
    private TextView dateChosen;
    private Button seeAvailable;
    private Spinner spin;
    private Spinner spin2;

    //setup for dates
    SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy");
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd");
    String startTime, endTime, selectedDate;
    Date rangeStart, rangeEnd, dbStart, dbEnd;

    //list view setup
    private ListView listView;
    private ArrayAdapter<String> adapter = null;
    ArrayList<String> myList = new ArrayList<>();
    ArrayList<Integer> idList = new ArrayList<>();

    ArrayList<Reservation> reservations = new ArrayList<>();

    TabHost tabs;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    //this gets the clicked item from the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                openSettingsDialog();
                break;
            case R.id.menu_logout:
                SharedPreferences.Editor editor = MainActivity.sharedpreferences.edit();
                editor.remove("LOGIN");
                editor.remove("box1");
                editor.remove("box2");
                editor.apply();
                Intent intent = new Intent(TabPage.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openSettingsDialog(){
        settingsDialog settingsDialog = new settingsDialog();
        settingsDialog.show(getSupportFragmentManager(),"settingsDialog");
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);

        //tab setup
        tabs = findViewById(R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec;

        //list of user reservations
        listView = findViewById(R.id.myRes);
        listView.setOnItemClickListener(this);

        adapter = new ArrayAdapter<String>(this, R.layout.item, myList);
        listView.setAdapter(adapter);

        //calendar
        calendar = findViewById(R.id.calendarView);

        //displays date chosen
        dateChosen = findViewById(R.id.textView9);
        //dateChosen.setText("(choose a date on calendar)");

        //setting date display to current date
        String today = df.format(new Date(calendar.getDate()));
        dateChosen.setText(today);
        // setting todays date as initial string for query
        selectedDate = tf.format(new Date(calendar.getDate()));

        //radio buttons for location
        library = findViewById(R.id.checkBox);
        stu = findViewById(R.id.checkBox1);
        //spinner 1 setup
        spin = findViewById(R.id.spinner4);
        spin.setOnItemSelectedListener(this);
        //spinner 2 setup
        spin2 = findViewById(R.id.spinner6);
        spin2.setOnItemSelectedListener(this);
        //DB setup
        helper = new SQLHelper(this);
        db = helper.getWritableDatabase();

        // TAB 1 ----------------------------------------------------------------------------------------------
        // Initialize a TabSpec for tab1 and add it to the TabHost
        spec = tabs.newTabSpec("tag1");
        spec.setContent(R.id.My_Reservations);
        spec.setIndicator("My Reservations");
        tabs.addTab(spec);

        adapter.notifyDataSetChanged();
        delete = findViewById(R.id.button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("pressed");

                    AlertDialog dialog = new AlertDialog.Builder(TabPage.this).create();

                    //set message, title, and icon
                    dialog.setTitle("Room Reservation");
                    dialog.setMessage("Confirm Cancel Reservation");

                    //set three option buttons
                    dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Confirm", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            helper.deleteRes(deleteID);
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

        //grabbing user email
        final String user_login = MainActivity.sharedpreferences.getString("LOGIN", null);

        //query DB for all reservations under the current username
        try {
            cursor = db.rawQuery("SELECT * FROM RESERVATIONS WHERE email = ?", new String[]{user_login});
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("res_id"));
                String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
                String date = cursor.getString(cursor.getColumnIndex(SQLConstants.DATE));
                String start = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_START));
                String end = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_END));

                idList.add(id);
                myList.add("Date: " + date + " Start Time: " + start + " End Time: " + end + " Room: " + room);
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {e.printStackTrace();}

        // TAB 2 ----------------------------------------------------------------------------------------------
        // Initialize a TabSpec for tab2 and add it to the TabHost
        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.Make_Reservations);
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

                // sets textview to date chosen on calculator
                dateChosen.setText((month+1) + "/" + day + "/" + year);

                //cleaning up data to match database format
                mth++;
                newDay = String.format("%02d", day);
                newMonth = String.format("%02d", mth);

                //saving date string for DB query
                selectedDate = yr + "-" + newMonth + "-" + newDay;

            }
        });

        //button to see results
        seeAvailable = findViewById(R.id.button5);
        seeAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(startTime);
                System.out.println(endTime);

                //clear arraylist if not empty
                reservations.clear();

                //convert time strings into dates
                try {
                    rangeStart = format.parse(startTime);
                    rangeEnd = format.parse(endTime);
                } catch (Exception e) {e.printStackTrace();}

                // ensures times selected are valid
                if (rangeEnd.before(rangeStart) || rangeEnd.equals(rangeStart)) {
                    Toast.makeText(getApplicationContext(), "End Time Must Be After Start Time", Toast.LENGTH_SHORT).show();
                }
                else if (rangeEnd.getTime() - rangeStart.getTime() > 7200000) {
                    Toast.makeText(getApplicationContext(), "Maximum Reservation Length is 2 Hours", Toast.LENGTH_SHORT).show();
                }
                else {
                    //query all reservations for selected day
                    try {
                        cursor = db.rawQuery("SELECT * FROM RESERVATIONS WHERE date = ?", new String[]{selectedDate});
                        while (cursor.moveToNext()) {
                            String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
                            String date = cursor.getString(cursor.getColumnIndex(SQLConstants.DATE));
                            String start = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_START));
                            String end = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_END));

                            //convert all time strings to date objects
                            try {
                                dbStart = format.parse(start);
                                dbEnd = format.parse(end);
                            } catch (Exception e) {e.printStackTrace();}

                            // add reservation to list if it falls within time range
                            if (dbEnd.after(rangeStart) || ( dbStart.after(rangeStart) && dbStart.before(rangeEnd))) {
                                reservations.add(new Reservation(room, date, start, end));
                                System.out.println(room + " start " + start + " end " + end);
                            }
                        }

                        // moves to search results page
                        Intent intent = new Intent(TabPage.this, SearchResultPage.class);
                        // creates bundle to pass arraylist
                        Bundle args = new Bundle();
                        args.putSerializable("res",reservations);
                        intent.putExtra("BUNDLE",args);
                        intent.putExtra("email", user_login);
                        intent.putExtra("date", selectedDate);
                        intent.putExtra("start", startTime);
                        intent.putExtra("end", endTime);
                        // starts next intent
                        startActivity(intent);
                    } catch(Exception e) {e.printStackTrace();}
                }
            } // closes OnClick
        }); // closes OnClickListener


    } // closes OnCreate

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        v.setSelected(true);
        deleteID = idList.get(position);

    }

    int i;
    String s;
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        String ampm;

        if(parent.getId() == R.id.spinner4)
        {
            ampm = item.substring(Math.max(item.length() - 2, 0));
            if(ampm.equals("PM")){

                s = item.substring(0, item.indexOf("P"));
                i = Integer.parseInt(s.substring(0, s.indexOf(":")));
                if (i < 12){
                    i += 12;
                    s = s.substring(s.indexOf(":"));
                    s = i + s;
                }
                startTime = s;
            }
            else{
                s = item.substring(0, item.indexOf("A"));
                i = Integer.parseInt(s.substring(0, s.indexOf(":")));
                if (i < 10){
                    s = "0" + s;
                }
                startTime = s;
            }
        }
        else if(parent.getId() == R.id.spinner6)
        {
            ampm = item.substring(Math.max(item.length() - 2, 0));
            if(ampm.equals("PM")){

                s = item.substring(0, item.indexOf("P"));
                i = Integer.parseInt(s.substring(0, s.indexOf(":")));
                if (i < 12){
                    i += 12;
                    s = s.substring(s.indexOf(":"));
                    s = i + s;
                }
                endTime = s;
            }
            else{
                s = item.substring(0, item.indexOf("A"));
                i = Integer.parseInt(s.substring(0, s.indexOf(":")));
                if (i < 10){
                    s = "0" + s;
                }
                endTime = s;
            }
        }

    }
    @Override
    protected void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
