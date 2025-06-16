package PostKarat16June.RateLimiter.Threshold;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {


    int maxRequests;
    Map<String, Integer> userToRequestCount;


    public RateLimiter(int maxRequests) {
        this.maxRequests = maxRequests;
        this.userToRequestCount = new HashMap<>();
    }


    public boolean allowRequest(String userId) {


        if (userToRequestCount.getOrDefault(userId, 0) < maxRequests) {

            userToRequestCount.put(userId, userToRequestCount.getOrDefault(userId, 0) + 1);
            return true;
        }

        return false;
    }


    public void resetLimit(String userId) {

        this.userToRequestCount.put(userId, 0);
        System.out.println(" User Limit Reset ::::");
    }


    public static void main(String[] args) {


        RateLimiter rateLimiter = new RateLimiter(5);


        for (int i = 0; i < 10; i++) {

            boolean allowed = rateLimiter.allowRequest("123");
            System.out.println(" Request : " + (i + 1) + " is allowed : " + allowed);
        }

        rateLimiter.resetLimit("123");

        for (int i = 10; i < 20; i++) {

            boolean allowed = rateLimiter.allowRequest("123");
            System.out.println(" Request : " + (i + 1) + " is allowed : " + allowed);
        }
    }
}

