package com.example.spacebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLHelper extends SQLiteOpenHelper {

    SQLHelper(Context context) {
        super(context, SQLConstants.DATABASE_NAME, null, SQLConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating tables
        db.execSQL(SQLConstants.CREATE_USER_TABLE);
        db.execSQL(SQLConstants.CREATE_ROOM_TABLE);
        db.execSQL(SQLConstants.CREATE_RES_TABLE);

        // room data
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('001','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('002','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('101','LIBRARY','NO');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('102','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('103','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('201','LIBRARY','NO');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('202','LIBRARY','No');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('203','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('204','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('205','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('206','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('207','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('208','LIBRARY','NO');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('209','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('210','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('211','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('212','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('213','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('214','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('215','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('216','LIBRARY','NO');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('217','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('218','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('219','LIBRARY','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('700','SC','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('701','SC','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('702','SC','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('703','SC','YES');");
        db.execSQL("INSERT INTO " + SQLConstants.ROOM_TABLE + " (roomNo, location, hasLCD) VALUES ('704','SC','YES');");

        // test users
        db.execSQL("INSERT INTO " + SQLConstants.USER_TABLE + " (email, password, fname, lname) VALUES ('test@bentley.edu','482c811da5d5b4bc6d497ffa98491e38','Joe','Smith');");
        db.execSQL("INSERT INTO " + SQLConstants.USER_TABLE + " (email, password, fname, lname) VALUES ('admin@bentley.edu','7bf0e433abdcc805e5b1fb29898caaf9','John','Doe');");
        db.execSQL("INSERT INTO " + SQLConstants.USER_TABLE + " (email, password, fname, lname) VALUES ('android@bentley.edu','482c811da5d5b4bc6d497ffa98491e38','Jane','Doe');");

        // test reservations
        db.execSQL("INSERT INTO " + SQLConstants.RES_TABLE + " (email, roomNo, date, start, endTime) VALUES ('test@bentley.edu','001','2019-04-01','08:00','08:30');");

    }

    //called when database version mismatch
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion >= newVersion) return;

        Log.d("Spacebook", "onUpgrade: Version = " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + SQLConstants.USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SQLConstants.ROOM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SQLConstants.RES_TABLE);
        onCreate(db);
    }

    //method for adding user - disabled for now
/*
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        Log.d("Spacebook", user.getUser() + " added");
    }
*/
    //method for adding reservation
    void addRes(Reservation r){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + SQLConstants.RES_TABLE + " (" + SQLConstants.USER_EMAIL + "," + SQLConstants.ROOM_NO + "," + SQLConstants.DATE +
                "," + SQLConstants.TIME_START + "," + SQLConstants.TIME_END + ") VALUES ('" + r.getUser_email() + "','" + r.getRoomNo() + "','" +
                r.getDate() + "','" + r.getStart() + "','" + r.getEnd() + "');");
        Log.d("Spacebook", r.getRes_id() + " added");
    }

    void deleteRes(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SQLConstants.RES_TABLE, "res_id =?",new String[]{Integer.toString(id)});
        Log.d("Spacebook", "item deleted");
    }
}
