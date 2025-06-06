package PostKarat06June.TennisCourtAssignment;

import java.util.ArrayList;
import java.util.List;

public class Court {


    Integer courtId;
    List<BookingRecord> bookings;

    public Court(Integer courtId) {
        this.courtId = courtId;
        this.bookings = new ArrayList<>();
    }


    public void addBooking(BookingRecord booking) {

        this.bookings.add(booking);
    }

    public Integer getCourtId() {
        return courtId;
    }

    public List<BookingRecord> getBookings() {
        return bookings;
    }
}
