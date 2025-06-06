package PostKarat06June.TennisCourtAssignment.LevelUp3;

import java.util.*;

public class TennisCourtAssignment {



    static class CourtUsage
    {

        int availableTime;
        int usageCount;
        Court court;

        public CourtUsage(int availableTime, int usageCount, Court court) {
            this.availableTime = availableTime;
            this.usageCount = usageCount;
            this.court = court;
        }
    }


    public static List<Court> assignCourts(List<BookingRecord> bookings, int maintenanceTime, int durability)
    {


        bookings.sort(Comparator.comparingInt(x->x.startTime));
        PriorityQueue<CourtUsage> pq = new PriorityQueue<>(Comparator.comparingInt(c->c.availableTime));
        List<Court> finalCourtList = new ArrayList<>();
        int courtCounter=0;

        for(BookingRecord bookingRecord : bookings)
        {

            boolean assigned = false;

            List<CourtUsage> temp = new ArrayList<>();

            while (!pq.isEmpty())
            {

                CourtUsage cu = pq.poll();

                if(cu.availableTime<=bookingRecord.startTime)
                {


                    cu.court.bookings.add(bookingRecord);
                    cu.usageCount++;

                    if(cu.usageCount==durability)
                    {

                        cu.availableTime = bookingRecord.finishTime + maintenanceTime;
                        cu.usageCount = 0;
                    }
                    else {
                        cu.availableTime = bookingRecord.finishTime;

                    }

                    pq.offer(cu);
                    assigned = true;
                    break;

                }
                else {

                    temp.add(cu);
                }
            }

            pq.addAll(temp);

            if(!assigned)
            {

                Court court = new Court(++courtCounter);
                court.bookings.add(bookingRecord);
                finalCourtList.add(court);
                pq.offer(new CourtUsage(bookingRecord.finishTime, 1, court));
            }
        }

        return finalCourtList;
    }

    public static void main(String[] args) {


        List<BookingRecord> bookings = Arrays.asList(
                new BookingRecord(1, 1, 4),
                new BookingRecord(2, 5, 6),
                new BookingRecord(3, 7, 8),
                new BookingRecord(4, 9, 10),
                new BookingRecord(5, 11, 12),
                new BookingRecord(6, 13, 15),
                new BookingRecord(7, 17, 18)
        );

        int durability = 2;        // Court can be used for 2 bookings
        int maintenanceTime = 2;   // Then needs 2 units of rest

        List<Court> result = assignCourts(
                bookings, maintenanceTime, durability);

        System.out.println("Court Assignment:");
        for (Court court : result) {
            System.out.println(court);
        }

    }
}
