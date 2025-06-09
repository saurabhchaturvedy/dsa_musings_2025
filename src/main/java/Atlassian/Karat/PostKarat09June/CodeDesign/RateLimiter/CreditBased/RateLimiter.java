package PostKarat09June.CodeDesign.RateLimiter.CreditBased;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {


    int maxCredits;
    int maxRequests;
    long rateLimitingWindowSizeInMs;
    Map<String, Integer> userToRequestCountMap;
    Map<String, Integer> userToCreditMap;
    Map<String, Long> userToLastResetTimeStamp;


    RateLimiter(int maxRequests, int maxCredits, long rateLimitingWindowSizeInMs) {

        this.maxRequests = maxRequests;
        this.maxCredits = maxCredits;
        this.rateLimitingWindowSizeInMs = rateLimitingWindowSizeInMs * 1000;
        this.userToRequestCountMap = new HashMap<>();
        this.userToCreditMap = new HashMap<>();
        this.userToLastResetTimeStamp = new HashMap<>();
    }


    public boolean rateLimit(String userId) {


        long currentTime = System.currentTimeMillis();

        long lastResetTimeStamp = userToLastResetTimeStamp.getOrDefault(userId, 0L);

        if (currentTime > lastResetTimeStamp + rateLimitingWindowSizeInMs) {
            System.out.println(" Refreshing Credit and Request Limits ::::");

            int unusedRequests = Math.max(0, maxRequests - userToRequestCountMap.getOrDefault(userId, 0));

            System.out.println(" UnUsed Requests From Last Window : " + unusedRequests);

            int creditsLeft = userToCreditMap.getOrDefault(userId, 0);

            System.out.println(" Credits Still Available With User : " + creditsLeft);

            userToCreditMap.put(userId, Math.min(maxCredits, unusedRequests + creditsLeft));

            System.out.println("Newly Allocated Credits = " + userToCreditMap.get(userId));

            userToRequestCountMap.put(userId, 0);
            userToLastResetTimeStamp.put(userId, currentTime);

        }

        int requestCount = userToRequestCountMap.getOrDefault(userId, 0);

        if (requestCount < maxRequests) {

            System.out.println("Current Request Count = " + requestCount);
            userToRequestCountMap.put(userId, userToRequestCountMap.getOrDefault(userId, 0) + 1);

            return true;
        } else {

            System.out.println(" Request Quota Exhausted .... Utilizing Credits NOW");
            int creditsAvailable = userToCreditMap.getOrDefault(userId, 0);
            System.out.println("Available Credits are =" + creditsAvailable);

            if (creditsAvailable > 0) {
                System.out.println("Current Credit Count = " + requestCount);
                userToCreditMap.put(userId, userToCreditMap.getOrDefault(userId, 0) - 1);
                return true;
            } else {

                return false;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {


        RateLimiter rateLimiter = new RateLimiter(6, 3, 10);


        rateLimiter.rateLimit("123");
        Thread.sleep(1000);

        rateLimiter.rateLimit("123");
        Thread.sleep(1000);

        rateLimiter.rateLimit("123");
        Thread.sleep(1000);

        rateLimiter.rateLimit("123");
        Thread.sleep(1000);

        Thread.sleep(7000);

        rateLimiter.rateLimit("123");
        Thread.sleep(1000);

        rateLimiter.rateLimit("123");
        Thread.sleep(1000);
    }
}
