package KaratFriday;

import java.util.*;

public class CourseSchedule2 {


    public static String findMidCourse(String[][] prerequisites) {


        Map<String, List<String>> adjacencyList = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();

        for (String[] prerequisite : prerequisites) {

            String from = prerequisite[0];
            String to = prerequisite[1];

            adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            indegree.put(to, indegree.getOrDefault(to, 0) + 1);
            indegree.putIfAbsent(from, 0);
        }


        Queue<String> queue = new LinkedList<>();

        for (String course : indegree.keySet()) {

            if (indegree.get(course) == 0) {

                queue.offer(course);
            }
        }

        List<String> topoOrder = new ArrayList<>();

        while (!queue.isEmpty()) {

            String currentCourse = queue.poll();
            topoOrder.add(currentCourse);

            for (String neighbour : adjacencyList.getOrDefault(currentCourse, new ArrayList<>())) {

                indegree.put(neighbour, indegree.get(neighbour) - 1);
                if (indegree.get(neighbour) == 0) {

                    queue.offer(neighbour);
                }
            }
        }


        int mid = topoOrder.size() / 2;
        return topoOrder.get(mid);
    }


    public static void main(String[] args) {



        String[][] prereqs1 = {
                {"a", "b"},
                {"b", "c"}
        };
        System.out.println("Mid Course: " + findMidCourse(prereqs1));  // Output: b

        String[][] prereqs2 = {
                {"Logic", "Algorithms"},
                {"Data Structures", "Logic"},
                {"Algorithms", "Calculus"},
                {"Calculus", "Linear Algebra"}
        };
        // Expected order: Data Structures → Logic → Algorithms → Calculus → Linear Algebra
        // Mid course: Algorithms
        System.out.println("Mid Course: " + findMidCourse(prereqs2));  //
    }
}
