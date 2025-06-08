package PostKarat08June.CustomerSatisfaction.LevelUp5;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;

public class CustomerSupportSystem {

    static class Rating {
        int value;
        LocalDateTime timestamp;

        Rating(int value, LocalDateTime timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    static class AgentMonthlyReport {
        public String agent;
        public String month; // YYYY-MM
        public double averageRating;
        public int ratingCount;

        AgentMonthlyReport(String agent, String month, double averageRating, int ratingCount) {
            this.agent = agent;
            this.month = month;
            this.averageRating = averageRating;
            this.ratingCount = ratingCount;
        }

        // Needed for Jackson
        public AgentMonthlyReport() {}
    }

    private final Map<String, List<Rating>> agentRatings = new HashMap<>();

    public void addRating(String agentName, int rating) {
        addRating(agentName, rating, LocalDateTime.now());
    }

    public void addRating(String agentName, int rating, LocalDateTime dateTime) {
        if (rating < 1 || rating > 5) throw new IllegalArgumentException("Rating must be 1-5");

        agentRatings
            .computeIfAbsent(agentName, k -> new ArrayList<>())
            .add(new Rating(rating, dateTime));
    }

    private List<AgentMonthlyReport> generateMonthlyReports() {
        Map<String, Map<YearMonth, List<Rating>>> grouped = new HashMap<>();
        List<AgentMonthlyReport> result = new ArrayList<>();

        for (Map.Entry<String, List<Rating>> entry : agentRatings.entrySet()) {
            String agent = entry.getKey();
            for (Rating rating : entry.getValue()) {
                YearMonth ym = YearMonth.from(rating.timestamp);
                grouped
                    .computeIfAbsent(agent, k -> new HashMap<>())
                    .computeIfAbsent(ym, k -> new ArrayList<>())
                    .add(rating);
            }
        }

        for (Map.Entry<String, Map<YearMonth, List<Rating>>> agentEntry : grouped.entrySet()) {
            String agent = agentEntry.getKey();
            for (Map.Entry<YearMonth, List<Rating>> monthEntry : agentEntry.getValue().entrySet()) {
                YearMonth month = monthEntry.getKey();
                List<Rating> ratings = monthEntry.getValue();
                double avg = ratings.stream().mapToInt(r -> r.value).average().orElse(0.0);
                result.add(new AgentMonthlyReport(agent, month.toString(), avg, ratings.size()));
            }
        }

        return result;
    }

    public void exportCSV(String filename) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Agent,Month,AverageRating,RatingCount\n");

        for (AgentMonthlyReport report : generateMonthlyReports()) {
            sb.append(String.format("%s,%s,%.2f,%d\n",
                    report.agent, report.month, report.averageRating, report.ratingCount));
        }

        Files.write(Path.of(filename), sb.toString().getBytes());
    }

    public void exportJSON(String filename) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        List<AgentMonthlyReport> reports = generateMonthlyReports();
//        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reports);
//        Files.write(Path.of(filename), json.getBytes());
    }

    public void exportXML(String filename) throws IOException {
//        XmlMapper xmlMapper = new XmlMapper();
//        List<AgentMonthlyReport> reports = generateMonthlyReports();
//        String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(reports);
//        Files.write(Path.of(filename), xml.getBytes());
    }

    public static void main(String[] args) throws IOException {
        CustomerSupportSystem system = new CustomerSupportSystem();

        // Sample ratings
        system.addRating("Alice", 5, LocalDateTime.of(2025, 1, 10, 10, 0));
        system.addRating("Alice", 4, LocalDateTime.of(2025, 1, 15, 11, 0));
        system.addRating("Bob", 5, LocalDateTime.of(2025, 2, 20, 12, 0));
        system.addRating("Charlie", 3, LocalDateTime.of(2025, 2, 25, 9, 0));

        // Export in all formats
        system.exportCSV("report.csv");
        system.exportJSON("report.json");
        system.exportXML("report.xml");

        System.out.println("Reports written: report.csv, report.json, report.xml");
    }
}
