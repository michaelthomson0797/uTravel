package com.utravel;

import android.content.Context;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.InputStreamReader;

public class TripFinder {
    Context context;
    private String departureLocation;
    private String departureDate;
    private String tripDuration;
    private String budget;
    private String tripStyle;
    private String distance;

    private Trip foundTrip;

    public TripFinder(Context context, String departureLocation, String departureDate, String tripDuration,
                      String budget, String tripStyle, String distance) {
        this.context = context;
        this.departureLocation = departureLocation;
        this.departureDate = departureDate;
        this.tripDuration = tripDuration;
        this.budget = budget;
        this.tripStyle = tripStyle;
        this.distance = distance;
    }

    public Trip getTrip() {
        //for now, just pulls first entry from the test csv
        try {
            CSVReader flightReader = new CSVReader(new InputStreamReader(context.getResources().openRawResource(R.raw.flights)));
            CSVReader hotelReader = new CSVReader(new InputStreamReader(context.getResources().openRawResource(R.raw.hotels)));
            String[] flightLine = flightReader.readNext();
            String[] hotelLine = hotelReader.readNext();
            foundTrip = new Trip(flightLine[0], flightLine[1], flightLine[2], flightLine[3], flightLine[4], hotelLine[0], hotelLine[1], hotelLine[2], hotelLine[3]);
        } catch (Exception e) {
            Log.d("FUUUUUUUUUUUUUUUUUCK", "PLEEEEEEEEEEEEEASE");
            e.printStackTrace();
        }

        return foundTrip;
    }
}
