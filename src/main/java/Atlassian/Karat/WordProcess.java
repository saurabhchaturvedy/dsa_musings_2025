import java.util.ArrayList;
import java.util.List;

public class WordProcess {


    public static List<String> reflow(String[] lines, int maxWidth) {

        List<String> words = new ArrayList<>();
        List<String> result = new ArrayList<>();


        for (String line : lines) {

            for (String word : line.split(" ")) {

                words.add(word);
            }
        }

        List<String> currentLine = new ArrayList<>();
        int currentLength = 0;

        for (String word : words) {


            if (currentLine.size() + currentLength + word.length() <= maxWidth) {

                currentLine.add(word);
                currentLength += word.length();

            } else {


                result.add(justify(currentLine, maxWidth));
                currentLine = new ArrayList<>();
                currentLine.add(word);
                currentLength = word.length();
            }


        }


        if (!currentLine.isEmpty()) {

            result.add(justify(currentLine, maxWidth));
        }

        return result;
    }


    public static String justify(List<String> words, int maxWidth) {


        if (words.size() == 1) return words.get(0);


        int totalChars = words.stream().mapToInt(String::length).sum();
        int totalSpaces = maxWidth - totalChars;
        int spacesBetweenWords = totalSpaces / (words.size() - 1);
        int extraSpaces = totalSpaces % (words.size() - 1);


        StringBuilder justifiedLine = new StringBuilder();

        String str = "";

        for (int i = 0; i < words.size(); i++) {

            justifiedLine.append(words.get(i));

            if (i < words.size() - 1) {

                for (int k = 0; k < spacesBetweenWords; k++) {

                    str += "-";
                }

                justifiedLine.append(str);


                if (extraSpaces > 0) {

                    justifiedLine.append("-");
                    extraSpaces--;
                }

                str = "";
            }
        }

        return justifiedLine.toString();
    }


    public static void main(String[] args) {



        String[] lines1 = {
                "The day began as still as the",
                "night abruptly lighted with",
                "brilliant flame"
        };
        System.out.println(reflow(lines1, 24));
    }
}
