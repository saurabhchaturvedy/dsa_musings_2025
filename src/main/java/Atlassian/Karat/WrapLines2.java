import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WrapLines2 {


    public static List<String> reflowJustify(String[] lines, int maxChars) {

        List<String> words = new ArrayList<>();

        for (String line : lines) {

            String[] parts = line.split(" ");
            Collections.addAll(words, parts);
        }


        List<String> result = new ArrayList<>();
        int index = 0;

        while (index < words.size()) {

            int currentLen = words.get(index).length();
            int last = index + 1;

            while (last < words.size()) {

                if (currentLen + 1 + words.get(index).length() > maxChars) break;
                currentLen = currentLen + 1 + words.get(index).length();
                last++;
            }

            result.add(justify(words.subList(index, last), maxChars));
            index = last;
        }

        return result;
    }


    public static String justify(List<String> listWords, int maxChars) {

        if (listWords.size() == 1) return listWords.get(0);


        int totalChars = listWords.stream().mapToInt(String::length).sum();
        int totalDashes = maxChars - totalChars;
        int gaps = listWords.size() - 1;

        int minDashes = totalDashes / gaps;
        int extraDashes = totalDashes % gaps;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < listWords.size(); i++) {

            sb.append(listWords.get(i));

            if (i < gaps) {

                for (int j = 0; j < minDashes + (i < extraDashes ? 1 : 0); j++) {

                    sb.append("-");
                }
            }
        }

        return sb.toString();
    }


    public static void main(String[] args) {


        String[] lines = {
                "The day began as still as the",
                "night abruptly lighted with",
                "brilliant flame"
        };

        System.out.println("=== width 24 ===");
        reflowJustify(lines, 24).forEach(System.out::println);
    }
}

