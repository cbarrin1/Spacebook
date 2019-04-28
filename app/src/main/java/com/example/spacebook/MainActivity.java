package com.example.spacebook;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private SQLHelper helper;

    private EditText username;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        login = findViewById(R.id.button2);

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

    }

    private void validate (String username, String password) {
        if ((username.equals("admin")) && (password.equals("1234"))) {

            // PLEASE connect THE tab page class to the activity that contains the tabs
            Intent intent = new Intent(MainActivity.this, TabPage.class);
            startActivity(intent);
            Toast.makeText(this, "Login Successful",Toast.LENGTH_SHORT).show();
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
