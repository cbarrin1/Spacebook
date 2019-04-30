package com.example.spacebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

public class SearchResultPage extends FragmentActivity {

    private ArrayList<Reservation> list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        // retrieving reservation list from tab activity
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        list = (ArrayList<Reservation>) args.getSerializable("ARRAYLIST");


    }
}
