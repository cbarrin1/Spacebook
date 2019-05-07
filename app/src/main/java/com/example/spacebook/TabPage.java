package com.example.spacebook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TabHost;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.widget.Button;

public class TabPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    //DB objects
    private SQLiteDatabase db;
    private SQLHelper helper;
    private Cursor cursor;

    // UI Items
    private CheckBox library;
    private CheckBox stu;
    private TextView dateChosen;
    CalendarView calendar;
    Button delete;
    Button seeAvailable;
    Spinner spin;
    Spinner spin2;

    //setup for dates
    SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy", Locale.US);
    SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.US);
    SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    String startTime, endTime, selectedDate;
    Date rangeStart, rangeEnd, dbStart, dbEnd;

    //list view setup
    ListView listView;
    private ArrayAdapter<String> adapter = null;
    ArrayList<String> myList = new ArrayList<>();
    ArrayList<Integer> idList = new ArrayList<>();
    ArrayList<Reservation> reservations = new ArrayList<>();
    private ArrayList<String> roomList = new ArrayList<>();
    String searchAgain;
    int deleteID;

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

        //DB setup
        helper = new SQLHelper(this);
        db = helper.getWritableDatabase();

// TAB 1 ------------------------------------------------------------------------------------------------------------------------------------------
        // Initialize a TabSpec for tab1 and add it to the TabHost
        spec = tabs.newTabSpec("tag1");
        spec.setContent(R.id.My_Reservations);
        spec.setIndicator("My Reservations");
        tabs.addTab(spec);

        //list of user reservations
        listView = findViewById(R.id.myRes);
        listView.setOnItemClickListener(this);
        adapter = new ArrayAdapter<>(this, R.layout.item, myList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // cancel reservation button
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
                            myList.clear();
                            try {
                                cursor = db.rawQuery("SELECT * FROM RESERVATIONS WHERE email = ?", new String[]{searchAgain});
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
                        }
                    });

                    dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //does not do anything
                        }
                    });
                    dialog.show();
            }});

        //grabbing user email
        final String user_login = MainActivity.sharedpreferences.getString("LOGIN", null);
        searchAgain = user_login;

        try {
            cursor = db.rawQuery("SELECT * FROM RESERVATIONS WHERE email = ?", new String[]{user_login});
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("res_id"));
                String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
                String date = cursor.getString(cursor.getColumnIndex(SQLConstants.DATE));
                String start = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_START));
                String end = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_END));

                idList.add(id);
                myList.add("Date: " + formatDate(date) + "\nRoom: " + room + "\nReserved Time: " + convertTime(start) + " - " + convertTime(end));
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {e.printStackTrace();}

// TAB 2 ------------------------------------------------------------------------------------------------------------------------------------------
        // Initialize a TabSpec for tab2 and add it to the TabHost
        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.Make_Reservations);
        spec.setIndicator("Make Reservations");
        tabs.addTab(spec);

        //calendar
        calendar = findViewById(R.id.calendarView);
        //displays date chosen
        dateChosen = findViewById(R.id.textView9);
        //setting date display to current date
        dateChosen.setText(df.format(new Date(calendar.getDate())));
        // setting today's date as string for db query
        selectedDate = tf.format(new Date(calendar.getDate()));

        //check boxes for location
        library = findViewById(R.id.checkBox);
        stu = findViewById(R.id.checkBox1);
        //set listeners for tab1
        library.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v){
            }
        });
        stu.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v){
            }
        });

        //spinner 1 setup
        spin = findViewById(R.id.spinner4);
        spin.setOnItemSelectedListener(this);
        //spinner 2 setup
        spin2 = findViewById(R.id.spinner6);
        spin2.setOnItemSelectedListener(this);

        //listener for changing date on calendar
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // setting up calendar
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, dayOfMonth);
                int newMonth = month + 1;

                // set textview to date chosen on calendar
                String selection = (month+1) + "/" + dayOfMonth + "/" + year;
                dateChosen.setText(selection);
                // formatting data to fit with db
                String dayString = String.format(Locale.US,"%02d", dayOfMonth);
                String monthString = String.format(Locale.US, "%02d", newMonth);
                // saving date string for DB query
                selectedDate = year + "-" + monthString + "-" + dayString;
            }
        });

        // button to see results
        seeAvailable = findViewById(R.id.button5);
        seeAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //query DB for all reservations under the current username
                String location = "";
                if (library.isChecked() && !stu.isChecked()) {
                    location = "LIBRARY";
                }
                else if (!library.isChecked() && stu.isChecked()) {
                    location = "SC";
                }

                //clear arraylist holding reservations
                reservations.clear();
                roomList.clear();

                //convert time strings into dates
                try {
                    rangeStart = format.parse(startTime);
                    rangeEnd = format.parse(endTime);
                    System.out.println(rangeStart);
                    System.out.println(rangeEnd);
                } catch (Exception e) {e.printStackTrace();}

                // ensures times selected are valid
                if (rangeEnd.before(rangeStart) || rangeEnd.equals(rangeStart)) {
                    Toast.makeText(getApplicationContext(), "End Time Must Be After Start Time", Toast.LENGTH_SHORT).show();
                    System.out.println(rangeEnd + " " + rangeStart);
                }
                else if (rangeEnd.getTime() - rangeStart.getTime() > 7200000) {
                    Toast.makeText(getApplicationContext(), "Maximum Reservation Length is 2 Hours", Toast.LENGTH_SHORT).show();
                }
                else {
                    System.out.println(rangeEnd + " " + rangeStart);
                    //query all reservations for selected day
                    if (location.isEmpty()) {
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
                                if (dbEnd.after(rangeStart) || (dbStart.after(rangeStart) && dbStart.before(rangeEnd))) {
                                    reservations.add(new Reservation(room, date, start, end));
                                }
                            }
                        } catch (Exception e) {e.printStackTrace();}
                        // retrieves list of all rooms
                        try {
                            cursor = db.rawQuery("SELECT * FROM ROOMS", null);
                            while (cursor.moveToNext()) {
                                String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
                                roomList.add(room);
                            }
                        } catch(Exception e) {e.printStackTrace();}
                    } else {
                        try {
                            cursor = db.rawQuery("SELECT * FROM RESERVATIONS INNER JOIN ROOMS ON RESERVATIONS.room_id = ROOMS.room_id WHERE date =? AND location =?", new String[]{selectedDate, location});
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
                                if (dbEnd.after(rangeStart) || (dbStart.after(rangeStart) && dbStart.before(rangeEnd))) {
                                    reservations.add(new Reservation(room, date, start, end));
                                }
                            }
                        } catch (Exception e) {e.printStackTrace();}
                        // retrieves list of selected rooms
                        try {
                            cursor = db.rawQuery("SELECT * FROM ROOMS WHERE location =?", new String[]{location});
                            while (cursor.moveToNext()) {
                                String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
                                roomList.add(room);
                            }
                        } catch(Exception e) {e.printStackTrace();}
                    }

                    // moves to search results page
                    Intent intent = new Intent(TabPage.this, SearchResultPage.class);
                    // creates bundle to pass arraylist
                    Bundle args = new Bundle();
                    args.putSerializable("res",reservations);
                    intent.putExtra("BUNDLE",args);
                    Bundle args2 = new Bundle();
                    args2.putSerializable("room",roomList);
                    intent.putExtra("rooms",args2);
                    intent.putExtra("email", user_login);
                    intent.putExtra("date", selectedDate);
                    intent.putExtra("start", startTime);
                    intent.putExtra("end", endTime);
                    // starts next intent
                    startActivity(intent);
                }
            } // closes OnClick
        }); // closes OnClickListener
    } // closes OnCreate

    public static String convertTime(String s){
        String newTime = s.substring(0, s.indexOf(":"));
        int i = Integer.parseInt(newTime);

        if (i == 12) {
            newTime = i + s.substring(s.indexOf(":")) + "PM";
        }
        else if (i > 12) {
            i -= 12;
            newTime = i + s.substring(s.indexOf(":")) + "PM";
        }
        else {
            newTime = i + s.substring(s.indexOf(":")) + "AM";
        }
        return newTime;
    }

    public static String formatDate(String s){
        int m, d, y;

        y = Integer.parseInt(s.substring(0, s.indexOf("-")));
        m = Integer.parseInt(s.substring(s.indexOf("-") + 1, s.indexOf("-", s.indexOf("-") + 1)));
        d = Integer.parseInt(s.substring(Math.max(s.length() - 2, 0)));

        return m + "/" + d + "/" + y;
    }

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        v.setSelected(true);
        deleteID = idList.get(position);
    }
    String s, ampm;
    int i;
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        if(parent.getId() == R.id.spinner4) {
            ampm = item.substring(Math.max(item.length() - 2, 0));
            if(ampm.equals("PM")){
                s = item.substring(0, item.indexOf("P"));
                i = Integer.parseInt(s.substring(0, s.indexOf(":")));
                if (i < 12){
                    i += 12;
                    s = s.substring(s.indexOf(":"));
                    startTime = i + s;
                }
                else
                    startTime = s;
            }
            else{
                s = item.substring(0, item.indexOf("A"));
                i = Integer.parseInt(s.substring(0, s.indexOf(":")));
                if (i < 10){
                    startTime = "0" + s;
                }
                else
                    startTime = s;
            }
        }
        else if(parent.getId() == R.id.spinner6) {
            ampm = item.substring(Math.max(item.length() - 2, 0));
            if(ampm.equals("PM")){
                s = item.substring(0, item.indexOf("P"));
                i = Integer.parseInt(s.substring(0, s.indexOf(":")));

                if (i < 12){
                    i += 12;
                    s = s.substring(s.indexOf(":"));
                    endTime = i + s;
                }
                else
                    endTime = s;
            }
            else{
                s = item.substring(0, item.indexOf("A"));
                i = Integer.parseInt(s.substring(0, s.indexOf(":")));
                if (i < 10){
                    endTime = "0" + s;
                }
                else
                    endTime = s;
            }
        }
    } // end onItemSelected

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        helper = new SQLHelper(this);
        db = helper.getWritableDatabase();
        myList.clear();
        try {
            cursor = db.rawQuery("SELECT * FROM RESERVATIONS WHERE email = ?", new String[]{searchAgain});
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("res_id"));
                String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
                String date = cursor.getString(cursor.getColumnIndex(SQLConstants.DATE));
                String start = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_START));
                String end = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_END));

                idList.add(id);
                myList.add("Date: " + formatDate(date) + "\nRoom: " + room + "\nReserved Time: " + convertTime(start) + " - " + convertTime(end));
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        helper = new SQLHelper(this);
        db = helper.getWritableDatabase();
        myList.clear();
        try {
            cursor = db.rawQuery("SELECT * FROM RESERVATIONS WHERE email = ?", new String[]{searchAgain});
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("res_id"));
                String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
                String date = cursor.getString(cursor.getColumnIndex(SQLConstants.DATE));
                String start = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_START));
                String end = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_END));

                idList.add(id);
                myList.add("Date: " + formatDate(date) + "\nRoom: " + room + "\nReserved Time: " + convertTime(start) + " - " + convertTime(end));
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {e.printStackTrace();}
    }
    @Override
    protected void onStop(){
        super.onStop();
        if(db != null)
            db.close();
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
        }
    }
}
