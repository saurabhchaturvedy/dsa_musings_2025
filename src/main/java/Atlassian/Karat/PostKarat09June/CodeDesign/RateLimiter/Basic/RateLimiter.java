package PostKarat09June.CodeDesign.RateLimiter.Basic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RateLimiter {


    int maxRequests;
    long windowSizeInMs;
    Map<String, Queue<Long>> userTimestampsLog;


    RateLimiter(int maxRequests, long windowSizeInMs) {

        this.maxRequests = maxRequests;
        this.windowSizeInMs = windowSizeInMs * 1000;
        this.userTimestampsLog = new HashMap<>();
    }


    public boolean rateLimit(String userId) {

        long currentTime = System.currentTimeMillis();

        Queue<Long> timeStamps = userTimestampsLog.getOrDefault(userId, new LinkedList<>());

        while (!timeStamps.isEmpty() && (currentTime - timeStamps.peek()) > windowSizeInMs) {

            timeStamps.poll();
        }

        if (timeStamps.size() < maxRequests) {

            timeStamps.add(currentTime);
            userTimestampsLog.put(userId, timeStamps);
            return true;
        } else {

            return false;
        }
    }


    public static void main(String[] args) {


        RateLimiter rateLimiter = new RateLimiter(5, 10);


        for (int i = 0; i < 10; i++) {

            boolean allowed = rateLimiter.rateLimit("user123");
            System.out.println(" Request : " + (i + 1) + " Allowed : " + allowed);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
