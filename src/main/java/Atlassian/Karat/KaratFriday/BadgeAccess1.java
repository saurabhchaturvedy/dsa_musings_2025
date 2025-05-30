package KaratFriday;

import java.util.*;

public class BadgeAccess1 {


    public static Map<String, Set<String>> badgeAccess1(List<String[]> accessLog) {


        Map<String, Boolean> accessMap = new HashMap<>();
        Set<String> enterWithoutAnExit = new HashSet<>();
        Set<String> exitWithoutAnEntry = new HashSet<>();

        for (String[] record : accessLog) {

            String name = record[0];
            String action = record[1];


            if (action.equals("enter")) {

                if (accessMap.getOrDefault(name, false)) {

                    enterWithoutAnExit.add(name);
                }

                accessMap.put(name, true);
            } else if (action.equals("exit")) {

                if (!accessMap.getOrDefault(name, false)) {

                    exitWithoutAnEntry.add(name);
                } else {

                    accessMap.put(name, false);
                }

            }
        }

        for (Map.Entry<String, Boolean> entry : accessMap.entrySet()) {

            if (entry.getValue()) {

                enterWithoutAnExit.add(entry.getKey());
            }
        }

        Map<String, Set<String>> result = new HashMap<>();
        result.put("enterWithoutAnExit", enterWithoutAnExit);
        result.put("exitWithoutAnEntry", exitWithoutAnEntry);

        return result;
    }


    public static void main(String[] args) {


        List<String[]> records = Arrays.asList(
                new String[]{"Martha", "exit"},
                new String[]{"Paul", "enter"},
                new String[]{"Martha", "enter"},
                new String[]{"Martha", "exit"},
                new String[]{"Jennifer", "enter"},
                new String[]{"Paul", "enter"},
                new String[]{"Curtis", "enter"},
                new String[]{"Paul", "exit"},
                new String[]{"Martha", "enter"},
                new String[]{"Martha", "exit"},
                new String[]{"Jennifer", "exit"}
        );

        Map<String, Set<String>> result = badgeAccess1(records);

        System.out.println("Entered without exiting: " + result.get("enterWithoutAnExit"));
        System.out.println("Exited without entering: " + result.get("exitWithoutAnEntry"));
    }
}
