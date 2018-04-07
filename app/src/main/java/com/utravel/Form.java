package com.utravel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * This Activity is for the form which the user will fill in for their search. The user will be able
 * to submit a search and load a trip from memory
 */
public class Form extends AppCompatActivity {

    private EditText budgetEditText;
    private SeekBar distanceSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //boiler-plate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        //Get instantiate UI elements
        distanceSeekbar = findViewById(R.id.distanceSeekBar);
        final TextView distanceIndicator = findViewById(R.id.distanceIndicator);
        final Button submitButton = findViewById(R.id.submitButton);
        final Button loadButton = findViewById(R.id.loadButton);
        budgetEditText = findViewById(R.id.budgetEditText);

        //Set a listener to update distance value to user
        distanceSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                distanceIndicator.setText(String.format(Locale.CANADA,"%d km", i*160));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {/*nothing...*/}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {/*nothing...*/}
        });

        //get results and change activity
        //TODO: Find out if we want one result or two
        submitButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: Get Trips from Experts and send to Results using putExtras

                Intent resultsIntent = new Intent(Form.this, Results.class);
                Form.this.startActivity(resultsIntent);
            }
        });

        //get saved trips and change activity
        loadButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loadIntent = new Intent(Form.this, Load.class);
                Form.this.startActivity(loadIntent);
            }
        });
    }

    //Upon leaving activity, save current form state
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("budget", budgetEditText.getText().toString());
        outState.putInt("distance", distanceSeekbar.getProgress());
    }

    //load current form state upon restoration of activity
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        budgetEditText.setText(savedInstanceState.getInt("budget"));
        distanceSeekbar.setProgress(savedInstanceState.getInt("distance"));
    }
}
