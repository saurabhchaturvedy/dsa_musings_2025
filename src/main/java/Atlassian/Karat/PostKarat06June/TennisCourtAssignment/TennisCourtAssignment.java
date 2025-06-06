package PostKarat06June.TennisCourtAssignment;

import java.util.*;


// Time complexity O(nlogn)
// Space Complexity O(n)

public class TennisCourtAssignment {


    static class CourtEndTime {

        int finishTime;
        Court court;

        public CourtEndTime(int finishTime, Court court) {
            this.finishTime = finishTime;
            this.court = court;
        }
    }


    public static List<Court> assignCourts(List<BookingRecord> bookingRecordList) {


        bookingRecordList.sort(Comparator.comparingInt(x -> x.startTime));

        PriorityQueue<CourtEndTime> pq = new PriorityQueue<>(Comparator.comparing(c -> c.finishTime));

        List<Court> courtsBooked = new ArrayList<>();
        int courtCounter = 0;

        for (BookingRecord booking : bookingRecordList) {


            if (!pq.isEmpty() && pq.peek().finishTime <= booking.getStartTime()) {


                CourtEndTime availableCourt = pq.poll();
                if(availableCourt!=null) {
                    availableCourt.court.bookings.add(booking);
                    pq.add(new CourtEndTime(booking.getFinishTime(), availableCourt.court));
                }

            } else {

                Court newCourt = new Court(++courtCounter);
                newCourt.bookings.add(booking);
                courtsBooked.add(newCourt);
                pq.add(new CourtEndTime(booking.getFinishTime(), newCourt));
            }
        }
        return courtsBooked;
    }


    public static void main(String[] args) {




        List<BookingRecord> bookings = Arrays.asList(
                new BookingRecord(1, 1, 4),
                new BookingRecord(2, 2, 5),
                new BookingRecord(3, 6, 8),
                new BookingRecord(4, 3, 7),
                new BookingRecord(5, 9, 10)
        );

        List<Court> result = assignCourts(bookings);

        for (Court court : result) {
            System.out.println("Court " + court.courtId + ":");
            for (BookingRecord b : court.bookings) {
                System.out.println("  Booking ID " + b.id + " (" + b.startTime + " - " + b.finishTime + ")");
            }
        }
    }
}
