package Post14June.Design.RateLimiter.Threshold;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {


    int maxRequests;
    Map<String, Integer> userToRequestCountMap;


    public RateLimiter(int maxRequests) {
        this.maxRequests = maxRequests;
        this.userToRequestCountMap = new HashMap<>();
    }


    public void resetLimit(String userId) {


        userToRequestCountMap.put(userId, 0);
        System.out.println("user limit reset :: ");

    }


    public boolean allowRequest(String userId) {


        if (userToRequestCountMap.getOrDefault(userId, 0) < maxRequests) {

            userToRequestCountMap.put(userId, userToRequestCountMap.getOrDefault(userId, 0) + 1);
            return true;
        }

        return false;
    }


    public static void main(String[] args) {


        RateLimiter rateLimiter = new RateLimiter(5);

        for (int i = 0; i < 10; i++) {

            boolean allowed = rateLimiter.allowRequest("user123");
            System.out.println(" Request : " + (i + 1) + " Allowed : " + allowed);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        rateLimiter.resetLimit("user123");


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
