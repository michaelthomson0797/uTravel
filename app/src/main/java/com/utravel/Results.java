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
 * This activity will display the result of the search using the user's inputs.
 * It is still undecided if we should use a list of results or just one result
 */
public class Results extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        final ListView resultsListView = findViewById(R.id.resultsListView);

        //make back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // TODO: Get ArrayList of result using getExtras


    }
}
