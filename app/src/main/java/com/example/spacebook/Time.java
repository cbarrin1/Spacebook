package com.example.spacebook;

import android.support.annotation.NonNull;

public class Time {

    private int id;
    private String time;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getTime() {return time;}

    public Time(String time){
        super();
        this.time = time;
    }

    @NonNull
    public String toString(){
        return "Time Start: " + time;
    }
}
