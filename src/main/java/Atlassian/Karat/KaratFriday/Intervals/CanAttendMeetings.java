package KaratFriday.Intervals;

import java.util.Arrays;

public class CanAttendMeetings {


    public static boolean canAttendMeetings(int[][] intervals) {

        if (intervals == null || intervals.length == 0) return false;


        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 1; i < intervals.length; i++) {


            if (intervals[i][0] < intervals[i - 1][1]) {

                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {


        int[][] intervals = {
                {0, 30},
                {5, 10},
                {15, 20}
        };

        CanAttendMeetings mr = new CanAttendMeetings();
        System.out.println(mr.canAttendMeetings(intervals));
    }
}
