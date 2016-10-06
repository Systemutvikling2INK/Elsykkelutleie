package no.ntnu.elsykkelutleie;

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
}