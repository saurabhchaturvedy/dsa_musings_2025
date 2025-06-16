package PostKarat16June.RateLimiter.GlobalReset;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {


    int maxRequests;
    long windowSizeInMs;
    Map<String, Integer> userToRequestCount;
    long lastRestTimeStamp;


    RateLimiter(int maxRequests, long windowSizeInMs) {

        this.maxRequests = maxRequests;
        this.windowSizeInMs = windowSizeInMs * 1000;
        this.userToRequestCount = new HashMap<>();
        this.lastRestTimeStamp = System.currentTimeMillis();
    }


    public boolean allowRequest(String userId) {

        long currentTime = System.currentTimeMillis();

        if (currentTime > lastRestTimeStamp + windowSizeInMs) {

            this.userToRequestCount = new HashMap<>();
            this.lastRestTimeStamp = currentTime;
        }


        if (userToRequestCount.getOrDefault(userId, 0) < maxRequests) {

            userToRequestCount.put(userId, userToRequestCount.getOrDefault(userId, 0) + 1);
            return true;
        }

        return false;

    }


    public static void main(String[] args) {


        RateLimiter rateLimiter = new RateLimiter(5, 10);


        for (int i = 0; i < 20; i++) {

            boolean allowRequest = rateLimiter.allowRequest("user123");
            System.out.println(" Request : " + (i + 1) + " Allow Request : " + allowRequest);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
