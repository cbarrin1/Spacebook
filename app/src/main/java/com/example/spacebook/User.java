package com.example.spacebook;

import android.support.annotation.NonNull;

public class User {

    private int id;
    private String email;
    private String password;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getEmail() {return email;}
    public void setEmail(String user) {this.email = user;}

    public String getPass() {return password;}
    public void setPass(String pass) {this.password = pass;}

    public User(String user, String pass){
        super();
        this.email = user;
        this.password = pass;
    }

    @NonNull
    public String toString(){
        return "Email: " + email + " Password: " + password;
    }

}
