import java.util.*;

public class BadgeAccess {


    public static Map<String, Set<String>> badgeAccess(List<String[]> accessLog) {

        Map<String, Boolean> insideMap = new HashMap<>();
        Set<String> enterWithoutExit = new HashSet<>();
        Set<String> exitWithoutEnter = new HashSet<>();

        for (String[] accessRecord : accessLog) {

            String name = accessRecord[0];
            String action = accessRecord[1];


            if (action.equals("enter")) {

                if (insideMap.getOrDefault(name, false)) {

                    enterWithoutExit.add(name);
                }

                insideMap.put(name, true);
            } else if (action.equals("exit")) {

                if (!insideMap.getOrDefault(name, false)) {

                    exitWithoutEnter.add(name);
                } else {

                    insideMap.put(name, false);
                }

            }
        }

        for (Map.Entry<String, Boolean> entry : insideMap.entrySet()) {

            if (entry.getValue()) {

                enterWithoutExit.add(entry.getKey());
            }
        }


        Map<String, Set<String>> result = new HashMap<>();
        result.put("entryWithoutExit", enterWithoutExit);
        result.put("exitWithoutentry", exitWithoutEnter);

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

        Map<String, Set<String>> result = badgeAccess(records);

        System.out.println("Entered without exiting: " + result.get("entryWithoutExit"));
        System.out.println("Exited without entering: " + result.get("exitWithoutentry"));
    }
    }

