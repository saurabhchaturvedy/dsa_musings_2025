
import java.util.ArrayList;
import java.util.List;

public class WordProcessor {

    public static List<String> reflowAndJustify(String[] lines, int maxWidth) {
        List<String> result = new ArrayList<>();
        
        // List to hold words to process
        List<String> words = new ArrayList<>();

        // Split each line into words
        for (String line : lines) {
            for (String word : line.split(" ")) {
                words.add(word);
            }
        }

        // Initialize current line
        List<String> currentLine = new ArrayList<>();
        int currentLength = 0;

        // Process each word
        for (String word : words) {
            // Check if adding the word would exceed maxWidth
            if (currentLength + currentLine.size() + word.length() <= maxWidth) {
                currentLine.add(word);
                currentLength += word.length();
            } else {
                // Justify the current line and add to result
                result.add(justify(currentLine, maxWidth));
                // Start a new line with the current word
                currentLine = new ArrayList<>();
                currentLine.add(word);
                currentLength = word.length();
            }
        }

        // Add the last line if it exists
        if (!currentLine.isEmpty()) {
            result.add(justify(currentLine, maxWidth));
        }

        return result;
    }

    // Method to justify a line
    private static String justify(List<String> words, int maxWidth) {
        if (words.size() == 1) {
            return words.get(0); // No justification needed for single word
        }

        int totalChars = words.stream().mapToInt(String::length).sum();
        int totalSpaces = maxWidth - totalChars;
        int spaceBetweenWords = totalSpaces / (words.size() - 1);
        int extraSpaces = totalSpaces % (words.size() - 1);

        StringBuilder justifiedLine = new StringBuilder();
        String str="";
        for (int i = 0; i < words.size(); i++) {
            justifiedLine.append(words.get(i));
            if (i < words.size() - 1) { // No space after the last word
                // Add base spaces

                for(int k=0; k<spaceBetweenWords; k++)
                {
                  str+="-";
                }
                justifiedLine.append(str);
                // Add extra spaces if any
                if (extraSpaces > 0) {
                    justifiedLine.append("-");
                    extraSpaces--;
                }
            }
            str="";
        }
        return justifiedLine.toString();
    }

    public static void main(String[] args) {
        String[] lines1 = {
                "The day began as still as the",
                "night abruptly lighted with",
                "brilliant flame"
        };
       System.out.println(reflowAndJustify(lines1, 24)); // Expected output: ["The--day--began-as-still", ...]
     //   System.out.println(reflowAndJustify(lines1, 25)); // Expected output: ["The-day-began-as-still-as", ...]
     //   System.out.println(reflowAndJustify(lines1, 26)); // Expected output: ["The--day-began-as-still-as", ...]
      //  System.out.println(reflowAndJustify(lines1, 40)); // Expected output: ["The--day--began--as--still--as-the-night", ...]
    // System.out.println(reflowAndJustify(lines1, 14)); // Expected output: ["The--day-began", ...]
    //    System.out.println(reflowAndJustify(lines1, 15)); // Expected output: ["The--day--began", ...]
        
        String[] lines2 = { "a b", "c d" };
       System.out.println(reflowAndJustify(lines2, 20)); // Expected output: ["a------b-----c-----d"]
    //    System.out.println(reflowAndJustify(lines2, 4));  // Expected output: ["a--b", "c--d"]
      //  System.out.println(reflowAndJustify(lines2, 2));  // Expected output: ["a", "b", "c", "d"]
    }
}