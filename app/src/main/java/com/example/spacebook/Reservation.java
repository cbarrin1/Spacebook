package com.example.spacebook;

public class Reservation {

    private int res_id;
    private int room_id;
    private int user_id;
    private String date;
    private String start;
    private String end;


    public int getRes_id() {return res_id;}
    public void setRes_id(int id) {this.res_id = id;}

    public int getRoom_id() {return room_id;}
    public void setRoom_id(int id) {this.room_id = id;}


    public int getUser_id() {return user_id;}
    public void setUser_id(int id) {this.user_id = id;}

    public String getDate() {return date;}
    public void setDate(String d) {this.date = d;}

    public String getStart() {return start;}
    public void setStart(String s) {this.start = s;}

    public String getEnd() {return end;}
    public void setEnd(String e) {this.end = e;}

    public Reservation(int room, int user, String date, String start, String end){
        super();
        this.room_id = room;
        this.user_id = user;
        this.date = date;
        this.start = start;
        this.end = end;
    }
}
