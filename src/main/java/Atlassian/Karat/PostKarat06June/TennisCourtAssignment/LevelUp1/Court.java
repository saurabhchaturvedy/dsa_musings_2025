package PostKarat06June.TennisCourtAssignment.LevelUp1;

import java.util.ArrayList;
import java.util.List;

public class Court {


    int id;
    List<BookingRecord> bookings = new ArrayList<>();

    public Court(int id) {
        this.id = id;
    }
}
