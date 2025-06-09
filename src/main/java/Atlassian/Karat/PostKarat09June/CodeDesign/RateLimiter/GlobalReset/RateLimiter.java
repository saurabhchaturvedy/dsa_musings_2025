package PostKarat09June.CodeDesign.RateLimiter.GlobalReset;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {


    int maxRequests;
    long windowSizeForRateLimitInMs;
    Map<String, Integer> userToRequestCountMap;
    long lastResetTime;


    RateLimiter(int maxRequests, long windowSizeForRateLimitInMs) {

        this.maxRequests = maxRequests;
        this.windowSizeForRateLimitInMs = windowSizeForRateLimitInMs * 1000;
        this.userToRequestCountMap = new HashMap<>();
        this.lastResetTime = System.currentTimeMillis();
    }


    public void initializeGlobalReset() {

        long currentTime = System.currentTimeMillis();

        if (currentTime > lastResetTime + windowSizeForRateLimitInMs) {

            this.userToRequestCountMap = new HashMap<>();
            System.out.println(" global reset DONE !!!");
            this.lastResetTime = currentTime;
        }
    }


    public boolean rateLimit(String userId) {

        initializeGlobalReset();

        int requestCount = userToRequestCountMap.getOrDefault(userId, 0);

        if (requestCount < maxRequests) {

            userToRequestCountMap.put(userId, userToRequestCountMap.getOrDefault(userId, 0) + 1);
            return true;
        } else {

            return false;
        }
    }

    public static void main(String[] args) {



        RateLimiter rateLimiter = new RateLimiter(5, 10);

        for (int i = 0; i < 20; i++) {


            boolean allowed = rateLimiter.rateLimit("123");
            System.out.println(" Request " + (i + 1) + " is allowed : " + allowed);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    }

