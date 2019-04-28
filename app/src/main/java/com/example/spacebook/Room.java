package com.example.spacebook;

import android.support.annotation.NonNull;

public class Room {

    private int id;
    private String roomNo;
    private String location;
    private String lcd;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getRoomNo() {return roomNo;}
    public void setRoomNo(String room) {this.roomNo = room;}

    public String getLocation() {return location;}
    public void setLocation(String loc) {this.location = loc;}

    public String getLcd() {return lcd;}
    public void setLcd(String hasLCD) {this.lcd = hasLCD;}

    public Room(String room, String loc, String hasLCD){
        super();
        this.roomNo = room;
        this.location = loc;
        this.lcd = hasLCD;
    }

    @NonNull
    public String toString(){
        return "Room ID: " + id + " Room No: " + roomNo + " Location: " + location;
    }

}
