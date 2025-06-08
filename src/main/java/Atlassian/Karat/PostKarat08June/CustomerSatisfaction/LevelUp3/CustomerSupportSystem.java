package PostKarat08June.CustomerSatisfaction.LevelUp3;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;

public class CustomerSupportSystem {


    static class Rating {

        int value;
        LocalDateTime timeUpdated;

        public Rating(int value, LocalDateTime timeUpdated) {
            this.value = value;
            this.timeUpdated = timeUpdated;
        }
    }

    static class AgentRatingSummary {

        String agentName;
        List<Rating> ratings;

        public AgentRatingSummary(String agentName, List<Rating> ratings) {
            this.agentName = agentName;
            this.ratings = ratings;
        }


        public double average() {

            return ratings.stream().mapToInt(r -> r.value).average().orElse(0.0);
        }

        public Integer count() {

            return ratings.size();
        }

        public LocalDateTime firstRatingTimeStamp() {

            return ratings.get(0).timeUpdated;
        }

        public Integer mostRecentRating() {

            return ratings.get(ratings.size() - 1).value;
        }

    }


    Map<String, List<Rating>> agentRatingsMap = new HashMap<>();


    public void addRating(String agentName, Integer rating, LocalDateTime timeUpdated) {


        if (rating < 1 || rating > 5) {

            throw new IllegalArgumentException(" Rating must be between 1 and 5");
        }

        agentRatingsMap.computeIfAbsent(agentName, k -> new ArrayList<>()).add(new Rating(rating, timeUpdated));

    }


    public List<String> getAgentRatingSummaryByMonth(YearMonth month, Comparator<AgentRatingSummary> comparator) {


        List<AgentRatingSummary> summaries = new ArrayList<>();


        for (Map.Entry<String, List<Rating>> entry : agentRatingsMap.entrySet()) {


            String agentName = entry.getKey();
            List<Rating> ratingsForGivenMonth = entry.getValue().stream().filter(rating -> YearMonth.from(rating.timeUpdated).equals(month)).toList();

            if (!ratingsForGivenMonth.isEmpty()) {

                summaries.add(new AgentRatingSummary(agentName, ratingsForGivenMonth));
            }
        }

        summaries.sort(comparator);

        List<String> result = new ArrayList<>();

        for (AgentRatingSummary summary : summaries) {

            result.add(summary.agentName + " -> average = " + summary.average() + " count = " + summary.count());

        }


        return result;
    }


    public Comparator<AgentRatingSummary> avgThenName() {

        return Comparator.<AgentRatingSummary>comparingDouble(a -> a.average()).thenComparing(a -> a.agentName);
    }


    public Comparator<AgentRatingSummary> avgThenCount() {

        return Comparator.<AgentRatingSummary>comparingDouble(a -> a.average()).thenComparing(a -> a.count());
    }

    public Comparator<AgentRatingSummary> avgThenMostRecent() {

        return Comparator.<AgentRatingSummary>comparingDouble(a -> a.average()).thenComparing(a -> a.mostRecentRating());
    }

    public Comparator<AgentRatingSummary> avgThenFirstRatingTimeStamp() {

        return Comparator.<AgentRatingSummary>comparingDouble(a -> a.average()).thenComparing(a -> a.firstRatingTimeStamp());
    }


    public static void main(String[] args) {


        CustomerSupportSystem system = new CustomerSupportSystem();

        LocalDateTime jan = LocalDateTime.of(2025, 1, 15, 10, 0);
        LocalDateTime feb = LocalDateTime.of(2025, 2, 10, 11, 0);
        LocalDateTime mar = LocalDateTime.of(2025, 3, 5, 9, 0);

        // Add ratings across different months
        system.addRating("Alice", 5, jan);
        system.addRating("Alice", 4, jan);
        system.addRating("Bob", 5, jan);
        system.addRating("Charlie", 3, jan);
        system.addRating("Bob", 5, feb);
        system.addRating("Alice", 2, feb);
        system.addRating("Charlie", 5, mar);

        YearMonth queryMonth = YearMonth.of(2025, 1);
        System.out.println("Best Agents in " + queryMonth + ":");
        List<String> results = system.getAgentRatingSummaryByMonth(queryMonth, system.avgThenCount());
        results.forEach(System.out::println);

    }
}
