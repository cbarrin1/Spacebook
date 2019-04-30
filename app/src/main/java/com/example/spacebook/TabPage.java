package com.example.spacebook;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
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
import android.view.View.OnClickListener;
import android.app.PendingIntent;


import android.widget.Button;

public class TabPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //DB objects
    private SQLiteDatabase db;
    private SQLHelper helper;
    private Cursor cursor;

    //User Reservation List
    private TextView resList;
    private TextView dateChosen;

    private CheckBox library;
    private CheckBox stu;

    private CalendarView calendar;

    private String timeChosen;
    private String startTime;
    private String endTime;
    private Button seeAvailable;
    private Spinner spin;
    private Spinner spin2;

    private NotificationManager mNotificationManager;
    private int SIMPLE_NOTIFCATION_ID = 25;
    private NotificationCompat.Builder mBuilder = null;

    private String textTitle = "Simple Notification Example";
    private String textContent = "Get back to Application by clicking me";

    //date format for dateChosen
    SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy");

    private ArrayList<Reservation> list;

    String selectedDate;
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


        mNotificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default",
                    "Channel foobar",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Channel description");
            channel.setLightColor(Color.GREEN);
            channel.enableVibration(true);
            mNotificationManager.createNotificationChannel(channel);
        }
        Intent notifyIntent = new Intent(this, TabPage.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder = new NotificationCompat.Builder(this, "default")
                .setContentIntent(pendingIntent)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setSmallIcon(R.drawable.droid)
                .setAutoCancel(true)     //cancel Notification after clicking on it
                //set Android to vibrate when notified
                .setVibrate(new long[] {1000, 1000, 2000, 2000})
                //allow heads up notification; otherwise use PRIORITY_DEFAULT
                .setPriority(NotificationCompat.PRIORITY_HIGH);


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
        library = findViewById(R.id.checkBox);
        stu = findViewById(R.id.checkBox1);

        spin = findViewById(R.id.spinner4);
        spin.setOnItemSelectedListener(this);

        spin2 = findViewById(R.id.spinner6);
        spin2.setOnItemSelectedListener(this);






        //button to see results
       seeAvailable = findViewById(R.id.button5);

       seeAvailable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                mNotificationManager.notify(SIMPLE_NOTIFCATION_ID, mBuilder.build());


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
                        //this list will be pass to search results activity in order to remove times already reserved
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




    //clear notification
        //cancel.setOnClickListener(new View.OnClickListener() {
        //public void onClick(View v) {
          //  mNotificationManager.cancel(SIMPLE_NOTFICATION_ID);
        //}
    //});
}
