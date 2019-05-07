package com.example.spacebook;

import android.app.TabActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

// This class is responsible for sending out confirmation emails to recipients
public class EmailConfirmation extends AppCompatActivity {

    // list for emails
    List<EditText> emails;
    Reservation r;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_emails);
        // initialize array and add(find) views
        emails = new ArrayList<>();
        emails.add((EditText) findViewById(R.id.email1));
        emails.add((EditText) findViewById(R.id.email2));
        emails.add((EditText) findViewById(R.id.email3));
        emails.add((EditText) findViewById(R.id.email4));
        emails.add((EditText) findViewById(R.id.email5));

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("reservation");
        r = (Reservation) args.getSerializable("res");

    }
    // Sends emails when button is clicked
    public void sendEmails (View view) {
        // make a string to put all the recepient emails
        String fullRecepients = "";
        // get all the values that are not empty from the list
      for (int i = 0; i<emails.size(); i++){
            if(!emails.get(i).getText().toString().isEmpty()) {
                // append the recipients string
                fullRecepients+= emails.get(i).getText().toString()+ ",";
            }
        }
        // opens gmail and parses recipients addresses, subject and text
        Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:" +fullRecepients));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Study room reservation");
        intent.putExtra(Intent.EXTRA_TEXT, "You have reserved a room. \nDate: " + TabPage.formatDate(r.getDate()) + "\nRoom: " + r.getRoomNo() + "\nReserved Time: " +
                TabPage.convertTime(r.getStart()) + " - " + TabPage.convertTime(r.getEnd()));
        if (intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }
        // finishes when done
        finish();


    }

    // transfers directly to my reservations page
    public void doNotSend (View view) {
        Intent intent =new Intent(EmailConfirmation.this, TabPage.class);
        startActivity(intent);
        finish();
    }


}
