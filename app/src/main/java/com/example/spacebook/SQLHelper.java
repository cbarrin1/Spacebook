package com.example.spacebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class SQLHelper extends SQLiteOpenHelper {

    private ContentValues values;
    private Cursor cursor;

    private ArrayList<User> userList;
    private ArrayList<String> roomList;
    private ArrayList<Reservation> resList;


    public SQLHelper(Context context) {
        super(context, SQLConstants.DATABASE_NAME, null, SQLConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating tables
        db.execSQL(SQLConstants.CREATE_USER_TABLE);
        db.execSQL(SQLConstants.CREATE_ROOM_TABLE);
        db.execSQL(SQLConstants.CREATE_RES_TABLE);

        // inserting room data
        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "001");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "002");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "101");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "NO");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "102");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "103");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "201");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "NO");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "202");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "NO");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "203");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "204");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "205");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "206");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "207");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "208");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "NO");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "209");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "210");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "211");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "212");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "213");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "214");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "215");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "216");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "NO");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "217");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);
        values = new ContentValues();

        values.put(SQLConstants.ROOM_NO, "218");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, "219");
        values.put(SQLConstants.ROOM_LOCATION, "LIBRARY");
        values.put(SQLConstants.HAS_LCD, "YES");
        db.insert(SQLConstants.ROOM_TABLE, null, values);

        // test users
        values = new ContentValues();
        values.put(SQLConstants.USER_EMAIL, "test@bentley.edu");
        values.put(SQLConstants.USER_PASS, "password123");
        db.insert(SQLConstants.USER_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.USER_EMAIL, "admin@bentley.edu");
        values.put(SQLConstants.USER_PASS, "12345");
        db.insert(SQLConstants.USER_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.USER_EMAIL, "android@bentley.edu");
        values.put(SQLConstants.USER_PASS, "testing");
        db.insert(SQLConstants.USER_TABLE, null, values);

        //test reservations
        values = new ContentValues();
        values.put(SQLConstants.USER_EMAIL, "test@bentley.edu");
        values.put(SQLConstants.ROOM_NO, "001");
        values.put(SQLConstants.DATE, "2019-04-01");
        values.put(SQLConstants.TIME_START, "08:00");
        values.put(SQLConstants.TIME_END, "08:30");
        db.insert(SQLConstants.RES_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.USER_EMAIL, "test@bentley.edu");
        values.put(SQLConstants.ROOM_NO, "219");
        values.put(SQLConstants.DATE, "2019-04-01");
        values.put(SQLConstants.TIME_START, "09:00");
        values.put(SQLConstants.TIME_END, "09:30");
        db.insert(SQLConstants.RES_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.USER_EMAIL, "test@bentley.edu");
        values.put(SQLConstants.ROOM_NO, "002");
        values.put(SQLConstants.DATE, "2019-04-01");
        values.put(SQLConstants.TIME_START, "08:00");
        values.put(SQLConstants.TIME_END, "09:30");
        db.insert(SQLConstants.RES_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.USER_EMAIL, "test@bentley.edu");
        values.put(SQLConstants.ROOM_NO, "201");
        values.put(SQLConstants.DATE, "2019-04-01");
        values.put(SQLConstants.TIME_START, "08:00");
        values.put(SQLConstants.TIME_END, "09:30");
        db.insert(SQLConstants.RES_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.USER_EMAIL, "test@bentley.edu");
        values.put(SQLConstants.ROOM_NO, "212");
        values.put(SQLConstants.DATE, "2019-04-01");
        values.put(SQLConstants.TIME_START, "08:00");
        values.put(SQLConstants.TIME_END, "09:30");
        db.insert(SQLConstants.RES_TABLE, null, values);










    }

    //called when database version mismatch
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion >= newVersion) return;

        Log.d("SQLiteDemo", "onUpgrade: Version = " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + SQLConstants.USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SQLConstants.ROOM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SQLConstants.RES_TABLE);
        onCreate(db);
    }

    //method for adding user
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(SQLConstants.USER_EMAIL, user.getUser());
        values.put(SQLConstants.USER_PASS, user.getPass());
        db.insert(SQLConstants.USER_TABLE, null, values);
        Log.d("SQLiteDemo", user.getUser() + " added");
    }

    //method for adding reservation
    public void addRes(Reservation r){
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(SQLConstants.ROOM_NO, r.getRoomNo());
        values.put(SQLConstants.USER_EMAIL, r.getUser_email());
        values.put(SQLConstants.DATE, r.getDate());
        values.put(SQLConstants.TIME_START, r.getStart());
        values.put(SQLConstants.TIME_END, r.getEnd());
        db.insert(SQLConstants.RES_TABLE, null, values);
        Log.d("SQLiteDemo", r.getRes_id() + " added");
    }

    //method for getting all users in DB
    public ArrayList<User> getUserList () {
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.query(SQLConstants.USER_TABLE,
                new String[] {SQLConstants.USER_ID, SQLConstants.USER_EMAIL, SQLConstants.USER_PASS},
                null, null, null, null, SQLConstants.USER_ID);

        //write contents of Cursor to list
        userList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(SQLConstants.USER_ID));
            String u = cursor.getString(cursor.getColumnIndex(SQLConstants.USER_EMAIL));
            String p = cursor.getString(cursor.getColumnIndex(SQLConstants.USER_PASS));

            userList.add(new User(id, u, p));
        }
        return userList;
    }

    //method for getting all reservations in DB (for one user email)
    public ArrayList<Reservation> getResList (String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.query(SQLConstants.RES_TABLE,
                new String[] {SQLConstants.USER_EMAIL, SQLConstants.ROOM_NO, SQLConstants.DATE, SQLConstants.TIME_START, SQLConstants.TIME_END},
                "email = " + email , null, null, null, SQLConstants.DATE);

        //write contents of Cursor to list
        resList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String useremail = cursor.getString(cursor.getColumnIndex(SQLConstants.USER_EMAIL));
            String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
            String date = cursor.getString(cursor.getColumnIndex(SQLConstants.DATE));
            String s = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_START));
            String e = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_END));

            resList.add(new Reservation(useremail, room, date, s, e));
        }
        return resList;
    }

    //gets list of all rooms in DB
    public ArrayList<String> getRoomList () {
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.query(SQLConstants.ROOM_TABLE,
                new String[] {SQLConstants.ROOM_NO},
                null, null, null, null, SQLConstants.ROOM_NO);

        //write contents of Cursor to list
        roomList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
            roomList.add(room);
        }
        return roomList;
    }



}
