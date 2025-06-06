package PostKarat06June.TennisCourtAssignment.LevelUp1;

import java.util.*;

public class TennisCourtAssignment {


    static class CourtEndTime {

        int availableTime;
        Court court;

        public CourtEndTime(int availableTime, Court court) {
            this.availableTime = availableTime;
            this.court = court;
        }
    }

    public static List<Court> assignCourts(List<BookingRecord> bookingRecordList, int fixedMaintenanceTime) {


        bookingRecordList.sort(Comparator.comparingInt(x -> x.getStartTime()));

        PriorityQueue<CourtEndTime> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c.availableTime));
        List<Court> totalBookedCourts = new ArrayList<>();
        int courtCounter = 0;

        for (BookingRecord booking : bookingRecordList) {

            if (!pq.isEmpty() && pq.peek().availableTime <= booking.getStartTime()) {

                CourtEndTime availableCourt = pq.poll();
                if(availableCourt!=null) {
                    availableCourt.court.bookings.add(booking);
                    pq.offer(new CourtEndTime(booking.getFinishTime() + fixedMaintenanceTime, availableCourt.court));
                }

            } else {

                Court newCourt = new Court(++courtCounter);
                newCourt.bookings.add(booking);
                totalBookedCourts.add(newCourt);
                pq.offer(new CourtEndTime(booking.getFinishTime() + fixedMaintenanceTime, newCourt));
            }
        }

        return totalBookedCourts;
    }

    public static void main(String[] args) {



        List<BookingRecord> bookings = Arrays.asList(
                new BookingRecord(1, 1, 4),
                new BookingRecord(2, 5, 7),
                new BookingRecord(3, 8, 9),
                new BookingRecord(4, 11, 13)
        );

        int maintenanceTime = 2; // X = 2

        List<Court> courts = assignCourts(bookings, maintenanceTime);

        for (Court court : courts) {
            System.out.println("Court " + court.id + ":");
            for (BookingRecord b : court.bookings) {
                System.out.println("  Booking ID " + b.id + " (" + b.startTime + " - " + b.finishTime + ")");
            }
        }
    }
}

