package PostKarat06June.TennisCourtAssignment.LevelUp5;

public class TennisCourtAssignment {


    public static boolean isConflict(BookingRecord b1, BookingRecord b2) {

        return b1.startTime < b2.endTime && b2.startTime < b1.endTime;
    }


    public static void main(String[] args) {


        BookingRecord b1 = new BookingRecord(2, 4);
        BookingRecord b2 = new BookingRecord(3, 7);

        boolean conflict = isConflict(b1, b2);

        System.out.println(" Is booking conflicted ? : " + conflict);
    }
}
