package com.example.spacebook;

import android.support.annotation.NonNull;

public class User {

    private int id;
    private String user_name;
    private String password;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getUser() {return user_name;}
    public void setUser(String user) {this.user_name = user;}

    public String getPass() {return password;}
    public void setPass(String pass) {this.password = pass;}

    public User(int id, String user, String pass){
        super();
        this.id = id;
        this.user_name = user;
        this.password = pass;
    }

    public User(String user, String pass){
        super();
        this.user_name = user;
        this.password = pass;
    }

    @NonNull
    public String toString(){
        return "Email: " + user_name + " Password: " + password;
    }

}
