package DSA.TennisCourtAssignment.LevelUp1;

import java.util.*;

public class TennisCourtAssignment {


    static class CourtEndTime {

        Integer availableTime;
        Integer usageCount;
        Court court;

        public CourtEndTime(Integer availableTime, Court court, Integer usageCount) {
            this.availableTime = availableTime;
            this.court = court;
            this.usageCount = usageCount;
        }
    }


    public List<Court> assignCourts(List<BookingRecord> bookings, Integer maxUsage, Integer mainTime) {


        List<Court> courtResult = new ArrayList<>();

        bookings.sort(Comparator.comparingInt(x -> x.startTime));
        PriorityQueue<CourtEndTime> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.availableTime));
        Integer courtIdCounter = 0;

        for (BookingRecord bookingRecord : bookings) {


            boolean assigned = false;
            List<CourtEndTime> temp = new ArrayList<>();


            while (!pq.isEmpty()) {

                CourtEndTime availableCourt = pq.poll();


                if (availableCourt.availableTime <= bookingRecord.getStartTime()) {


                    availableCourt.court.bookings.add(bookingRecord);
                    availableCourt.usageCount++;

                    if (availableCourt.usageCount == maxUsage) {

                        availableCourt.availableTime = bookingRecord.finishTime + mainTime;
                        availableCourt.usageCount = 0;
                    } else {

                        availableCourt.availableTime = bookingRecord.finishTime;
                    }

                    pq.offer(availableCourt);
                    assigned = true;
                    break;

                } else {

                    temp.add(availableCourt);
                }

            }


            pq.addAll(temp);

            if (!assigned) {

                Court newCourt = new Court(++courtIdCounter);
                newCourt.bookings.add(bookingRecord);
                courtResult.add(newCourt);
                pq.offer(new CourtEndTime(bookingRecord.finishTime, newCourt, 1));
            }
        }

        return courtResult;
    }


    public static void main(String[] args) {


        TennisCourtAssignment tennisCourtAssignment = new TennisCourtAssignment();

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

        List<Court> courts = tennisCourtAssignment.assignCourts(bookings, maxUsage, maintenanceTime);

        System.out.println("Booking Plan:");
        for (Court court : courts) {
            System.out.println(court);
        }

    }
}
