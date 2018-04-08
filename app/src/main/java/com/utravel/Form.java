package com.utravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;

import java.util.Locale;

/**
 * This Activity is for the form which the user will fill in for their search. The user will be able
 * to submit a search and load a trip from memory
 */
public class Form extends AppCompatActivity {

    private EditText budgetEditText;
    private EditText departureDateEditText;
    private EditText tripDurationEditText;
    private SeekBar distanceSeekbar;
    private PlaceAutocompleteFragment autocompleteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //boiler-plate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        //instantiate UI elements
        autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        distanceSeekbar = findViewById(R.id.distanceSeekBar);
        final TextView distanceIndicator = findViewById(R.id.distanceIndicator);
        final Button submitButton = findViewById(R.id.submitButton);
        final Button loadButton = findViewById(R.id.loadButton);
        budgetEditText = findViewById(R.id.budgetEditText);
        departureDateEditText = findViewById(R.id.departureDate);
        tripDurationEditText = findViewById(R.id.tripDuration);


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

        //get trip detail and change activity
        //TODO: Find out if we want one result or two
        submitButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: Get Trip from Experts and send to Details using putExtras

                Intent resultsIntent = new Intent(Form.this, Details.class);
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
