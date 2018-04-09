package com.utravel;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;


/**
 * This activity will display the returned trip of the search using the user's inputs.
 */
public class Details extends AppCompatActivity implements OnMapReadyCallback{

    //TODO: instantiate variables to hold TextView items?
    private String address;
    private String destinationCode;
    private Trip currentTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        //make back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get found trip
        currentTrip = (Trip)getIntent().getSerializableExtra("trip");

        //instantiate info textViews
        final TextView locationTextView = findViewById(R.id.tripLocation);
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
        address = currentTrip.getHotelAddress();
        destinationCode = currentTrip.getFlightCode().substring(6);

        //pull values from found trip, send to UI
        hotelAddressTextView.setText(address);
        flightCodesTextView.setText(currentTrip.getFlightCode());
        flightTimeCodesTextView.setText(currentTrip.getFlightTimeCode());
        flightAirlineTextView.setText(currentTrip.getFlightAirline());
        flightLayoversTextView.setText(currentTrip.getFlightLayovers());
        flightPriceTextView.setText(currentTrip.getFlightPrice());
        hotelNameTextView.setText(currentTrip.getHotelName());
        hotelScoreTextView.setText(currentTrip.getHotelScore());
        hotelPriceTextView.setText(currentTrip.getHotelPrice());

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng hotelPosition = getLatLong(address);
        LatLng flightPosition = getLatLong(destinationCode);
        googleMap.addMarker(new MarkerOptions().position(hotelPosition));
        googleMap.addMarker(new MarkerOptions().position(flightPosition));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(hotelPosition));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hotelPosition, 12.0f));
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
            ObjectOutput out;
            try {
                File outFile = new File(Environment.getExternalStorageDirectory() + File.separator + "uTravel", address + ".trip");
                out = new ObjectOutputStream(new FileOutputStream(outFile));
                out.writeObject(currentTrip);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
