package PostKarat08June.CustomerSatisfaction.LevelUp2;

import java.util.*;

public class CustomerSupportSystem {


    static class Rating {

        int value;
        long timestamp;

        public Rating(int value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }


    Map<String, List<Rating>> agentRatingsMap = new HashMap<>();
    long globalTimestamp = 0;


    public void addRating(String agentName, Integer rating) {

        if (rating < 1 || rating > 5) {

            throw new IllegalArgumentException(" Rating should be between 1 and 5");
        }


        agentRatingsMap.computeIfAbsent(agentName, k -> new ArrayList<>()).add(new Rating(rating, ++globalTimestamp));
    }


    public double getAverage(List<Rating> ratings) {

        return ratings.stream().mapToInt(rating -> rating.value).average().getAsDouble();
    }


    public Integer totalRatings(List<Rating> ratings) {

        return ratings.stream().mapToInt(rating -> rating.value).sum();
    }


    public long getFirstRatingTimeStamp(List<Rating> ratings) {

        return ratings.get(0).timestamp;
    }

    public Integer getMostRecentRating(List<Rating> ratings) {

        return ratings.get(ratings.size() - 1).value;
    }


    public List<String> getAverageRatingView(Comparator<Map.Entry<String, List<Rating>>> comparator) {

        List<Map.Entry<String, List<Rating>>> entries = new ArrayList<>(agentRatingsMap.entrySet());
        entries.sort(comparator);

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, List<Rating>> entry : entries) {

            double average = getAverage(entry.getValue());

            result.add(entry.getKey() + " ->  average rating = " + average);

        }

        return result;
    }


    public Comparator<Map.Entry<String, List<Rating>>> avgThenName() {

        return Comparator.<Map.Entry<String, List<Rating>>>comparingDouble(e -> getAverage(e.getValue())).thenComparing(Map.Entry::getKey);
    }


    public Comparator<Map.Entry<String, List<Rating>>> avgThenRatingCount() {

        return Comparator.<Map.Entry<String, List<Rating>>>comparingDouble(e -> getAverage(e.getValue())).thenComparing(e -> totalRatings(e.getValue()));
    }


    public Comparator<Map.Entry<String, List<Rating>>> avgThenRecentRating() {

        return Comparator.<Map.Entry<String, List<Rating>>>comparingDouble(e -> getAverage(e.getValue())).thenComparing(e -> getMostRecentRating(e.getValue()));
    }


    public Comparator<Map.Entry<String, List<Rating>>> avgThenFirstRatingTime() {

        return Comparator.<Map.Entry<String, List<Rating>>>comparingDouble(e -> getAverage(e.getValue())).thenComparing(e -> getFirstRatingTimeStamp(e.getValue()));
    }


    public static void main(String[] args) {


        CustomerSupportSystem system = new CustomerSupportSystem();

        // Sample ratings
        system.addRating("Alice", 5);
        system.addRating("Bob", 4);
        system.addRating("Charlie", 5);
        system.addRating("Alice", 4);
        system.addRating("Bob", 5);
        system.addRating("Charlie", 4);
        system.addRating("Bob", 5);
        system.addRating("Dave", 4);
        system.addRating("Dave", 5);

        // Display by different strategies
        System.out.println("1. Avg Rating > Name:");
        system.getAverageRatingView(system.avgThenName()).forEach(System.out::println);

        System.out.println("\n2. Avg Rating > Rating Count:");
        system.getAverageRatingView(system.avgThenRatingCount()).forEach(System.out::println);

        System.out.println("\n3. Avg Rating > Most Recent Rating:");
        system.getAverageRatingView(system.avgThenRecentRating()).forEach(System.out::println);

        System.out.println("\n4. Avg Rating > First Rating Timestamp:");
        system.getAverageRatingView(system.avgThenFirstRatingTime()).forEach(System.out::println);

    }
}
