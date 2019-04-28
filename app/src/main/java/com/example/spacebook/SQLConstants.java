package com.example.spacebook;

public class SQLConstants {

    public static final String DATABASE_NAME = "spacebook.db";
    public static final int DATABASE_VERSION = 1;
    public static final String KEY_ID = " integer primary key autoincrement";

    // USERS table
    public static final String USER_TABLE = "users";
    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASS = "password";
    public static final String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + " (" + USER_ID + KEY_ID + "," +
                                                    USER_EMAIL + " text," + USER_PASS + " text);";
    // USERS TABLE: user_id, email, password

    // ROOMS table
    public static final String ROOM_TABLE = "rooms";
    public static final String ROOM_ID = "room_id";
    public static final String ROOM_NO = "roomNo";
    public static final String ROOM_LOCATION = "location";
    public static final String HAS_LCD = "hasLCD";
    public static final String CREATE_ROOM_TABLE = "CREATE TABLE " + ROOM_TABLE + " (" + ROOM_ID + KEY_ID + "," +
                                                        ROOM_NO + " text," + ROOM_LOCATION + " text," + HAS_LCD + " text);";
    // ROOMS TABLE: room_id, roomNo, location, hasLCD

    // RESERVATIONS table
    public static final String RES_TABLE = "reservations";
    public static final String RES_ID = "res_id";
    public static final String DATE = "date";
    public static final String TIME_START = "start";
    public static final String TIME_END = "end";
    public static final String CREATE_RES_TABLE = "CREATE TABLE " + RES_TABLE + " (" + RES_ID + KEY_ID + "," +
                                                    USER_EMAIL + " text," + ROOM_NO + " text," + DATE + " text," + TIME_START + " text," +
                                                    TIME_END + " text);";
    // RESERVATIONS TABLE: res_id, email, roomNo, date, start, end
}
