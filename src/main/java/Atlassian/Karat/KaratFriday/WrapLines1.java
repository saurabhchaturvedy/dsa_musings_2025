package KaratFriday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WrapLines1 {


    public static List<String> wrapLines1(List<String> words, int maxChars) {


        List<String> result = new ArrayList<>();
        StringBuilder wrappedLine = new StringBuilder();

        for (String word : words) {

            if (wrappedLine.isEmpty()) {

                wrappedLine.append(word);
            } else {


                if (wrappedLine.length() + 1 + word.length() <= maxChars) {

                    wrappedLine.append("-").append(word);
                } else {

                    result.add(wrappedLine.toString());
                    wrappedLine = new StringBuilder(word);
                }
            }
        }

        if (!wrappedLine.isEmpty()) {

            result.add(wrappedLine.toString());
        }

        return result;

    }


    public static void main(String[] args) {



        List<String> words1 = Arrays.asList("The", "day", "began", "as", "still", "as", "the",
                "night", "abruptly", "lighted", "with", "brilliant", "flame");


        System.out.println(wrapLines1(words1,13));

    }
}
