package com.utravel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * This activity will provide a list of saved flights where they can then be loaded from memory.
 */
public class Load extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TODO: Get ArrayList of results and display...
    }
}
