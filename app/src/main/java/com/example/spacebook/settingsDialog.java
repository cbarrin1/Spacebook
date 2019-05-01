package com.example.spacebook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.Toast;

import java.util.ArrayList;

public class settingsDialog extends AppCompatDialogFragment {

    private ArrayList<Boolean> settingsChecked;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final SharedPreferences preferences = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        final String[] available = getResources().getStringArray(R.array.settings_array);
        final boolean[] selected = {preferences.getBoolean("box1",false),preferences.getBoolean("box2",false)};
        builder.setMultiChoiceItems(R.array.settings_array,selected, new DialogInterface.OnMultiChoiceClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if (isChecked) {
                    switch (which){
                        case 0:
                            preferences.edit().putBoolean("box1",true).apply();
                            Toast.makeText(getContext(), "item 0 selected", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            preferences.edit().putBoolean("box2",true).apply();
                            Toast.makeText(getContext(), "item 1 selected", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
                if(!isChecked) {
                    switch (which){
                        case 0:
                            preferences.edit().remove("box1").apply();
                            Toast.makeText(getContext(), "item 0 removed", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            preferences.edit().remove("box2").apply();
                            Toast.makeText(getContext(), "item 1 removed", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

        });

        builder.setTitle( "Settings").setPositiveButton("ok",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });
        return builder.create();
    }


}
