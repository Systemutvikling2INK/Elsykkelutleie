package no.ntnu.elsykkelutleie;

import java.util.Date;
import java.util.Random;

/**
 * Created by Knut on 15.09.2016.
 */
public class Bike {

    private int id;
    private double batteryPercentage;
    private boolean inUse = false; //Is set to true if the user booked a bike, and within 30minutes claimed the it.
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

    public boolean getInUse() {
        return inUse;
    }

    public void setInUse(boolean use) {
        inUse = use;
    }

    public void reserveBike() {
        Date reservationDate = new Date();
        booking = new Booking(reservationDate, generateRandomCode());
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