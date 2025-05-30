package KaratFriday.Intervals;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms2 {


    public static Integer minimumRooms(int[][] intervals) {

        if (intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] interval : intervals) {


            while (!minHeap.isEmpty() && minHeap.peek() <= interval[0]) {

                minHeap.poll();
            }

            minHeap.add(interval[1]);
        }

        return minHeap.size();
    }


    public static void main(String[] args) {


        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};

        Integer rooms = minimumRooms(intervals);

        System.out.println(" minimum rooms required are : " + rooms);
    }
}


