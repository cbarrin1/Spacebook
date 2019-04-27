package com.example.spacebook;

public class Reservation {

    private int res_id;
    private int room_id;
    private int time_id;
    private int user_id;
    private int length;
    private String date;

    public int getRes_id() {return res_id;}
    public void setRes_id(int id) {this.res_id = id;}

    public int getRoom_id() {return room_id;}
    public void setRoom_id(int id) {this.room_id = id;}

    public int getTime_id() {return time_id;}
    public void setTime_id(int id) {this.time_id = id;}

    public int getUser_id() {return user_id;}
    public void setUser_id(int id) {this.user_id = id;}

    public int getLength() {return length;}
    public void setLength(int len) {this.length = len;}

    public String getDate() {return date;}
    public void setDate(String d) {this.date = d;}

    public Reservation(int room, int time, int user, int len, String d){
        super();
        this.room_id = room;
        this.time_id = time;
        this.user_id = user;
        this.length = len;
        this.date = d;
    }
}
