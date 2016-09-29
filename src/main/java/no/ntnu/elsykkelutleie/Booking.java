package no.ntnu.elsykkelutleie;

import java.util.Date;

/**
 * Created by Knut on 21.09.2016.
 */
public class Booking {

    private Date startBooking;
    private Date delivery;

    public Booking() {

    }

    public Date getStartBooking() {
        return startBooking;
    }

    public void setStartBooking(Date booking) {
        startBooking = booking;
    }

    public Date getDelivery() {
        return delivery;
    }

    public void setDelivery(Date delivery) {
        this.delivery = delivery;
    }
}
