package Post14June.Design.CustomerSatisfaction.LevelUp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerSatisfaction {


    Map<String, List<Integer>> agentToRatingsMap;

    CustomerSatisfaction() {

        this.agentToRatingsMap = new HashMap<>();
    }


    public void addRating(String agentName, int rating) {

        if (rating < 1 || rating > 5)
            throw new IllegalArgumentException("Rating should be between 1 and 5");


        agentToRatingsMap.computeIfAbsent(agentName, k -> new ArrayList<>()).add(rating);
    }


    public List<String> agentsAverageRatingView() {

        List<Map.Entry<String, List<Integer>>> entries = new ArrayList<>(agentToRatingsMap.entrySet());

        entries.sort((a, b) -> {

            double avg1 = average(a.getValue());
            double avg2 = average(b.getValue());

            return Double.compare(avg2, avg1);

        });


        List<String> result = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : entries) {

            String agentName = entry.getKey();
            List<Integer> ratings = entry.getValue();
            double average = average(ratings);
            result.add(agentName + " : -> Average : " + average);
        }

        return result;
    }


    public double average(List<Integer> ratings) {

        double sum = 0.0;

        for (Integer rating : ratings) {

            sum += rating;
        }

        return (double) sum / ratings.size();
    }


    public static void main(String[] args) {


        CustomerSatisfaction system = new CustomerSatisfaction();

        system.addRating("Alice", 5);
        system.addRating("Bob", 3);
        system.addRating("Alice", 4);
        system.addRating("Charlie", 5);
        system.addRating("Bob", 4);
        system.addRating("Charlie", 5);

        List<String> ratings = system.agentsAverageRatingView();
        for (String s : ratings) {
            System.out.println(s);
        }

    }
}
