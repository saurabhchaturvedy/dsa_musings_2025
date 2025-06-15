package Post14June.Design.CustomerSatisfaction.LevelUp2;

import java.util.*;

public class CustomerSatisfaction {


    static class Rating {

        int value;
        long timeStamp;

        public Rating(int value, long timeStamp) {
            this.value = value;
            this.timeStamp = timeStamp;
        }
    }


    Map<String, List<Rating>> customerRatingsMap = new HashMap<>();
    long globalTimeStamp = 0;

    public void addRating(String agentName, Integer rating) {

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException(" Rating must be between 1 and 5");
        }

        customerRatingsMap.computeIfAbsent(agentName, k -> new ArrayList<>()).add(new Rating(rating, ++globalTimeStamp));
    }



    public List<String> agentsAverageRatingView(Comparator<Map.Entry<String, List<Rating>>> comparator) {

        List<Map.Entry<String,List<Rating>>> entries = new ArrayList<>(customerRatingsMap.entrySet());
        entries.sort(comparator);


        List<String> result = new ArrayList<>();

        for (Map.Entry<String, List<Rating>> entry : entries) {

            String agentName = entry.getKey();
            List<Rating> ratings = entry.getValue();
            double average = average(ratings);
            result.add(agentName + " : -> Average : " + average);
        }

        return result;


    }


    public double average(List<Rating> ratings) {


        return ratings.stream().mapToInt(x -> x.value).average().orElse(0.0);
    }


    public Integer ratingsCount(List<Rating> ratings) {

        return ratings.stream().mapToInt(x -> x.value).sum();
    }

    public long firstRatingTimeStamp(List<Rating> ratings) {

        return ratings.get(0).timeStamp;
    }

    public Integer mostRecentRating(List<Rating> ratings) {

        return ratings.get(ratings.size() - 1).value;
    }


    public Comparator<Map.Entry<String, List<Rating>>> avgThenName() {

        return Comparator.<Map.Entry<String, List<Rating>>>comparingDouble(x -> average(x.getValue())).thenComparing(Map.Entry::getKey);
    }


    public Comparator<Map.Entry<String, List<Rating>>> avgThenRatingsCount() {

        return Comparator.<Map.Entry<String, List<Rating>>>comparingDouble(x -> average(x.getValue())).thenComparing(x -> ratingsCount(x.getValue()));
    }


    public Comparator<Map.Entry<String, List<Rating>>> avgThenFirstRatingTimeStamp() {

        return Comparator.<Map.Entry<String, List<Rating>>>comparingDouble(x -> average(x.getValue())).thenComparing(x -> firstRatingTimeStamp(x.getValue()));
    }


    public Comparator<Map.Entry<String, List<Rating>>> avgThenMostRecentRating() {

        return Comparator.<Map.Entry<String, List<Rating>>>comparingDouble(x -> average(x.getValue())).thenComparing(x -> mostRecentRating(x.getValue()));
    }


    public static void main(String[] args) {



        CustomerSatisfaction system = new CustomerSatisfaction();

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
        system.agentsAverageRatingView(system.avgThenName()).forEach(System.out::println);

        System.out.println("\n2. Avg Rating > Rating Count:");
        system.agentsAverageRatingView(system.avgThenRatingsCount()).forEach(System.out::println);

        System.out.println("\n3. Avg Rating > Most Recent Rating:");
        system.agentsAverageRatingView(system.avgThenMostRecentRating()).forEach(System.out::println);

        System.out.println("\n4. Avg Rating > First Rating Timestamp:");
        system.agentsAverageRatingView(system.avgThenFirstRatingTimeStamp()).forEach(System.out::println);
    }
}
