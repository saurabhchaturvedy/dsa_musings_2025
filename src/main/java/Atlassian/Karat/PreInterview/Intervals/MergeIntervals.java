package PreInterview.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {



    public static int[][] mergeIntervals(int[][]intervals)
    {


        if(intervals.length<=1) return intervals;

        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));

        List<int[]> merged = new ArrayList<>();

        int[]current = intervals[0];

        for(int i=1; i<intervals.length; i++)
        {

            int[]next = intervals[i];

            if(current[1] >= next[0])
            {

                current[1] = Math.max(current[1],next[1]);
            }
            else {


                merged.add(current);
                current = next;
            }
        }

        merged.add(current);
       return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {


        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        MergeIntervals mi = new MergeIntervals();
        int[][] result = mi.mergeIntervals(intervals);
        for (int[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
