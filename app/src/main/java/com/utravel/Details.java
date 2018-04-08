package com.utravel;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * This activity will display the returned trip of the search using the user's inputs.
 */
public class Details extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        //make back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // TODO: Get ArrayList of result using getExtras


    }
}
