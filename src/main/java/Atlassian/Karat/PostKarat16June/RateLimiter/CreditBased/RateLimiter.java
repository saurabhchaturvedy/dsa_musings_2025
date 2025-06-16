package PostKarat16June.RateLimiter.CreditBased;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {


    int maxRequests;
    int maxCredits;
    Map<String, Integer> userToRequestCountMap;
    Map<String, Integer> userToCreditCountMap;
    long lastResetTimeStamp;
    long windowSizeForRateLimitInMs;


    RateLimiter(int maxRequests, int maxCredits, long windowSizeForRateLimitInMs) {


        this.maxCredits = maxCredits;
        this.maxRequests = maxRequests;
        this.windowSizeForRateLimitInMs = windowSizeForRateLimitInMs * 1000;
        this.lastResetTimeStamp = System.currentTimeMillis();
        this.userToRequestCountMap = new HashMap<>();
        this.userToCreditCountMap = new HashMap<>();

    }


    public void initializeGlobalReset() {


        long currentTime = System.currentTimeMillis();

        if (currentTime > lastResetTimeStamp + windowSizeForRateLimitInMs) {

            for (Map.Entry<String, Integer> entry : userToRequestCountMap.entrySet()) {

                String user = entry.getKey();
                Integer used = entry.getValue();

                Integer unused = Math.max(0, maxRequests - used);

                Integer existingCredits = userToRequestCountMap.getOrDefault(user, 0);
                Integer newCredits = Math.min(maxCredits, unused + existingCredits);

                userToCreditCountMap.put(user, newCredits);

            }

            userToRequestCountMap.clear();
            this.lastResetTimeStamp = currentTime;
        }


    }


    public boolean allowRequest(String userId) {

        initializeGlobalReset();


        if (userToRequestCountMap.getOrDefault(userId, 0) < maxRequests) {

            userToRequestCountMap.put(userId, userToRequestCountMap.getOrDefault(userId, 0) + 1);
            return true;

        } else {


            if (userToCreditCountMap.getOrDefault(userId, 0) > 0) {

                userToCreditCountMap.put(userId, userToCreditCountMap.getOrDefault(userId, 0) - 1);
                return true;

            } else {

                return false;
            }
        }

    }


    public static void main(String[] args) {

        RateLimiter rateLimiter = new RateLimiter(5, 10, 10);

        System.out.println("==== Test 1: Make 5 requests within limit ====");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Request " + i + " allowed: " + rateLimiter.allowRequest("user123"));
        }

        System.out.println("\n==== Test 2: Bursty traffic (exceeding limit) ====");
        for (int i = 6; i <= 10; i++) {
            System.out.println("Request " + i + " allowed (should use credits): " + rateLimiter.allowRequest("user123"));
        }

        System.out.println("\n==== Test 3: Exceeding all credits too ====");
        System.out.println("Request 11 allowed: " + rateLimiter.allowRequest("user123")); // should be false

        System.out.println("\n==== Wait 10s to enter new window ====");
        try {
            Thread.sleep(10000); // Wait for reset window
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n==== Test 4: New window + unused credits added ====");
        for (int i = 12; i <= 18; i++) {
            System.out.println("Request " + i + " allowed: " + rateLimiter.allowRequest("user123"));
        }

        System.out.println("\n==== Test 5: Another request after limit & credit usage ====");
        System.out.println("Request 19 allowed: " + rateLimiter.allowRequest("user123"));

    }
}
