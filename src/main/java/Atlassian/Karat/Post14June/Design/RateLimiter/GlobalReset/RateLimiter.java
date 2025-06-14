package Post14June.Design.RateLimiter.GlobalReset;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {


    int maxRequests;
    Map<String, Integer> userToRequestCountMap;
    long windowSizeInMs;
    Long lastResetTime;


    RateLimiter(int maxRequests, long windowSizeInMs) {

        this.maxRequests = maxRequests;
        this.windowSizeInMs = windowSizeInMs * 1000;
        this.userToRequestCountMap = new HashMap<>();
        this.lastResetTime = System.currentTimeMillis();
    }


    public void globalReset() {

        long currentTime = System.currentTimeMillis();

        if (currentTime > lastResetTime + windowSizeInMs) {

            this.userToRequestCountMap = new HashMap<>();
            System.out.println(" Global Reset Done : ");
            this.lastResetTime = currentTime;
        }
    }


    public boolean allowRequest(String userId) {

        globalReset();


        if (userToRequestCountMap.getOrDefault(userId, 0) < maxRequests) {

            userToRequestCountMap.put(userId, userToRequestCountMap.getOrDefault(userId, 0) + 1);
            return true;
        } else {

            return false;
        }


    }


    public static void main(String[] args) {


        RateLimiter rateLimiter = new RateLimiter(5, 10);

        for (int i = 0; i < 20; i++) {


            boolean allowed = rateLimiter.allowRequest("123");
            System.out.println(" Request " + (i + 1) + " is allowed : " + allowed);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

}
