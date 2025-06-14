package Post14June.Design.RateLimiter;

import java.util.*;

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


        while (!timestamps.isEmpty() && currentTime - timestamps.peek() > windowSizeInMs) {

            timestamps.poll();
        }

        if (timestamps.size() < maxRequests) {

            timestamps.add(currentTime);
            userTimeStampLog.put(userId, timestamps);
            return true;
        } else {

            return false;
        }
    }


    public static void main(String[] args) {


        RateLimiter rateLimiter = new RateLimiter(5, 10);


        for (int i = 0; i < 10; i++) {


            boolean isAllowed = rateLimiter.allowRequest("User_123");
            System.out.println(" Request : " + (i + 1) + " is Allowed ? : " + isAllowed);
        }
    }

}
