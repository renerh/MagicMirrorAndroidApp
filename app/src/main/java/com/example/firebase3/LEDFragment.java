package com.example.firebase3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * <h1>LED Fragment!</h1>
 * The Status Fragment shows the Green, Red, Blue, and
 * desk lights buttons so that you can send a reference
 * to firebase database to turn on the RGB Strip and the
 * lamp. The app would send a signal(False or True) and
 * the raspberry pi would constantly ask firebase if there
 * has been a change in those references through a python
 * script. Then if there has been a change it would turn
 * the accordingly GPIO pin ON or OFF
 *
 * @author  Rene Hermosillo
 * @since   04-06-2019
 */

public class LEDFragment extends Fragment {
    public static LEDFragment newInstance(){
        LEDFragment fragment = new LEDFragment();
        return fragment;
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refHome = database.getReference("home");
    DatabaseReference refLuces, redLights, blueLights, greenLights, deskLights;
    public ToggleButton red, blue, green, desk;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_led , container, false);

        refLuces = refHome.child("luces");
        redLights = refLuces.child("redLights");
        blueLights = refLuces.child("blueLights");
        greenLights = refLuces.child("greenLights");
        deskLights = refLuces.child("deskLights");


        red = (ToggleButton) view.findViewById(R.id.red);
        blue = (ToggleButton) view.findViewById(R.id.blue);
        green = (ToggleButton) view.findViewById(R.id.green);
        desk = (ToggleButton) view.findViewById(R.id.desk);



        controlRed(redLights, red);
        controlBlue(blueLights, blue);
        controlGreen(greenLights, green);
        controlDesk(deskLights, desk);



        return view;
    }

    private void controlDesk(final DatabaseReference refDesk, final ToggleButton desk ) {

        desk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refDesk.setValue(isChecked);
            }
        });

        refDesk.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean estado_led  = (Boolean) dataSnapshot.getValue();
                desk.setChecked(estado_led);
                if(estado_led){
                    desk.setTextOn("Desk Light\n OFF");
                    global.desk = "Desk Lights OFF";

                } else {
                    desk.setTextOff("Desk Light\n ON");
                    global.desk = "Desk Lights ON";

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }

        });
    }


        private void controlRed(final DatabaseReference refRed, final ToggleButton red ) {

        red.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refRed.setValue(isChecked);
            }
        });

        refRed.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean estado_led  = (Boolean) dataSnapshot.getValue();
                red.setChecked(estado_led);
                if(estado_led){
                    red.setTextOn("ON");
                    global.red = "Red Lights ON";
                } else {
                    red.setTextOff("OFF");
                    global.red = "Red Lights OFF";
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }

        });
    }




    private void controlBlue(final DatabaseReference refBlue, final ToggleButton blue ) {

        blue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refBlue.setValue(isChecked);
            }
        });

        refBlue.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean estado_led  = (Boolean) dataSnapshot.getValue();
                blue.setChecked(estado_led);
                if(estado_led){
                    blue.setTextOn("ON");
                    global.blue = "Blue Lights ON";
                } else {
                    blue.setTextOff("OFF");
                    global.blue = "Blue Lights OFF";
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }

        });
    }




    private void controlGreen(final DatabaseReference refGreen, final ToggleButton green ) {

        green.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refGreen.setValue(isChecked);
            }
        });

        refGreen.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean estado_led  = (Boolean) dataSnapshot.getValue();
                green.setChecked(estado_led);
                if(estado_led){
                    green.setTextOn("ON");
                    global.green = "Green Lights ON";
                } else {
                    green.setTextOff("OFF");
                    global.green = "Green Lights OFF";
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }

        });
    }




}
