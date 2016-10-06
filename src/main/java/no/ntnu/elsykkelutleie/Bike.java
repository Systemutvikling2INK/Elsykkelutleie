package no.ntnu.elsykkelutleie;

import java.util.Date;
import java.util.Random;

/**
 * Created by Knut on 15.09.2016.
 */
public class Bike {

    private int id;
    private double batteryPercentage;
    private boolean inUse; //Is set to true if the user booked a bike, and within 30minutes claimed the it.
    private Booking booking;

    public Bike() {

    }

    public Bike(int id, double batteryPercentage) {
        this.id = id;
        this.batteryPercentage = batteryPercentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(double batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public boolean isInUse() {
        return inUse;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setInUse(boolean use) {
        inUse = use;
    }

    public String getReservationCode() {
        return booking.getCode();
    }

    public boolean getInUse() {
        if (booking == null && !inUse) {
            return false;
        } else {
            return true;
        }
    }

    public boolean reserveBike() {
        if (booking == null) {
            Date reservationDate = new Date();
            booking = new Booking(reservationDate, generateRandomCode());
            return true;
        } else {
            return false;
        }
    }

    public boolean receiveBike(String bikeCode) {//TODO: legge inn at man må være innenfor 30min etter man har leid sykkelen
        if (bikeCode.equals(getReservationCode())) {
            inUse = true;
            return true;
        } else {
            return false;
        }
    }

    public void deliverBackBike() {
        if (inUse) {
            inUse = false;
            booking = null;
        }
    }

    private final String[] chars = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C",
            "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    //Uten 0 og O fordi dem kan lett forveksles

    private String generateRandomCode() {
        Random random = new Random();
        String code = "";
        for(int i = 0; i < 5; i++) {
            code += chars[random.nextInt(chars.length-1)];
        }
        return code;
    }
}