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
    private ArrayList<Time> timeList;
    private ArrayList<Room> roomList;
    private ArrayList<Reservation> resList;


    public SQLHelper(Context context) {
        super(context, SQLConstants.DATABASE_NAME, null, SQLConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLConstants.CREATE_USER_TABLE);
        db.execSQL(SQLConstants.CREATE_TIME_TABLE);
        db.execSQL(SQLConstants.CREATE_ROOM_TABLE);
        db.execSQL(SQLConstants.CREATE_RES_TABLE);

        // initial time inserts
        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "08:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "08:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "08:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "08:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "09:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "09:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "09:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "09:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "10:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "10:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "10:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "10:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "11:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "11:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "11:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "11:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "12:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "12:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "12:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "12:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "13:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "13:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "13:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "13:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "14:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "14:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "14:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "14:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "15:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "15:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "15:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "15:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "16:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "16:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "16:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "16:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "17:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "17:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "17:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "17:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "18:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "18:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "18:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "18:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "19:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "19:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "19:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "19:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "20:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "20:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "20:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "20:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "21:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "21:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "21:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "21:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "22:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "22:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "22:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "22:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "23:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "23:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "23:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "23:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "24:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "0:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "0:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "0:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "1:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "1:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "1:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "1:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "2:00");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "2:15");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "2:30");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        values = new ContentValues();
        values.put(SQLConstants.TIME_START, "2:45");
        db.insert(SQLConstants.TIME_TABLE, null, values);

        // initial room inserts

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

        // test user
        values = new ContentValues();
        values.put(SQLConstants.USER_EMAIL, "test@bentley.edu");
        values.put(SQLConstants.USER_PASS, "password123");
        db.insert(SQLConstants.USER_TABLE, null, values);

    }

    //called when database version mismatch
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion >= newVersion) return;

        Log.d("SQLiteDemo", "onUpgrade: Version = " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + SQLConstants.USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SQLConstants.TIME_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SQLConstants.ROOM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SQLConstants.RES_TABLE);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(SQLConstants.USER_EMAIL, user.getUser());
        values.put(SQLConstants.USER_PASS, user.getPass());
        db.insert(SQLConstants.USER_TABLE, null, values);
        Log.d("SQLiteDemo", user.getUser() + " added");
        db.close();
    }

    public void addRes(Reservation r){
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(SQLConstants.ROOM_ID, r.getRoom_id());
        values.put(SQLConstants.TIME_ID, r.getTime_id());
        values.put(SQLConstants.USER_ID, r.getUser_id());
        values.put(SQLConstants.length, r.getLength());
        values.put(SQLConstants.date, r.getDate());
        db.insert(SQLConstants.RES_TABLE, null, values);
        Log.d("SQLiteDemo", r.getRes_id() + " added");
        db.close();
    }

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
        db.close();
        return userList;
    }

    public ArrayList<Time> getTimeList () {

        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.query(SQLConstants.TIME_TABLE,
                new String[] {SQLConstants.TIME_START},
                null, null, null, null, SQLConstants.TIME_START);

        //write contents of Cursor to list
        timeList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String str = cursor.getString(cursor.getColumnIndex(SQLConstants.TIME_START));
            timeList.add(new Time(str));
        }
        db.close();
        return timeList;
    }

    public ArrayList<Room> getRoomList () {

        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.query(SQLConstants.ROOM_TABLE,
                new String[] {SQLConstants.ROOM_NO, SQLConstants.ROOM_LOCATION, SQLConstants.HAS_LCD},
                null, null, null, null, SQLConstants.ROOM_NO);

        //write contents of Cursor to list
        roomList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String room = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_NO));
            String location = cursor.getString(cursor.getColumnIndex(SQLConstants.ROOM_LOCATION));
            String lcd = cursor.getString(cursor.getColumnIndex(SQLConstants.HAS_LCD));

            roomList.add(new Room(room, location, lcd));
        }
        db.close();
        return roomList;
    }



}
