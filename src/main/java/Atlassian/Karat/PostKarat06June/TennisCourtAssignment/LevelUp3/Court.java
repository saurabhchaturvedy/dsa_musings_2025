package PostKarat06June.TennisCourtAssignment.LevelUp3;

import java.util.ArrayList;
import java.util.List;

public class Court {


    int id;
    List<BookingRecord> bookings = new ArrayList<>();

    public Court(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BookingRecord> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingRecord> bookings) {
        this.bookings = bookings;
    }


    @Override
    public String toString() {
        return "Court{" +
                "id=" + id +
                ", bookings=" + bookings +
                '}';
    }
}
