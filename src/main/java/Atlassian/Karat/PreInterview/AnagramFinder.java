package PreInterview;

import KaratFriday.Intervals.InsertInterval;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramFinder {


    public static String getAnagram(String main, List<String> words) {

        for (String word : words) {

            Map<Character, Integer> freqMap = getFreqMap(word);
            int length = word.length();

            for (int i = 0; i <= main.length() - length; i++) {

                String window = main.substring(i, i + length);
                if (getFreqMap(window).equals(freqMap)) {
                    System.out.println(" Anagram found : " + getFreqMap(window).keySet());
                    return word;
                }
            }
        }

        return null;
    }

    private static Map<Character, Integer> getFreqMap(String word) {


        Map<Character, Integer> map = new HashMap<>();

        for (char c : word.toCharArray()) {

            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        return map;
    }


    public static void main(String[] args) {


        String main = "xylophoneabc";
        List<String> words = Arrays.asList("cab", "dog", "rat");

        String result = getAnagram(main, words);

        if (result != null) {
            System.out.println("Anagram found for word: " + result);
        } else {
            System.out.println("No anagram found.");
        }

    }
}
