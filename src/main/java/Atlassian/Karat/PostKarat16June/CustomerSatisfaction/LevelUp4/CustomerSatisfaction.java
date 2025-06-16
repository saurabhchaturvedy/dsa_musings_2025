package PostKarat16June.CustomerSatisfaction.LevelUp4;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    CustomerSatisfaction() {

        this.agentToRatingsMap = new HashMap<>();
    }

    public void addRating(String agentName, Integer rating, LocalDateTime timeUpdated) {


        if (rating < 1 || rating > 5)
            throw new IllegalArgumentException("Rating must be between 1 and 5");

        agentToRatingsMap.computeIfAbsent(agentName, k -> new ArrayList<>()).add(new Rating(rating, timeUpdated));
    }

    static class AgentMonthlyAverageReport {

        String agentName;
        String month;
        double monthlyAverage;
        Integer ratingCount;

        public AgentMonthlyAverageReport(String agentName, String month, double monthlyAverage, Integer ratingCount) {
            this.agentName = agentName;
            this.month = month;
            this.monthlyAverage = monthlyAverage;
            this.ratingCount = ratingCount;
        }


        AgentMonthlyAverageReport() {

        }
    }


    public List<AgentMonthlyAverageReport> generateMonthWiseReport() {

        Map<String, Map<YearMonth, List<Rating>>> grouped = new HashMap<>();
        List<AgentMonthlyAverageReport> result = new ArrayList<>();


        for (Map.Entry<String, List<Rating>> entry : agentToRatingsMap.entrySet()) {

            String agentName = entry.getKey();

            List<Rating> ratings = entry.getValue();

            for (Rating rating : ratings) {

                YearMonth ym = YearMonth.from(rating.timeUpdated);

                grouped.computeIfAbsent(agentName, k -> new HashMap<>()).computeIfAbsent(ym, k -> new ArrayList<>()).add(rating);
            }
        }

        for (Map.Entry<String, Map<YearMonth, List<Rating>>> entry : grouped.entrySet()) {

            String agentName = entry.getKey();

            for (Map.Entry<YearMonth, List<Rating>> ent : entry.getValue().entrySet()) {

                YearMonth month = ent.getKey();
                List<Rating> ratings = ent.getValue();

                double average = ratings.stream().mapToInt(x -> x.value).average().orElse(0.0);
                result.add(new AgentMonthlyAverageReport(agentName, month.toString(), average, ratings.size()));
            }
        }

        return result;
    }


    public void exportAsCSV(String fileName) throws IOException {

        StringBuilder sb = new StringBuilder();
        sb.append("Agent_Name,Month,Monthly_Average,Ratings_Counts\n");

        for (AgentMonthlyAverageReport agentMonthlyAverageReport : generateMonthWiseReport()) {

            sb.append(String.format("%s,%s,%.2f,%d\n", agentMonthlyAverageReport.agentName, agentMonthlyAverageReport.month, agentMonthlyAverageReport.monthlyAverage, agentMonthlyAverageReport.ratingCount));
        }

        Files.write(Path.of(fileName), sb.toString().getBytes());
    }


//    public void exportAsJSON(String fileName) throws IOException {
//
//        ObjectMapper mapper = new ObjectMapper();
//        List<AgentMonthlyAverageReport> reports = generateMonthWiseReport();
//        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reports);
//        Files.write(Path.of(fileName), json.getBytes());
//    }


    public static void main(String[] args) throws IOException {


        CustomerSatisfaction customerSatisfaction = new CustomerSatisfaction();

        // Sample ratings
        customerSatisfaction.addRating("Alice", 5, LocalDateTime.of(2025, 1, 10, 10, 0));
        customerSatisfaction.addRating("Alice", 4, LocalDateTime.of(2025, 1, 15, 11, 0));
        customerSatisfaction.addRating("Bob", 5, LocalDateTime.of(2025, 2, 20, 12, 0));
        customerSatisfaction.addRating("Charlie", 3, LocalDateTime.of(2025, 2, 25, 9, 0));
        customerSatisfaction.addRating("Charlie", 3, LocalDateTime.of(2025, 6, 25, 9, 0));

        // Export in all formats
        customerSatisfaction.exportAsCSV("report.csv");
   //    customerSatisfaction.exportAsJSON("report.json");
       // customerSatisfaction.exportXML("report.xml");

        System.out.println("Reports written: report.csv, report.json, report.xml");
    }
}
