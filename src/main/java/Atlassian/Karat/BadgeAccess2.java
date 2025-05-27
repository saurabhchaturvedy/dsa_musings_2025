import java.util.*;

public class BadgeAccess2 {


    public static Map<String, List<Integer>> badgeaccess2(List<String[]> accessLog) {


        Map<String, List<Integer>> accessMap = new HashMap<>();

        for (String[] record : accessLog) {

            String name = record[0];
            Integer time = Integer.parseInt(record[1]);

            accessMap.computeIfAbsent(name, k -> new ArrayList<>()).add(time);
        }

        Map<String, List<Integer>> result = new HashMap<>();

        for (Map.Entry<String, List<Integer>> entry : accessMap.entrySet()) {

            String name = entry.getKey();
            List<Integer> times = entry.getValue();
            Collections.sort(times);

            for (int i = 0; i < times.size(); i++) {
                List<Integer> tempList = new ArrayList<>();
                tempList.add(times.get(i));
                int startTime = getMinutes(times.get(i));


                for (int j = i + 1; j < times.size(); j++) {

                    int endTime = getMinutes(times.get(j));

                    if ((endTime - startTime) <= 60) {
                        tempList.add(times.get(j));
                    } else {

                        break;
                    }
                }


                if (tempList.size() >= 3) {

                    result.put(name, tempList);
                    break;
                }

            }
        }

        return result;
    }

    private static int getMinutes(Integer time) {


        int hour = time / 100;
        int mins = time % 100;

        return hour * 60 + mins;
    }


    public static void main(String[] args) {





        List<String[]> badgeRecords = Arrays.asList(
                new String[]{"Paul", "1355"},
                new String[]{"Jennifer", "1910"},
                new String[]{"John", "830"},
                new String[]{"Paul", "1315"},
                new String[]{"John", "835"},
                new String[]{"Paul", "1405"},
                new String[]{"Paul", "1630"},
                new String[]{"John", "855"},
                new String[]{"John", "915"},
                new String[]{"Jennifer", "1335"},
                new String[]{"Jennifer", "730"},
                new String[]{"John", "1630"}
        );

        Map<String, List<Integer>> result = badgeaccess2(badgeRecords);

        for (Map.Entry<String, List<Integer>> entry : result.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (int time : entry.getValue()) {
                System.out.print(time + " ");
            }
            System.out.println();
        }

    }
}
