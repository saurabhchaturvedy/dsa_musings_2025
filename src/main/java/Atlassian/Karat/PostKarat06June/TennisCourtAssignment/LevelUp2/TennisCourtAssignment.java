package PostKarat06June.TennisCourtAssignment.LevelUp2;

import java.util.*;

public class TennisCourtAssignment {


    static class CourtEndTime {

        int availableTime;
        int usageCount;
        Court court;

        public CourtEndTime(int availableTime, int usageCount, Court court) {
            this.availableTime = availableTime;
            this.usageCount = usageCount;
            this.court = court;
        }
    }


    public static List<Court> assignCourts(List<BookingRecord> bookings, int maxUsage, int fixMaintainenceTime) {


        bookings.sort(Comparator.comparingInt(x -> x.getStartTime()));
        PriorityQueue<CourtEndTime> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c.availableTime));
        List<Court> bookedCourtsList = new ArrayList<>();
        int courtCounter = 0;

        for (BookingRecord booking : bookings) {

            boolean assigned = false;
            List<CourtEndTime> temp = new ArrayList<>();

            while (!pq.isEmpty()) {

                CourtEndTime courtEndTime = pq.poll();

                if (courtEndTime.availableTime <= booking.getStartTime()) {

                    courtEndTime.court.bookings.add(booking);
                    courtEndTime.usageCount++;

                    if (courtEndTime.usageCount == maxUsage) {

                        courtEndTime.availableTime = booking.endTime + fixMaintainenceTime;
                        courtEndTime.usageCount = 0;
                    } else {


                        courtEndTime.availableTime = booking.endTime;
                    }


                    pq.offer(courtEndTime);
                    assigned = true;
                    break;
                } else {

                    temp.add(courtEndTime);
                }
            }


            pq.addAll(temp);

            if (!assigned) {

                Court court = new Court(++courtCounter);
                court.bookings.add(booking);
                bookedCourtsList.add(court);
                pq.offer(new CourtEndTime(booking.endTime, 1, court));
            }
        }

        return bookedCourtsList;
    }


    public static void main(String[] args) {


        List<BookingRecord> bookings = Arrays.asList(
                new BookingRecord(1, 1, 4),
                new BookingRecord(2, 5, 6),
                new BookingRecord(3, 7, 8),
                new BookingRecord(4, 9, 10),
                new BookingRecord(5, 11, 13),
                new BookingRecord(6, 14, 15)
        );

        int maxUsage = 2;         // Court needs maintenance after 2 bookings
        int maintenanceTime = 2;  // Maintenance takes 2 units

        List<Court> courts = assignCourts(bookings, maxUsage, maintenanceTime);

        System.out.println("Booking Plan:");
        for (Court court : courts) {
            System.out.println(court);
        }


    }
}
