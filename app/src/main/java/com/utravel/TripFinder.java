package com.utravel;

import android.content.Context;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.List;

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
            List flightList = flightReader.readAll();
            List hotelList = hotelReader.readAll();

            String[] bestFlight = null;
            String[] bestHotel = null;
            int bestCostDiff = Integer.MAX_VALUE;
            for (int i = 0; i < flightList.size(); i++) {
                String[] flightLine = (String[])flightList.get(i);
                String[] hotelLine = (String[])hotelList.get(i);

                Log.d("HOTELCONTENTS:", flightLine[0] + " " + hotelLine[0]);

                int cost = Integer.parseInt(flightLine[4].substring(1)) + Integer.parseInt(hotelLine[2].substring(1)) * Integer.parseInt(tripDuration);
                int budget = Integer.parseInt(this.budget);
                if (budget-cost < bestCostDiff && budget-cost >= 0) {
                    bestFlight = flightLine;
                    bestHotel = hotelLine;
                    bestCostDiff = budget-cost;
                }
            }

            if (bestFlight == null) {return null;}

            foundTrip = new Trip(bestFlight[0], bestFlight[1], bestFlight[2], bestFlight[3], bestFlight[4], bestHotel[0], bestHotel[1], bestHotel[2], bestHotel[3], bestHotel[4]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return foundTrip;
    }
}
