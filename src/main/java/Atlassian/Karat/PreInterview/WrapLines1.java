package PreInterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WrapLines1 {


    public static List<String> wrapLines1(List<String> words, int maxChars) {

        List<String> result = new ArrayList<>();
        StringBuilder wrappedLines = new StringBuilder();


        for (String word : words) {

            if (wrappedLines.isEmpty()) {

                wrappedLines.append(word);
            } else {


                if (wrappedLines.length() + 1 + word.length() <= maxChars) {

                    wrappedLines.append("-").append(word);
                } else {


                    result.add(wrappedLines.toString());
                    wrappedLines = new StringBuilder(word);
                }
            }
        }

        if (!wrappedLines.isEmpty()) {

            result.add(wrappedLines.toString());
        }

        return result;
    }


    public static void main(String[] args) {



        List<String> words1 = Arrays.asList("The", "day", "began", "as", "still", "as", "the",
                "night", "abruptly", "lighted", "with", "brilliant", "flame");


        System.out.println(wrapLines1(words1,13));
    }
}
