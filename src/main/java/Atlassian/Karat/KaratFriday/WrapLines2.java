package KaratFriday;

import java.util.ArrayList;
import java.util.List;

public class WrapLines2 {


    public static List<String> reflowAndJustify(String[] lines, int maxChars) {

        List<String> result = new ArrayList<>();
        List<String> words = new ArrayList<>();


        for (String line : lines) {

            for (String word : line.split(" ")) {

                words.add(word);
            }
        }


        List<String> currentLine = new ArrayList<>();
        int currentLength = 0;


        for (String word : words) {


            if (currentLength + currentLine.size() + word.length() <= maxChars) {

                currentLine.add(word);
                currentLength += word.length();

            } else {

                result.add(justify(currentLine, maxChars));
                currentLine = new ArrayList<>();
                currentLine.add(word);
                currentLength = word.length();
            }

        }

        if (!currentLine.isEmpty()) {

            result.add(justify(currentLine, maxChars));
        }

        return result;
    }


    public static String justify(List<String> words, int maxChars) {

        if (words.size() == 1) return words.get(0);


        int totalChars = words.stream().mapToInt(String::length).sum();
        int totalSpaces = maxChars - totalChars;
        int spacesBetweenWords = totalSpaces / (words.size() - 1);
        int extraSpaces = totalSpaces / (words.size() - 1);


        StringBuilder justifiedLine = new StringBuilder();
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < words.size(); i++) {

            justifiedLine.append(words.get(i));

            if (i < words.size() - 1) {

                for (int k = 0; k < spacesBetweenWords; k++) {

                    str.append("-");
                }

                justifiedLine.append(str);


                if (extraSpaces > 0) {

                    justifiedLine.append("-");
                    extraSpaces--;
                }
            }

            str = new StringBuilder();
        }

        return justifiedLine.toString();
    }


    public static void main(String[] args) {


        String[] lines1 = {
                "The day began as still as the",
                "night abruptly lighted with",
                "brilliant flame"
        };
        System.out.println(reflowAndJustify(lines1, 24));
    }
}
