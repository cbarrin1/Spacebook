package com.example.spacebook;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    // DB objects
    private SQLiteDatabase db;
    private SQLHelper helper;
    private Cursor cursor;

    // UI elements
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

        //saving preferences for future use
        sharedpreferences=getApplicationContext().getSharedPreferences("Preferences", 0);
        sharedpreferences.getBoolean("box1",false);
        sharedpreferences.getBoolean("box2",false);
        //saving login email as a string
        String user_login = sharedpreferences.getString("LOGIN", null);
        //if the user is currently still logged in, start the next activity
        if (user_login != null) {
            Intent intent = new Intent(MainActivity.this, TabPage.class);
            startActivity(intent);
            //finish();
        }
        //open db
        helper = new SQLHelper(this);
        db = helper.getWritableDatabase();

        //Login Button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(), password.getText().toString());
            }
        });

    }

    private void validate (String username, String password) {
        //string to save DB query result
        String dbPass = "";
        //look for users in DB with an email that matches input
        try {
            cursor = db.rawQuery("SELECT password FROM USERS WHERE email = ?", new String[]{username});
            while (cursor.moveToNext()) {
                dbPass = cursor.getString(cursor.getColumnIndex(SQLConstants.USER_PASS));
            }
        } catch(Exception e) {e.printStackTrace();}

        if (!dbPass.isEmpty()){
            //compares user input password with password in DB
            if (dbPass.equals(hashMD5(password))){
                //moves to next activity
                Intent intent = new Intent(MainActivity.this, TabPage.class);
                startActivity(intent);

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("LOGIN", username);
                editor.apply();
                Toast.makeText(this, "Login Successful",Toast.LENGTH_SHORT).show();
                finish();
            }
            else
                Toast.makeText(this, "Login FAILED",Toast.LENGTH_SHORT).show();
        }
    }
    private static String hashMD5(String pass) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(pass.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {e.printStackTrace();}
        return generatedPassword;
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(db != null)
            db.close();
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
    @Override
    protected void onRestart(){
        super.onRestart();
        if(!db.isOpen()){
            helper = new SQLHelper(this);
            db = helper.getWritableDatabase();
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(!db.isOpen()){
            helper = new SQLHelper(this);
            db = helper.getWritableDatabase();
        }
    }
}
