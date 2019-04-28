package com.example.spacebook;

import android.database.Cursor;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private SQLHelper helper;

    private EditText username;
    private EditText password;
    private Button login;
    private Cursor cursor;
    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            username.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
            login.setVisibility(View.VISIBLE);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        login = findViewById(R.id.button2);
        handler.postDelayed(runnable,2500);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(), password.getText().toString());
            }
        });

        helper = new SQLHelper(this);

        //create database
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {
            Log.d("Spacebook", "Create database failed");
        }

        helper.addRes(new Reservation("001", "test@bentley.edu", "2019-04-27", "08:00","09:00"));

    }

    private void validate (String username, String password) {
        String pass = "";
        try {
            cursor = db.rawQuery("SELECT password FROM USERS WHERE email = ?", new String[]{username});
            while (cursor.moveToNext()) {
                pass = cursor.getString(cursor.getColumnIndex(SQLConstants.USER_PASS));
            }
        } catch(Exception e) { e.printStackTrace();}

        if ((pass.equals(password))){

            Intent intent = new Intent(MainActivity.this, TabPage.class);
            intent.putExtra("user", username);
            startActivity(intent);
            Toast.makeText(this, "Login Successful",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Login FAILED",Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    protected void onPause() {
        super.onPause();
        if(db != null)
            db.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(db != null) {
            db.close();
            Log.d("Spacebook", "DB CLOSED");
        }

    }
}
