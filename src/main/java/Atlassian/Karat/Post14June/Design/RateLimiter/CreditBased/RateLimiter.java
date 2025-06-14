package Post14June.Design.RateLimiter.CreditBased;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {


    int maxRequests;
    int maxCredits;
    long windowSizeInMs;
    Map<String, Integer> userToRequestCountMap;
    Map<String, Integer> userToCreditBalanceMap;
    Map<String, Long> userToLastResetTimeStamp;


    RateLimiter(int maxRequests, int maxCredits, int windowSizeInMs) {

        this.maxCredits = maxCredits;
        this.maxRequests = maxRequests;
        this.windowSizeInMs = windowSizeInMs * 1000;
        this.userToRequestCountMap = new HashMap<>();
        this.userToCreditBalanceMap = new HashMap<>();
        this.userToLastResetTimeStamp = new HashMap<>();
    }


    public boolean allowRequest(String userId) {

        long currentTime = System.currentTimeMillis();

        long lastResetTime = userToLastResetTimeStamp.getOrDefault(userId, 0L);

        if (currentTime > lastResetTime + windowSizeInMs) {

            Integer remainingRequests = Math.max(0, maxCredits - userToRequestCountMap.getOrDefault(userId, 0));
            Integer currentCredits = userToCreditBalanceMap.getOrDefault(userId, 0);

            userToCreditBalanceMap.put(userId, Math.min(maxCredits, remainingRequests + currentCredits));


            userToRequestCountMap.put(userId, 0);
            userToLastResetTimeStamp.put(userId, currentTime);

        }

        int currentRequest = userToRequestCountMap.getOrDefault(userId, 0);

        if (currentRequest < maxRequests) {

            System.out.println("Current Request Count = " + currentRequest);
            userToRequestCountMap.put(userId, userToRequestCountMap.getOrDefault(userId, 0) + 1);

            return true;
        } else {

            System.out.println(" Request Quota Exhausted .... Utilizing Credits NOW");
            int creditsAvailable = userToCreditBalanceMap.getOrDefault(userId, 0);
            System.out.println("Available Credits are =" + creditsAvailable);

            if (creditsAvailable > 0) {
                System.out.println("Current Credit Count = " + creditsAvailable);
                userToCreditBalanceMap.put(userId, userToCreditBalanceMap.getOrDefault(userId, 0) - 1);
                return true;
            } else {

                return false;
            }
        }


    }

    public static void main(String[] args) throws InterruptedException {



        RateLimiter rateLimiter = new RateLimiter(6, 3, 10);


        rateLimiter.allowRequest("123");
        Thread.sleep(1000);

        rateLimiter.allowRequest("123");
        Thread.sleep(1000);

        rateLimiter.allowRequest("123");
        Thread.sleep(1000);

        rateLimiter.allowRequest("123");
        Thread.sleep(1000);

        Thread.sleep(7000);

        rateLimiter.allowRequest("123");
        Thread.sleep(1000);

        rateLimiter.allowRequest("123");
        Thread.sleep(1000);



    }
}
