package DSA.TennisCourtAssignment.LevelUp1;

import java.util.ArrayList;
import java.util.List;

public class Court {


    Integer id;
    List<BookingRecord> bookings;


    public Court(Integer id) {
        this.bookings = new ArrayList<>();
    }


    public void addBooking(BookingRecord booking) {

        this.bookings.add(booking);
    }

    public Integer getId() {
        return id;
    }

    public List<BookingRecord> getBookings() {
        return bookings;
    }
}
