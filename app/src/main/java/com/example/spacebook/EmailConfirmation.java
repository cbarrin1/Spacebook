package com.example.spacebook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailConfirmation extends AppCompatActivity {

    private EditText email1, email2, email3, email4, email5;
    String emailText1, emailText2, emailText3, emailText4, emailText5;
    Button send, noSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_emails);

        email1 = findViewById(R.id.editText);
        email2 = findViewById(R.id.editText3);
        email3 = findViewById(R.id.editText4);
        email4 = findViewById(R.id.editText5);
        email5 = findViewById(R.id.editText6);

        send = findViewById(R.id.button3);
        noSend = findViewById(R.id.button4);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // saving any entered email addresses
                emailText1 = email1.getText().toString();
                emailText2 = email2.getText().toString();
                emailText3 = email3.getText().toString();
                emailText4 = email4.getText().toString();
                emailText5 = email5.getText().toString();

            }
        });

        noSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }
}
