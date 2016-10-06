package no.ntnu.elsykkelutleie;

import java.util.Date;

/**
 * Created by Knut on 21.09.2016.
 */
public class Booking {

    private Date bookingStart;
    private String code;

    public Booking() {

    }

    public Booking(Date bookingStart, String code) {
        this.bookingStart = bookingStart;
        this.code = code;
    }

    public Date getBookingStart() {
        return bookingStart;
    }

    public void setBookingStart(Date booking) {
        bookingStart = booking;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String newCode) {
        code = newCode;
    }
}
