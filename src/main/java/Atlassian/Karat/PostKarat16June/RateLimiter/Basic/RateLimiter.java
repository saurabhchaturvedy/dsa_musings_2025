package PostKarat16June.RateLimiter.Basic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RateLimiter {


    int maxRequests;
    long windowSizeInMs;
    Map<String, Queue<Long>> userTimeStampLog;


    public RateLimiter(int maxRequests, long windowSizeInMs) {
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
        }

        return false;
    }


    public static void main(String[] args) {


        RateLimiter rateLimiter = new RateLimiter(5, 10);

        for (int i = 0; i < 10; i++) {

            boolean isAllowed = rateLimiter.allowRequest("user123");
            System.out.println(" Request : " + (i + 1) + " is Allowed ? " + isAllowed);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
