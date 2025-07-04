package PostKarat16June.CustomerSatisfaction.LevelUp3;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;

public class CustomerSatisfaction {


    static class Rating {

        int value;
        LocalDateTime timeUpdated;

        public Rating(int value, LocalDateTime timeUpdated) {
            this.value = value;
            this.timeUpdated = timeUpdated;
        }
    }

    Map<String, List<Rating>> agentToRatingsMap;


    CustomerSatisfaction()
    {

        this.agentToRatingsMap = new HashMap<>();
    }

    public void addRating(String agentName, Integer rating, LocalDateTime timeUpdated) {

        if (rating < 1 || rating > 5)
            throw new IllegalArgumentException("Rating must be between 1 and 5");

        agentToRatingsMap.computeIfAbsent(agentName, k -> new ArrayList<>()).add(new Rating(rating, timeUpdated));
    }

    static class AgentRatingSummary {

        String agentName;
        List<Rating> ratings;

        public AgentRatingSummary(String agentName, List<Rating> ratings) {
            this.agentName = agentName;
            this.ratings = ratings;
        }

        public double average() {

            return ratings.stream().mapToInt(x -> x.value).average().orElse(0.0);
        }

        public Integer ratingCount() {

            return ratings.stream().mapToInt(x -> x.value).sum();
        }

        public LocalDateTime firstRatingTimeStamp() {

            return ratings.get(0).timeUpdated;
        }

        public Integer mostRecentRating() {

            return ratings.get(ratings.size() - 1).value;
        }
    }

    public List<String> getAgentRatingSummaryByMonth(YearMonth month, Comparator<AgentRatingSummary> comparator) {

        List<AgentRatingSummary> summaries = new ArrayList<>();


        for (Map.Entry<String, List<Rating>> entry : agentToRatingsMap.entrySet()) {

            String agentName = entry.getKey();

            List<Rating> ratings = entry.getValue();

            List<Rating> ratingsForMonth = ratings.stream().filter(x -> YearMonth.from(x.timeUpdated).equals(month)).toList();

            if (!ratingsForMonth.isEmpty()) {

                summaries.add(new AgentRatingSummary(agentName, ratingsForMonth));
            }
        }


        summaries.sort(comparator);

        List<String> result = new ArrayList<>();

        for (AgentRatingSummary summary : summaries) {

            result.add(" Agent Name : " + summary.agentName + " Average Rating :" + summary.average() + " Rating Count : " + summary.ratingCount());
        }


        return result;
    }


    public Comparator<AgentRatingSummary> avgThenName() {

        return Comparator.<AgentRatingSummary>comparingDouble(x -> x.average()).thenComparing(x -> x.agentName);
    }


    public Comparator<AgentRatingSummary> avgThenRatingCount() {

        return Comparator.<AgentRatingSummary>comparingDouble(x -> x.average()).thenComparing(x -> x.ratingCount());
    }


    public Comparator<AgentRatingSummary> avgThenFirstRatingTimeStamp() {

        return Comparator.<AgentRatingSummary>comparingDouble(x -> x.average()).thenComparing(x -> x.firstRatingTimeStamp());
    }


    public Comparator<AgentRatingSummary> avgThenMostRecentRating() {

        return Comparator.<AgentRatingSummary>comparingDouble(x -> x.average()).thenComparing(x -> x.mostRecentRating());
    }


    public static void main(String[] args) {


        CustomerSatisfaction system = new CustomerSatisfaction();

        LocalDateTime jan = LocalDateTime.of(2025, 1, 15, 10, 0);
        LocalDateTime feb = LocalDateTime.of(2025, 2, 10, 11, 0);
        LocalDateTime mar = LocalDateTime.of(2025, 3, 5, 9, 0);

        // Add ratings across different months
        system.addRating("Alice", 5, jan);
        system.addRating("Alice", 4, jan);
        system.addRating("Bob", 5, jan);
        system.addRating("Charlie", 4, jan);
        system.addRating("Bob", 5, feb);
        system.addRating("Alice", 2, feb);
        system.addRating("Charlie", 5, mar);

        YearMonth queryMonth = YearMonth.of(2025, 1);
        System.out.println("Best Agents in " + queryMonth + ":");
        List<String> results = system.getAgentRatingSummaryByMonth(queryMonth, system.avgThenRatingCount());
        results.forEach(System.out::println);


    }
}
