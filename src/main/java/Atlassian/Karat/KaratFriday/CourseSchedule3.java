package KaratFriday;

import java.util.*;

public class CourseSchedule3 {


    public static List<String> getMidCoursesList(List<String[]> prerequisites) {


        Map<String, List<String>> adjacencyList = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();

        for (String[] prerequisite : prerequisites) {

            String from = prerequisite[0];
            String to = prerequisite[1];

            adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            indegree.put(to, indegree.getOrDefault(to, 0) + 1);
            indegree.putIfAbsent(from, 0);
        }


        List<String> roots = new ArrayList<>();
        for (String course : indegree.keySet()) {

            if (indegree.get(course) == 0) {
                roots.add(course);

            }
        }


        Set<String> result = new HashSet<>();

        for (String root : roots) {

            dfs(adjacencyList, root, new ArrayList<>(), result);
        }

        return new ArrayList<>(result);

    }

    private static void dfs(Map<String, List<String>> adjacencyList, String course, ArrayList<String> path, Set<String> result) {

        path.add(course);

        if (!adjacencyList.containsKey(course) || adjacencyList.get(course).isEmpty()) {

            int mid = path.size() / 2;
            result.add(path.get(mid));
        } else {


            for (String neighbour : adjacencyList.get(course)) {

                dfs(adjacencyList, neighbour, new ArrayList<>(path), result);
            }
        }
    }


    public static void main(String[] args) {





        List<String[]> allCourses = Arrays.asList(
                new String[]{"Logic","COBOL"},
                new String[]{"Data Structures","Algorithms"},
                new String[]{"Creative Writing","Data Structures"},
                new String[]{"Algorithms","COBOL"},
                new String[]{"Intro to Computer Science","Data Structures"},
                new String[]{"Logic","Compilers"},
                new String[]{"Data Structures","Logic"},
                new String[]{"Creative Writing","System Administration"},
                new String[]{"Databases","System Administration"},
                new String[]{"Creative Writing","Databases"}
        );

        List<String> mids = getMidCoursesList(allCourses);
        System.out.println("Midpoint Courses: " + mids);




    }
}
