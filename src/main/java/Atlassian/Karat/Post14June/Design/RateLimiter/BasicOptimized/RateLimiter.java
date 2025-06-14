package Post14June.Design.RateLimiter.BasicOptimized;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RateLimiter {


    int maxRequests;
    long windowSizeInMs;
    Map<String, Queue<Long>> userTimeStampLog;


    RateLimiter(int maxRequests, long windowSizeInMs) {

        this.maxRequests = maxRequests;
        this.windowSizeInMs = windowSizeInMs * 1000;
        this.userTimeStampLog = new HashMap<>();
    }


    public boolean allowRequest(String userId) {

        long currentTime = System.currentTimeMillis();

        Queue<Long> timestamps = userTimeStampLog.getOrDefault(userId, new LinkedList<>());


        if (timestamps.size() == maxRequests) {

            long oldestTimestamp = timestamps.peek();

            if (currentTime - oldestTimestamp >= windowSizeInMs) {

                timestamps.poll();
            } else {

                return true;
            }
        }



            timestamps.add(currentTime);
            userTimeStampLog.put(userId, timestamps);
            return true;

    }


    public static void main(String[] args) {


        RateLimiter rateLimiter = new RateLimiter(5, 10);


        for (int i = 0; i < 10; i++) {

            boolean allowed = rateLimiter.allowRequest("user123");
            System.out.println(" Request : " + (i + 1) + " Allowed : " + allowed);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
