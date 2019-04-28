package com.example.spacebook;

import android.support.annotation.NonNull;

public class Reservation {

    private int res_id;
    private String room_no;
    private String user_email;
    private String date;
    private String start;
    private String end;


    public int getRes_id() {return res_id;}
    public void setRes_id(int id) {this.res_id = id;}

    public String getRoom_id() {return room_no;}
    public void setRoom_id(String room) {this.room_no = room;}


    public String getUser_email() {return user_email;}
    public void setUser_email(String email) {this.user_email = email;}

    public String getDate() {return date;}
    public void setDate(String d) {this.date = d;}

    public String getStart() {return start;}
    public void setStart(String s) {this.start = s;}

    public String getEnd() {return end;}
    public void setEnd(String e) {this.end = e;}

    public Reservation(String room, String user, String date, String start, String end){
        super();
        this.room_no = room;
        this.user_email = user;
        this.date = date;
        this.start = start;
        this.end = end;
    }
    @NonNull
    public String toString(){
        return room_no + " " + user_email + " " +  date + " " + start + " " + end;
    }
}
