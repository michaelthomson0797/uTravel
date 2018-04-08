package com.utravel;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.List;


/**
 * This activity will display the returned trip of the search using the user's inputs.
 */
public class Details extends AppCompatActivity implements OnMapReadyCallback{

    //TODO: instantiate variables to hold TextView items?
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        //make back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //instantiate info textViews
        final TextView flightCodesTextView = findViewById(R.id.flightCodes);
        final TextView flightTimeCodesTextView = findViewById(R.id.flightTimeCodes);
        final TextView flightAirlineTextView = findViewById(R.id.flightAirline);
        final TextView flightLayoversTextView = findViewById(R.id.flightLayovers);
        final TextView flightPriceTextView = findViewById(R.id.flightPrice);

        final TextView hotelNameTextView = findViewById(R.id.hotelName);
        final TextView hotelScoreTextView = findViewById(R.id.hotelScore);
        final TextView hotelPriceTextView = findViewById(R.id.hotelPrice);
        final TextView hotelAddressTextView = findViewById(R.id.hotelAddress);

        //instantiate map
        final MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // TODO: Get the trip info and update detail window
        address = "McMaster University";//PLACEHOLDER

        //pull address of hotel from textView
        hotelAddressTextView.setText(address);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng position = getLatLong(address);
        googleMap.addMarker(new MarkerOptions().position(position));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 12.0f));
    }

    //Retrieves the latitude and longitude of a string address
    public LatLng getLatLong(String strAddress) {
        Geocoder coder = new Geocoder(this);
        LatLng p1 = null;
        List <Address> address;

        try {
            address = coder.getFromLocationName(strAddress, 1);
            if (address == null) return null;

            Address position = address.get(0);

            p1 = new LatLng(position.getLatitude(), position.getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.saveButton) {
            //SAVE THE THINGS
        }

        return super.onOptionsItemSelected(item);
    }
}
