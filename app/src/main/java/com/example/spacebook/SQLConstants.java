package com.example.spacebook;

public class SQLConstants {
    public static final String DATABASE_NAME = "spacebook.db";
    public static final int DATABASE_VERSION = 1;
    public static final String KEY_ID = " integer primary key autoincrement";

    // user table
    public static final String USER_TABLE = "users";
    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASS = "password";
    public static final String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + " (" + USER_ID + KEY_ID + "," +
                                                    USER_EMAIL + " text," + USER_PASS + " text);";

    // time slot table
    public static final String TIME_TABLE = "times";
    public static final String TIME_ID = "time_id";
    public static final String TIME_START = "timeStart";
    public static final String CREATE_TIME_TABLE = "CREATE TABLE " + TIME_TABLE + " (" + TIME_ID + KEY_ID + "," + TIME_START + " text);";

    // room table
    public static final String ROOM_TABLE = "rooms";
    public static final String ROOM_ID = "room_id";
    public static final String ROOM_NO = "roomNum";
    public static final String ROOM_LOCATION = "location";
    public static final String HAS_LCD = "hasLCD";
    public static final String CREATE_ROOM_TABLE = "CREATE TABLE " + ROOM_TABLE + " (" + ROOM_ID + KEY_ID + "," +
                                                        ROOM_NO + " text," + ROOM_LOCATION + " text," + HAS_LCD + " text);";

    //reservation table
    public static final String RES_TABLE = "reservations";
    public static final String RES_ID = "res_id";
    public static final String length = "length";
    public static final String date = "date";
    public static final String CREATE_RES_TABLE = "CREATE TABLE " + RES_TABLE + " (" + RES_ID + KEY_ID + "," +
                                                    USER_ID + " text," + ROOM_ID + " text," + TIME_ID + " text," +  length + " integer," +
                                                    date + " text);";



}
