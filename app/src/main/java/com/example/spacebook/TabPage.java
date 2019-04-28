package com.example.spacebook;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TabHost;


public class TabPage extends FragmentActivity implements AdapterView.OnItemSelectedListener  {

    TabHost tabs;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);

/*
        tabs = findViewById(R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec;


        // Initialize a TabSpec for tab1 and add it to the TabHost
        spec = tabs.newTabSpec("tag1");    //create new tab specification
        spec.setContent(R.id.My_Reservations);    //add tab view content
        spec.setIndicator("My Reservations");    //put text on tab
        tabs.addTab(spec);             //put tab in TabHost container


        // Initialize a TabSpec for tab2 and add it to the TabHost
        spec = tabs.newTabSpec("tag2");        //create new tab specification
        spec.setContent(R.id.Make_Reservations);            //add view tab content
        spec.setIndicator("Make Reservations");
        tabs.addTab(spec);
*/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
