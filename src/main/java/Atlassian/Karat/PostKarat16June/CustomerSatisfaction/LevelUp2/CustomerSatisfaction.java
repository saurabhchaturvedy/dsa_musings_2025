package PostKarat16June.CustomerSatisfaction.LevelUp2;

import java.util.*;

public class CustomerSatisfaction {


    static class Rating {

        int value;
        long timeUpdated;

        public Rating(int value, long timeUpdated) {
            this.value = value;
            this.timeUpdated = timeUpdated;
        }
    }

    Map<String, List<Rating>> agentToRatingsMap;
    long globalTimestamp = 0;


    CustomerSatisfaction() {

        this.agentToRatingsMap = new HashMap<>();
    }


    public void addRating(String agentName, Integer rating) {

        if (rating < 1 || rating > 5) {
            System.out.println(" Rating must be between 1 and 5");
        }

        agentToRatingsMap.computeIfAbsent(agentName, k -> new ArrayList<>()).add(new Rating(rating, ++globalTimestamp));
    }


    public List<String> getAgentAverageRatingView(Comparator<Map.Entry<String, List<Rating>>> comparator) {

        List<Map.Entry<String, List<Rating>>> entries = new ArrayList<>(agentToRatingsMap.entrySet());

        entries.sort(comparator);

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, List<Rating>> entry : entries) {


            String agentName = entry.getKey();
            double average = average(entry.getValue());
            result.add(" Agent Name : " + agentName + " Average Rating : " + average);
        }

        return result;
    }


    public double average(List<Rating> ratings) {

        return ratings.stream().mapToInt(x -> x.value).average().orElse(0.0);
    }

    public Integer totalRatings(List<Rating> ratings) {
        return ratings.stream().mapToInt(x -> x.value).sum();
    }


    public long firstRatingTimeStamp(List<Rating> ratings) {

        return ratings.get(0).timeUpdated;
    }

    public Integer mostRecentRating(List<Rating> ratings) {

        return ratings.get(ratings.size() - 1).value;
    }


    public Comparator<Map.Entry<String, List<Rating>>> avgThenName() {

        return Comparator.<Map.Entry<String, List<Rating>>>comparingDouble(x -> average(x.getValue())).thenComparing(Map.Entry::getKey);
    }


    public Comparator<Map.Entry<String, List<Rating>>> avgThenRatingCount() {

        return Comparator.<Map.Entry<String, List<Rating>>>comparingDouble(x -> average(x.getValue())).thenComparing(x -> totalRatings(x.getValue()));
    }


    public Comparator<Map.Entry<String, List<Rating>>> avgThenFirstRatingTimestamp() {

        return Comparator.<Map.Entry<String, List<Rating>>>comparingDouble(x -> average(x.getValue())).thenComparing(x -> firstRatingTimeStamp(x.getValue()));
    }


    public Comparator<Map.Entry<String, List<Rating>>> avgThenMostRecentRating() {

        return Comparator.<Map.Entry<String, List<Rating>>>comparingDouble(x -> average(x.getValue())).thenComparing(x -> mostRecentRating(x.getValue()));
    }


    public static void main(String[] args) {


        CustomerSatisfaction system = new CustomerSatisfaction();

        system.addRating("Alice", 5);
        system.addRating("Bob", 3);
        system.addRating("Alice", 4);
        system.addRating("Charlie", 5);
        system.addRating("Bob", 4);
        system.addRating("Charlie", 5);

        List<String> ratings = system.getAgentAverageRatingView(system.avgThenName());
        for (String s : ratings) {
            System.out.println(s);
        }
    }
}
