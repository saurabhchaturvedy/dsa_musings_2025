import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WrapLines1 {


    public static List<String> wrapLines1(List<String> words, int maxChars) {

        List<String> word = new ArrayList<>();
        StringBuilder line = new StringBuilder();

        for (String wrd : words) {

            if (line.isEmpty()) {

                line.append(wrd);
            } else {


                if (line.length() + 1 + wrd.length() <= maxChars) {

                    line.append("-").append(wrd);
                } else {

                    word.add(line.toString());
                    line = new StringBuilder(wrd);

                }
            }
        }


        if (!line.isEmpty()) {

            word.add(line.toString());
        }


        return word;
    }


    public static void main(String[] args) {


        List<String> words1 = Arrays.asList("The", "day", "began", "as", "still", "as", "the",
                "night", "abruptly", "lighted", "with", "brilliant", "flame");

        System.out.println(wrapLines1(words1, 13));
    }
}
