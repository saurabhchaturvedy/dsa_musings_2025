package PostKarat09June.CodeDesign.CustomerSatisfaction.LevelUp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerSatisfaction {


    Map<String, List<Integer>> agentRatingsMap;


    CustomerSatisfaction() {

        this.agentRatingsMap = new HashMap<>();
    }


    public void addRating(String agentName, int rating) {

        if (rating < 1 || rating > 5)
            throw new IllegalArgumentException(" rating must be between 1 and 5");

        agentRatingsMap.computeIfAbsent(agentName, k -> new ArrayList<>()).add(rating);
    }


    public double average(List<Integer> ratings) {

        if (ratings == null || ratings.isEmpty()) return 0.0;

        double sum = 0;

        for (Integer rating : ratings) {

            sum += rating;
        }

        return (double) sum / ratings.size();
    }


    public List<String> agentToRatingsAverageView() {

        List<Map.Entry<String, List<Integer>>> entries = new ArrayList<>(agentRatingsMap.entrySet());

        entries.sort(

                (e1, e2) -> {


                    double avg1 = average(e1.getValue());
                    double avg2 = average(e2.getValue());

                    return Double.compare(avg2, avg1);

                }
        );


        List<String> result = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : entries) {

            String agentName = entry.getKey();
            double average = average(entry.getValue());
            result.add(" agent name --> " + agentName + " average --> " + average);
        }


        return result;
    }

    public static void main(String[] args) {


        CustomerSatisfaction system = new CustomerSatisfaction();

        system.addRating("Alice", 5);
        system.addRating("Bob", 3);
        system.addRating("Alice", 4);
        system.addRating("Charlie", 5);
        system.addRating("Bob", 4);
        system.addRating("Charlie", 5);

        List<String> ratings = system.agentToRatingsAverageView();
        for (String s : ratings) {
            System.out.println(s);
        }
    }
}
