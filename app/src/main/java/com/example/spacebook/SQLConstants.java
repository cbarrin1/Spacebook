package com.example.spacebook;

class SQLConstants {

    static final String DATABASE_NAME = "spacebook.db";
    static final int DATABASE_VERSION = 1;
    private static final String KEY_ID = " integer primary key autoincrement not null";

    // USERS table
    static final String USER_TABLE = "users";
    static final String USER_ID = "user_id";
    static final String USER_EMAIL = "email";
    static final String USER_PASS = "password";
    static final String FIRST_NAME = "fname";
    static final String LAST_NAME = "lname";
    static final String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + " (" + USER_ID + KEY_ID + "," +
                                                    USER_EMAIL + " text," + USER_PASS + " text," + FIRST_NAME + " text," + LAST_NAME +  " text);";
    // USERS TABLE: user_id, email, password, fname, lname

    // ROOMS table
    static final String ROOM_TABLE = "rooms";
    static final String ROOM_ID = "room_id";
    static final String ROOM_NO = "roomNo";
    static final String ROOM_LOCATION = "location";
    static final String HAS_LCD = "hasLCD";
    static final String CREATE_ROOM_TABLE = "CREATE TABLE " + ROOM_TABLE + " (" + ROOM_ID + KEY_ID + "," +
                                                        ROOM_NO + " text," + ROOM_LOCATION + " text," + HAS_LCD + " text);";
    // ROOMS TABLE: room_id, roomNo, location, hasLCD

    // RESERVATIONS table
    static final String RES_TABLE = "reservations";
    static final String RES_ID = "res_id";
    static final String DATE = "date";
    static final String TIME_START = "start";
    static final String TIME_END = "endTime";
    static final String CREATE_RES_TABLE = "CREATE TABLE " + RES_TABLE + " (" + RES_ID + KEY_ID + "," + ROOM_ID + " text," +
                                                    USER_EMAIL + " text," + ROOM_NO + " text," + DATE + " text," + TIME_START + " text," +
                                                    TIME_END + " text, CONSTRAINT fk_rooms" +
                                                    " FOREIGN KEY (room_id)\n" +
                                                    " REFERENCES rooms(room_id)) ;";
    // RESERVATIONS TABLE: res_id, email, roomNo, date, start, end
}
