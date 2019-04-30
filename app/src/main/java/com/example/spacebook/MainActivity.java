package com.example.spacebook;

import android.content.SharedPreferences;
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
    //DB objects
    private SQLiteDatabase db;
    private SQLHelper helper;
    private Cursor cursor;

    private EditText username;
    private EditText password;
    private Button login;

    public static SharedPreferences sharedpreferences;
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

        // UI elements
        username = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        login = findViewById(R.id.button2);
        handler.postDelayed(runnable,2500);

        sharedpreferences=getApplicationContext().getSharedPreferences("Preferences", 0);
        String user_login = sharedpreferences.getString("LOGIN", null);

        if (user_login != null) {
            Intent intent = new Intent(MainActivity.this, TabPage.class);
            startActivity(intent);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(), password.getText().toString());
            }
        });

        //SQLite helper instance
        helper = new SQLHelper(this);
        //opens DB
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {Log.d("Spacebook", "Create database failed");}

    }

    private void validate (String username, String password) {
        //string to save DB query result
        String pass = "";
        //look for users in DB with an email that matches input
        try {
            cursor = db.rawQuery("SELECT password FROM USERS WHERE email = ?", new String[]{username});
            while (cursor.moveToNext()) {
                pass = cursor.getString(cursor.getColumnIndex(SQLConstants.USER_PASS));
            }
        } catch(Exception e) {e.printStackTrace();}

        //compares user input password with password in DB
        //password is stored in plain text, as all accounts are just test accounts
        if ((pass.equals(password))){
            //moves to next activity
            Intent intent = new Intent(MainActivity.this, TabPage.class);
            startActivity(intent);

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("LOGIN", username);
            editor.apply();
            Toast.makeText(this, "Login Successful",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Login FAILED",Toast.LENGTH_SHORT).show();
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
        }
    }
}
