package PreInterview.Intervals;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {


    public static int[][] insertInterval(int[][] intervals, int[] newInterval) {


        List<int[]> result = new ArrayList<>();

        int i = 0;
        int n = intervals.length;


        while (i < n && intervals[i][1] < newInterval[0]) {

            result.add(intervals[i]);
            i++;
        }


        while (i < n && intervals[i][0] <= newInterval[1]) {

            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }


        result.add(newInterval);

        while (i < n) {

            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }


    public static void printIntervals(int[][] intervals) {
        for (int[] interval : intervals) {
            System.out.print("[" + interval[0] + "," + interval[1] + "] ");
        }
        System.out.println();
    }


    public static void main(String[] args) {


        int[][] intervals = {
                {1, 3},
                {6, 9}
        };
        int[] newInterval = {2, 5};

        System.out.println("Original Intervals:");
        printIntervals(intervals);
        System.out.println("New Interval: [" + newInterval[0] + "," + newInterval[1] + "]");

        // Call the insert function
        int[][] merged = insertInterval(intervals, newInterval);

        System.out.println("Merged Intervals:");
        printIntervals(merged);
    }
}




