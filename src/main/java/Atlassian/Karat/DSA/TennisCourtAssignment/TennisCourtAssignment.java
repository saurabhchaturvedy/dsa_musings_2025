package DSA.TennisCourtAssignment;

import java.util.*;

public class TennisCourtAssignment {


    static class CourtEndTime {

        Integer finishTime;
        Court court;

        public CourtEndTime(Integer finishTime, Court court) {
            this.finishTime = finishTime;
            this.court = court;
        }
    }


    public List<Court> assignCourts(List<BookingRecord> bookings) {

        List<Court> courtResult = new ArrayList<>();

        bookings.sort(Comparator.comparingInt(x -> x.startTime));
        PriorityQueue<CourtEndTime> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.finishTime));

        int courtIdCounter = 0;


        for (BookingRecord booking : bookings) {


            if (!pq.isEmpty() && pq.peek().finishTime <= booking.getStartTime()) {


                CourtEndTime availableCourt = pq.poll();
                availableCourt.court.addBooking(booking);
                pq.offer(new CourtEndTime(booking.getFinishTime(), availableCourt.court));


            } else {
                Court newCourt = new Court(++courtIdCounter);
                newCourt.bookings.add(booking);
                courtResult.add(newCourt);
                pq.offer(new CourtEndTime(booking.getFinishTime(), newCourt));


            }
        }

        return courtResult;
    }


    public static void main(String[] args) {


        TennisCourtAssignment tennisCourtAssignment = new TennisCourtAssignment();

        List<BookingRecord> bookings = Arrays.asList(
                new BookingRecord(1, 1, 4),
                new BookingRecord(2, 2, 5),
                new BookingRecord(3, 6, 8),
                new BookingRecord(4, 3, 7),
                new BookingRecord(5, 9, 10)
        );

        List<Court> result = tennisCourtAssignment.assignCourts(bookings);

        for (Court court : result) {
            System.out.println("Court " + court.id + ":");
            for (BookingRecord b : court.bookings) {
                System.out.println("  Booking ID " + b.id + " (" + b.startTime + " - " + b.finishTime + ")");
            }
        }


    }
}
