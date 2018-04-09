package com.utravel;


import java.io.Serializable;

public class Trip implements Serializable{
    private  String location;

    //values for the flight
    private String flightCode;
    private String flightTimeCode;
    private String flightAirline;
    private String flightLayovers;
    private String flightPrice;

    //values for hotel
    private String hotelName;
    private String hotelScore;
    private String hotelPrice;
    private String hotelAddress;

    public Trip(String flightCode, String flightTimeCode, String flightAirline,
                String flightLayovers, String flightPrice, String hotelName, String hotelScore,
                String hotelPrice, String hotelAddress, String location) {
        this.flightCode = flightCode;
        this.flightTimeCode = flightTimeCode;
        this.flightAirline = flightAirline;
        this.flightLayovers = flightLayovers;
        this.flightPrice = flightPrice;
        this.hotelName = hotelName;
        this.hotelScore = hotelScore;
        this.hotelPrice = hotelPrice;
        this.hotelAddress = hotelAddress;
        this.location = location;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public String getFlightTimeCode() {
        return flightTimeCode;
    }

    public String getFlightAirline() {
        return flightAirline;
    }

    public String getFlightLayovers() {
        return flightLayovers;
    }

    public String getFlightPrice() {
        return flightPrice;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelScore() {
        return hotelScore;
    }

    public String getHotelPrice() {
        return hotelPrice;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public String getLocation() {
        return location;
    }
}
