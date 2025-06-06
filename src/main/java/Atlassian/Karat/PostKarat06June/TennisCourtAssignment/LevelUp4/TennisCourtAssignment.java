package PostKarat06June.TennisCourtAssignment.LevelUp4;

import java.util.Arrays;
import java.util.List;

public class TennisCourtAssignment {


    public static Integer minCourts(List<BookingRecord> bookings) {

        if (bookings == null || bookings.isEmpty()) return 0;

        int n = bookings.size();

        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {

            start[i] = bookings.get(i).startTime;
            end[i] = bookings.get(i).finishTime;
        }


        int usedCourts = 0;
        int maxCourts = 0;
        int i = 0;
        int j = 0;

        while (i < n) {


            if (start[i] < end[j]) {

                usedCourts++;
                maxCourts = Math.max(maxCourts, usedCourts);
                i++;
            } else {

                usedCourts--;
                j++;
            }
        }


        return maxCourts;
    }


    public static void main(String[] args) {


        List<BookingRecord> bookings = Arrays.asList(
                new BookingRecord(1, 4),
                new BookingRecord(2, 5),
                new BookingRecord(6, 8),
                new BookingRecord(7, 9),
                new BookingRecord(10, 12)
        );

        int result = minCourts(bookings);
        System.out.println("Minimum courts required: " + result);
    }
}
