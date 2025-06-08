package PostKarat08June.CustomerSatisfaction.LevelUp4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerSupportSystem {


    static class Rating {

        int value;
        LocalDateTime timeUpdated;

        public Rating(int value, LocalDateTime timeUpdated) {
            this.value = value;
            this.timeUpdated = timeUpdated;
        }
    }


    Map<String, List<Rating>> agentRatingsMap = new HashMap<>();


    public void addRating(String agentName, Integer rating, LocalDateTime timeUpdated) {

        if (rating < 1 || rating > 5) {

            throw new IllegalArgumentException(" rating must be between 1 and 5");
        }

        agentRatingsMap.computeIfAbsent(agentName, k -> new ArrayList<>()).add(new Rating(rating, timeUpdated));
    }


    public String exportReportAsCSVByMonth() {

        Map<String, Map<YearMonth, List<Rating>>> grouped = new HashMap<>();


        for (Map.Entry<String, List<Rating>> entry : agentRatingsMap.entrySet()) {

            String agentName = entry.getKey();
            List<Rating> ratings = entry.getValue();

            for (Rating rating : ratings) {

                YearMonth ym = YearMonth.from(rating.timeUpdated);
                grouped.computeIfAbsent(agentName, k -> new HashMap<>()).computeIfAbsent(ym, k -> new ArrayList<>()).add(rating);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Agent,Month,AverageRating,RatingCount\n");


        for (Map.Entry<String, Map<YearMonth, List<Rating>>> entry : grouped.entrySet()) {

            String agentName = entry.getKey();

            for (Map.Entry<YearMonth, List<Rating>> yearMonthListEntry : entry.getValue().entrySet()) {

                YearMonth yearMonth = yearMonthListEntry.getKey();

                double avg = yearMonthListEntry.getValue().stream().mapToInt(r -> r.value).average().orElse(0.0);

                Integer ratingCount = yearMonthListEntry.getValue().size();

                sb.append(String.format("%s,%s,%.2f,%d\n", agentName, yearMonth, avg, ratingCount));

            }
        }

        return sb.toString();
    }


    public void exportCSVToFile(String csvOutput, String filename) throws IOException {
        Files.write(Path.of(filename), csvOutput.getBytes());
        System.out.println(" CSV File : " + filename + " created successfully");
    }


    public static void main(String[] args) throws IOException {


        CustomerSupportSystem system = new CustomerSupportSystem();

        // Add sample ratings across months
        system.addRating("Alice", 5, LocalDateTime.of(2025, 1, 10, 10, 0));
        system.addRating("Alice", 4, LocalDateTime.of(2025, 1, 12, 11, 0));
        system.addRating("Bob", 5, LocalDateTime.of(2025, 1, 15, 14, 0));
        system.addRating("Bob", 3, LocalDateTime.of(2025, 2, 10, 10, 0));
        system.addRating("Charlie", 2, LocalDateTime.of(2025, 2, 20, 12, 0));
        system.addRating("Alice", 5, LocalDateTime.of(2025, 3, 1, 9, 0));

        // Export CSV
        String csvOutput = system.exportReportAsCSVByMonth();
        System.out.println(csvOutput);

        system.exportCSVToFile(csvOutput, "report.csv");


    }
}
