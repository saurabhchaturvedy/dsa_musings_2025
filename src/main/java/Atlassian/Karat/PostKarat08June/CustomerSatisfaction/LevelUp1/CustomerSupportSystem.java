package PostKarat08June.CustomerSatisfaction.LevelUp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerSupportSystem {


    Map<String, List<Integer>> agentRatingsMap;


    CustomerSupportSystem() {
        this.agentRatingsMap = new HashMap<>();
    }


    // O(1)
    public void addRating(String agentName, Integer rating) {

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating need to be greater than 1 and less than or equal to 5");


        }

        agentRatingsMap.computeIfAbsent(agentName, k -> new ArrayList<>()).add(rating);
    }


    // O(nlogn)
    public List<String> getAverageRatingView() {


        List<Map.Entry<String, List<Integer>>> entries = new ArrayList<>(agentRatingsMap.entrySet());


        entries.sort((e1, e2) -> {


            double avg1 = getAverage(e1.getValue());
            double avg2 = getAverage(e2.getValue());

            return Double.compare(avg1, avg2);

        });


        List<String> result = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : entries) {

            double average = getAverage(entry.getValue());

            result.add(entry.getKey() + " -> average rating = " + average);
        }


        return result;
    }


    public double getAverage(List<Integer> ratings) {

        if (ratings == null || ratings.isEmpty()) return 0.0;

        double sum = 0;

        for (Integer rating : ratings) {

            sum += rating;
        }


        return (double) sum / ratings.size();
    }


    public static void main(String[] args) {


        CustomerSupportSystem system = new CustomerSupportSystem();

        system.addRating("Alice", 5);
        system.addRating("Bob", 3);
        system.addRating("Alice", 4);
        system.addRating("Charlie", 5);
        system.addRating("Bob", 4);
        system.addRating("Charlie", 5);

        List<String> ratings = system.getAverageRatingView();
        for (String s : ratings) {
            System.out.println(s);
        }
    }
}
